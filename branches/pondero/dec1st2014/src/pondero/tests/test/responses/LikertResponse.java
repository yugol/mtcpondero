package pondero.tests.test.responses;

public class LikertResponse extends Response {

    private final String response;

    public LikertResponse(final String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toRecordedResponseString() {
        return response;
    }

}
