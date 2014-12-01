package pondero.task.responses;

public class PrevNextResponse extends Response {

    private boolean next;

    public boolean isNext() {
        return next;
    }

    public boolean isPrev() {
        return !next;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }

    public void setPrev(final boolean prev) {
        next = !prev;
    }

    @Override
    public String toRecordedResponseString() {
        throw new UnsupportedOperationException("PrevNextResponse.toRecordString() - not implemented");
    }

}
