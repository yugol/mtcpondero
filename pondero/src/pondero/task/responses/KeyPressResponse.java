package pondero.task.responses;

import java.awt.event.KeyEvent;

public class KeyPressResponse extends Response {

    private final String key;

    public KeyPressResponse(final KeyEvent evt) {
        key = String.valueOf(evt.getKeyChar());
    }

    public KeyPressResponse(final String key) {
        this.key = key;
    }

    public String getCharAsString() {
        return key;
    }

    @Override
    public String toRecordedResponseString() {
        return key;
    }

}
