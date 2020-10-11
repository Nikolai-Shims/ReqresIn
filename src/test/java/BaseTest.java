import io.restassured.response.Response;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class BaseTest {

    Response response;
    public static final String URL = "https://reqres.in";

    public Response get(String uri, int statusCode) {
        response = given()
                .when()
                .get(URL + uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response post(String body, String uri, int statusCode) {

        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body(body)
                .when()
                .post(URL + uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response put(String body, String uri, int statusCode) {
        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body(body)
                .when()
                .put(URL + uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response patchRequest(String body, String uri, int statusCode) {
        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body(body)
                .when()
                .patch(URL + uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public void deleteRequest(String uri, int statusCode) {

        given()
                .when()
                .delete(URL + uri)
                .then()
                .log().body()
                .statusCode(statusCode);

    }

    public void validateResponse(String expectedResult) {
        assertEquals(response.asString(), expectedResult);
    }

    public void validateResponceByPath(String path, String expectedResult) {
        assertEquals(response.jsonPath().getString(path), expectedResult);
    }
}
