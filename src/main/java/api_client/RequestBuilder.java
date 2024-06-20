package api_client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class RequestBuilder {
    private static final String URL = "https://petstore.swagger.io";

    public static RequestSpecBuilder getRequestBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setAccept(ContentType.ANY)
                .setContentType(ContentType.JSON)
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .setRelaxedHTTPSValidation();
    }
}
