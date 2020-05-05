package open;

/**
 * Ожидаемые от сервера коды ответа
 */
public enum ResponseCode {
    OK(200),
    CREATED(201);

    public int getResponseCode() {
        return responseCode;
    }

    private int responseCode;

    ResponseCode(int request) {
        this.responseCode = request;
    }
}
