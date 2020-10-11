import org.testng.annotations.Test;


public class ApiTest extends BaseTest {

    @Test
    public void getListUsers() {
        get("/api/users?page=2", 200);
        validateResponceByPath("data[3].last_name", "Fields");
    }

    @Test
    public void getSingleUser() {
        get("/api/users/2", 200);
        validateResponceByPath("data.first_name", "Janet");
    }

    @Test
    public void getSingleUserNotFound() {
        get("/api/users/23", 404);
        validateResponse("{}");
    }

    @Test
    public void getListResources() {
        get("/api/unknown", 200);
        validateResponceByPath("data[4].name", "tigerlily");
    }

    @Test
    public void getSingleResources() {
        get("/api/unknown/2", 200);
        validateResponceByPath("data.name", "fuchsia rose");
    }

    @Test
    public void getSingleResourcesNotFound() {
        get("/api/unknown/23", 404);
        validateResponse("{}");
    }

    @Test
    public void postCreate() {
        post("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}", "/api/users", 201);
        validateResponceByPath("name", "morpheus");
    }

    @Test
    public void putUpdate() {
        put("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}", "/api/users/2", 200);
        validateResponceByPath("job", "zion resident");
    }

    @Test
    public void patchUpdate() {
        patchRequest("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}", "/api/users/2", 200);
        validateResponceByPath("job", "zion resident");
    }

    @Test
    public void delete() {
        deleteRequest("/api/users/2", 204);
    }

    @Test
    public void postRegisterSuccessful() {
        post("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}", "/api/register", 200);
        validateResponceByPath("token", "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void postRegisterUnsuccessful() {
        post("{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}", "/api/register", 400);
        validateResponceByPath("error", "Missing password");

    }

    @Test
    public void postLoginSuccessful() {
        post("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}", "/api/login", 200);
        validateResponceByPath("token", "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void postLoginUnsuccessful() {
        post("{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}", "/api/login", 400);
        validateResponceByPath("error", "Missing password");

    }

    @Test
    public void delayedResponse() {
        get("/api/users?page=2", 200);
        validateResponceByPath("data[1].last_name", "Ferguson");
    }


}
