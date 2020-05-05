package open;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static open.APIRequests.GET_USER_LIST;
import static open.APIRequests.POST_NEW_USER;
import static open.ResponseCode.CREATED;
import static open.ResponseCode.OK;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertNotNull;

public class TestAPI {

    @Test
    public void getUserList() {
        final String USER_LIST_ROOT_PATH = "data";
        String jsonUserList = given().
                expect().
                statusCode(OK.getResponseCode()).
                when().
                get(GET_USER_LIST.getRequest()).
                asString();
        JsonPath jp = new JsonPath(jsonUserList);
        jp.setRootPath(USER_LIST_ROOT_PATH);
        List<Map<String, String>> users = jp.get();

        for (Map user : users) {
            assertNotNull(user.get("id"));
            assertNotNull(user.get("email"));
            assertNotNull(user.get("first_name"));
            assertNotNull(user.get("last_name"));
            assertNotNull(user.get("avatar"));
        }
    }

    @Test
    public void postNewUser() {
        User user = new User(TestData.getUserName(), TestData.getUserJob());
        given().
                contentType("application/json").
                body(user).
                when().
                post(POST_NEW_USER.getRequest()).
                then().
                statusCode(CREATED.getResponseCode())
                .body("name", equalTo(user.getName()),
                        "job", equalTo(user.getJob()));
    }
}
