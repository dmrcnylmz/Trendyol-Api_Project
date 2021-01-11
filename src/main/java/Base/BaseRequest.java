package Base;

import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest {

    public BaseRequest(){
        RestAssured.baseURI = "http://localhost/";
    }

    public static Map<String, Object> headersMapping() {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Accept", "application/json");
        return headerMap;
    }
}
