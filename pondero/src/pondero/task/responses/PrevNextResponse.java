package pondero.task.responses;

public class PrevNextResponse extends Response {

    private Boolean next;
    private Boolean prev;

    public boolean isNext() {
        return next != null && next;
    }

    public boolean isPrev() {
        return prev != null && prev;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }

    public void setPrev(final boolean prev) {
        this.prev = prev;
    }

    @Override
    public String toRecordedResponseString() {
        throw new UnsupportedOperationException("PrevNextResponse.toRecordString() - not implemented");
    }

}
