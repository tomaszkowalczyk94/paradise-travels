package paradiseTravels.bean;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import paradiseTravels.model.Reservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayuBean {

    /**
     curl -X POST https://secure.snd.payu.com/api/v2_1/orders \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer d9a4536e-62ba-4f60-8017-6053211d3f47" \
     -d '{
     "notifyUrl": "https://your.eshop.com/notify",
     "customerIp": "127.0.0.1",
     "merchantPosId": "300746",
     "description": "RTV market",
     "currencyCode": "PLN",
     "totalAmount": "21000",
     "buyer": {
     "email": "john.doe@example.com",
     "phone": "654111654",
     "firstName": "John",
     "lastName": "Doe",
     "language": "pl"
     },
     "settings":{
     "invoiceDisabled":"true"
     },
     "products": [
     {
     "name": "Wireless Mouse for Laptop",
     "unitPrice": "15000",
     "quantity": "1"
     },
     {
     "name": "HDMI cable",
     "unitPrice": "6000",
     "quantity": "1"
     }
     ]
     }'
     */

    /** Id punktu płatności (pos_id):	339155
     Drugi klucz (MD5):	54ee0061e89bbebbf68cf10979ce9277
     Protokół OAuth - client_id :	339155
     Protokół OAuth - client_secret:	9ed63952ca644415a311e93b429c9c84 */

    public static class PayuUInitRequestObject implements Serializable {
        public String notifyUrl = "http://77.55.193.96:8080/paradiseTravels/offers/notify"; // endpoint do odsyłania statusu
        public String customerIp= "192.168.1.17";
        public String merchantPosId = "339155";
        public String description = "xxx";
        public String currencyCode = "PLN";
        public String extOrderId;// id reserwacji dzieki ktoremu mamy potwierdzenie
        public String totalAmount;
        public List<Product> products = new ArrayList<>();
    }

    public static class Product implements Serializable{
        public Product(String name, String unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public String name;
        public String unitPrice;
        public String quantity = "1";
    }


    public HttpResponse<JsonNode> initPayment(Reservation reservation) throws UnirestException {
        HttpResponse<JsonNode> auth = auth();
        if(auth.getStatus() == 200) {
            Gson gson = new Gson();
            PayuUInitRequestObject payuUInitRequestObject = new PayuUInitRequestObject();
            payuUInitRequestObject.extOrderId = reservation.getId().toString();
            payuUInitRequestObject.totalAmount = String.valueOf(reservation.getPrice()*100); // groszy
            payuUInitRequestObject.products.add(new Product(reservation.getOffer().getName(), String.valueOf(reservation.getPrice()*100)));
            String jsonBuyRequest = gson.toJson(payuUInitRequestObject);
            String access_token = auth.getBody().getObject().get("access_token").toString();
            HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("https://secure.snd.payu.com/api/v2_1/orders")
                    .header("Authorization", "Bearer " + access_token)
                    .header("Content-Type","application/json")
                    .body(jsonBuyRequest).asJson();
            return  jsonNodeHttpResponse;
        }
        return  null;
    }


    public HttpResponse<JsonNode> initPaymentTest(Reservation reservation) throws UnirestException {
         HttpResponse<JsonNode> auth = auth();
         if(auth.getStatus() == 200) {
             Gson gson = new Gson();
             PayuUInitRequestObject payuUInitRequestObject = new PayuUInitRequestObject();
             payuUInitRequestObject.totalAmount = "999"; // groszy
             payuUInitRequestObject.products.add(new Product("wycieczka", payuUInitRequestObject.totalAmount));
             String jsonBuyRequest = gson.toJson(payuUInitRequestObject);
             String access_token = auth.getBody().getObject().get("access_token").toString();
             HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("https://secure.snd.payu.com/api/v2_1/orders")
                     .header("Authorization", "Bearer " + access_token)
                     .header("Content-Type","application/json")
                     .body(jsonBuyRequest).asJson();
             return  jsonNodeHttpResponse;
         }
         return  null;
    }

    private HttpResponse<JsonNode> auth() throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("https://secure.snd.payu.com/pl/standard/user/oauth/authorize")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("grant_type=client_credentials&client_id=339155&client_secret=9ed63952ca644415a311e93b429c9c84")
                .asJson();

        return jsonNodeHttpResponse;
    }


}
