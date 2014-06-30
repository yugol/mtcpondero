package pondero.engine.test.responses;


public class PrevNextResponse extends Response {

    private boolean next;
    private boolean prev;

    public boolean isNext() {
        return next;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }

    public void setPrev(final boolean prev) {
        this.prev = prev;
    }

}
