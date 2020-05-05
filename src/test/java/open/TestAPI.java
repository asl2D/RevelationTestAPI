package open;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.Arrays;
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
        final String userListRootPath = "data";
        List<String> userFields = Arrays.asList("id", "email", "first_name", "last_name", "avatar");

        String jsonUserList = given().
                expect().
                statusCode(OK.getResponseCode()).
                when().
                get(GET_USER_LIST.getRequest()).
                asString();
        JsonPath jp = new JsonPath(jsonUserList);
        jp.setRootPath(userListRootPath);
        List<Map<String, String>> users = jp.get();

        for (Map user : users) {
            for (String field : userFields)
                assertNotNull(user.get(field));
        }
    }

    @Test
    public void postNewUser() {
        String userName = "neo",
                userJob = "chosen";

        User user = new User(userName, userJob);
        given().
                contentType("application/json").
                body(user).
                when().
                post(POST_NEW_USER.getRequest()).
                then().
                statusCode(CREATED.getResponseCode())
                .body("name", equalTo(userName),
                        "job", equalTo(userJob));
    }
}
