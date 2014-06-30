package pondero.engine.test.responses;


public abstract class Response {

    private final long time;

    public Response() {
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

}
