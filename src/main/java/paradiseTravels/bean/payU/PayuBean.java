package paradiseTravels.bean.payU;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import paradiseTravels.bean.ReservationBean;
import paradiseTravels.bean.payU.exception.PayUException;
import paradiseTravels.model.Reservation;
import paradiseTravels.model.ReservationStatus;

import javax.inject.Inject;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PayuBean {

    @Inject
    ReservationBean reservationBean;

    private final static String PAYU_CLIENT_ID = "339155";
    private final static String PAYU_CLIENT_SECRET = "9ed63952ca644415a311e93b429c9c84";

    public static class PayuUInitRequestObject implements Serializable {
        public String notifyUrl = "http://77.55.193.96:8080/paradiseTravels/reservations/payu/notify"; // endpoint do odsyłania statusu
        public String customerIp= "192.168.1.17";
        public String merchantPosId = "339155";
        public String description = "xxx";
        public String currencyCode = "PLN";
        public String continueUrl = "http://localhost:4200/profile?paid=1";
        public String extOrderId;// id reserwacji dzieki ktoremu mamy potwierdzenie
        public int totalAmount;
        public List<Product> products = new ArrayList<>();
    }
//
    public static class Product implements Serializable{
        public Product(String name, int unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public String name;
        public int unitPrice;
        public String quantity = "1";
    }


    /**
     * inicjuje płatność w payU
     * @param reservation
     * @return url na strone gdzie można wykonać płatność
     * @throws Exception
     */
    public URL initPayment(Reservation reservation) throws PayUException {
        try {
            String accessToken = auth();

            Gson gson = new Gson();

            PayuUInitRequestObject payuUInitRequestObject = new PayuUInitRequestObject();
            payuUInitRequestObject.extOrderId = reservation.getId().toString();
            payuUInitRequestObject.totalAmount = (int)reservation.getPrice()*100; // wartość musi być w groszach

            payuUInitRequestObject.products.add(new Product(reservation.getOffer().getName(), payuUInitRequestObject.totalAmount));

            String jsonBuyRequest = gson.toJson(payuUInitRequestObject);

            HttpResponse<JsonNode> jsonNodeHttpResponse = jsonNodeHttpResponse = Unirest.post("https://secure.snd.payu.com/api/v2_1/orders")
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type","application/json")
                    .body(jsonBuyRequest).asJson();

            if(jsonNodeHttpResponse.getStatus() != 302) {
                throw new PayUException("create order payu request return http code: "+jsonNodeHttpResponse.getStatus());
            }

            reservation.setReservationStatus(ReservationStatus.PAYMENT_PROCESSED);
            reservation.setPayuOrderId((String) jsonNodeHttpResponse.getBody().getObject().get("orderId"));
            reservationBean.update(reservation);

            return new URL(jsonNodeHttpResponse.getBody().getObject().getString("redirectUri"));
        } catch (Exception e) {
            throw new PayUException(e);
        }
    }

    /**
     * PayU wymaga najpierw zalogowania się. Po zalogowaniu się otrzymujemy token, którym musimy
     * posługiwać się przy każdym kolejnym requeście.
     * @return access token wymagany przy requestach do payU
     * @throws PayUException
     */
    private String auth() throws PayUException {

        try {
            HttpResponse<JsonNode> jsonNodeHttpResponse = null;

            jsonNodeHttpResponse = Unirest.post("https://secure.snd.payu.com/pl/standard/user/oauth/authorize")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .body("grant_type=client_credentials&client_id="+PAYU_CLIENT_ID+"&client_secret="+PAYU_CLIENT_SECRET)
                    .asJson();

            if(jsonNodeHttpResponse.getStatus() != 200) {
                throw new PayUException("auth payu request return http code: "+jsonNodeHttpResponse.getStatus());
            }

            return jsonNodeHttpResponse.getBody().getObject().get("access_token").toString();

        } catch (UnirestException e) {
            throw new PayUException(e);
        }
    }
}
