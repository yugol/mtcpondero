package pondero.update;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class UpdateEngine implements PasswordReader {

    private final List<UpdateListener> listeners      = new ArrayList<UpdateListener>();
    private String                     password;
    private boolean                    cancelled;
    private PasswordReader             passwordReader = this;

    public void addListener(UpdateListener listener) {
        listeners.add(listener);
    }

    public void downloadUpdates(final Updates updates) {
        cancelled = false;
        new Thread() {

            @Override
            public void run() {
                if (updates != null && updates.size() > 0) {
                    for (UpdateListener listener : listeners) {
                        listener.updateProcessStarted(updates.size());
                    }
                    for (ArtifactDescriptor update : updates) {
                        if (!cancelled && validatePassword(update)) {
                            for (UpdateListener listener : listeners) {
                                listener.updateArtifactStarted(update);
                            }
                            try {
                                UpdateUtil.download(update);
                                for (UpdateListener listener : listeners) {
                                    listener.updateArtifactEnded(update);
                                }
                            } catch (Exception e) {
                                for (UpdateListener listener : listeners) {
                                    listener.updateArtifactFailed(update, e);
                                }
                            }
                        }
                    }
                }
                for (UpdateListener listener : listeners) {
                    listener.updateProcessEnded();
                }
            }

        }.start();
    }

    @Override
    public String readPassword(ArtifactDescriptor update) {
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf,
                // Messages.getString("lbl.enter-password-for", update.getCodeName()),
                update.getCodeName(),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (okCxl == JOptionPane.OK_OPTION) { return new String(pf.getPassword()); }
        return null;
    }

    public void readUpdates() {
        new Thread() {

            @Override
            public void run() {
                for (UpdateListener listener : listeners) {
                    listener.readRegistryStarted();
                }
                try {
                    Updates availableUpdates = UpdateUtil.getAvailableUpdates();
                    Updates applicableUpdates = UpdateUtil.getApplicableUpdates(availableUpdates);
                    for (UpdateListener listener : listeners) {
                        listener.readRegistryEnded(applicableUpdates);
                    }
                } catch (Exception e) {
                    for (UpdateListener listener : listeners) {
                        listener.readRegistryFailed(e);
                    }
                }
            }

        }.start();
    }

    public void setPasswordReader(PasswordReader passwordReader) {
        this.passwordReader = passwordReader;
    }

    private boolean validatePassword(ArtifactDescriptor update) {
        if (update.isProtected()) {
            while (!update.validatePassword(password)) {
                password = passwordReader.readPassword(update);
                if (password == null) { return false; }
            }
        }
        return true;
    }

}
