package paradiseTravels.bean;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import paradiseTravels.model.Reservation;

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

    public static class PauUInitRequestObject {
        public String notifyUrl = "https://secure.snd.payu.com/api/v2_1/orders";
        public String customerIp;
        public String merchantPosId = "339155";
        public String description = "xxx";
        public String currencyCode = "PLN";
        public float totalAmount;
        public List<Product> products = new ArrayList<>();
    }

    public static class Product {
        public Product(String name, float unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public String name;
        public float unitPrice;
        public int quantity = 1;
    }

    public void initPayment(Reservation reservation) throws UnirestException {

        auth();

        PauUInitRequestObject pauUInitRequestObject = new PauUInitRequestObject();
        pauUInitRequestObject.totalAmount = 9.99f;
        pauUInitRequestObject.products.add(new Product("wycieczka", pauUInitRequestObject.totalAmount));



//        Unirest.post("https://secure.payu.com/api/v2_1/orders")
//                .body()
//                .asJson()


    }

    private void auth() throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("https://secure.snd.payu.com/pl/standard/user/oauth/authorize")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("grant_type=client_credentials&client_id=339155&client_secret=9ed63952ca644415a311e93b429c9c84")
                .asJson();

        System.out.println("xx");

    }


}
