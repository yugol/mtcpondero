package pondero.data.evaluation;

import java.io.IOException;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;

public class Profile extends PdfReport {

    public static final String  BASE_NAME = "Profile";

    protected final BasicModel  model;
    protected final Participant participant;

    public Profile(final Participant participant, final BasicModel model) throws IOException {
        this.model = model;
        this.participant = participant;
    }

    @Override
    public void generate() throws Exception {
    }

}
