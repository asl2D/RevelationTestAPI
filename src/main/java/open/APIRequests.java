package open;

/**
 * Используемые запросы к API
 */
public enum APIRequests {
    GET_USER_LIST("https://reqres.in/api/users?page=2"),
    POST_NEW_USER("https://reqres.in/api/users");

    public String getRequest() {
        return request;
    }

    private String request;

    APIRequests(String request) {
        this.request = request;
    }
}
