package pondero.tests.test.responses;

import java.awt.event.MouseEvent;

public class MouseClickResponse extends Response {

    private final MouseEvent evt;

    public MouseClickResponse(final MouseEvent evt) {
        this.evt = evt;
    }

    public int getButton() {
        return evt.getButton();
    }

    @Override
    public String toRecordString() {
        throw new UnsupportedOperationException("MouseClickResponse.toRecordString() - not implemented");
    }

}
