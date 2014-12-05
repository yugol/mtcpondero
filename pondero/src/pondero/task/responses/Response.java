package pondero.task.responses;

public abstract class Response {

    private final long time;
    private boolean    goBack = false;

    public Response() {
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public boolean isGoBack() {
        return goBack;
    }

    public void setGoBack(final boolean goBack) {
        this.goBack = goBack;
    }

    public abstract String toRecordedResponseString();

}
