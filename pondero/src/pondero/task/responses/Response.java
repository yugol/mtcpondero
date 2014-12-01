package pondero.task.responses;

public abstract class Response {

    private final long time;

    public Response() {
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public abstract String toRecordedResponseString();

}
