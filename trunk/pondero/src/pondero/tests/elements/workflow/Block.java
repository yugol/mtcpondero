package pondero.tests.elements.workflow;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pondero.tests.elements.Element;
import pondero.tests.interfaces.HasBlockfeedback;
import pondero.tests.interfaces.HasFeedback;
import pondero.tests.interfaces.HasPostInstructions;
import pondero.tests.interfaces.HasPreInstructions;
import pondero.tests.interfaces.HasScreencolor;
import pondero.tests.staples.ElementUtil;
import pondero.tests.staples.ItemSequence;

public class Block extends Element implements HasBlockfeedback, HasFeedback, HasPreInstructions, HasPostInstructions, HasScreencolor {

    public static final String TYPENAME           = "block";

    private final Set<String>  blockfeedback      = new HashSet<String>();
    private final List<String> bgstim             = new ArrayList<String>();
    private boolean            correctmessageFlag = false;
    private FeedbackStimulus   correctmessage     = null;
    private boolean            errormessageFlag   = false;
    private FeedbackStimulus   errormessage       = null;
    private String[]           postinstructions   = null;
    private String[]           preinstructions    = null;
    private Color              screencolor        = null;
    private final List<String> trials             = new ArrayList<String>();

    public Block(final String name) {
        super(name);
    }

    public List<String> getBgstim() {
        return bgstim;
    }

    @Override
    public Set<String> getBlockFeedback() {
        return blockfeedback;
    }

    @Override
    public FeedbackStimulus getCorrectMessage() {
        return correctmessageFlag && correctmessage != null ? correctmessage : null;
    }

    @Override
    public FeedbackStimulus getErrorMessage() {
        return errormessageFlag && errormessage != null ? errormessage : null;
    }

    public String[] getPostinstructions() {
        return postinstructions;
    }

    public String[] getPreinstructions() {
        return preinstructions;
    }

    @Override
    public Color getScreenColor() {
        return screencolor == null ? test.getDefaults().getScreenColor() : screencolor;
    }

    public List<String> getTrials() {
        return trials;
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    public void setBgstim(final String... bgstim) {
        for (final String name : bgstim) {
            this.bgstim.add(name);
        }
    }

    @Override
    public void setBlockFeedback(final String... blockfeedback) {
        ElementUtil.fillBlockfeedback(this.blockfeedback, blockfeedback);
    }

    @Override
    public void setCorrectMessage(final boolean flag) {
        correctmessageFlag = flag;
    }

    @Override
    public void setCorrectMessage(final String stimulusName, final long duration) {
        correctmessage = new FeedbackStimulus(stimulusName, duration);
        correctmessageFlag = true;
    }

    @Override
    public void setErrorMessage(final boolean flag) {
        errormessageFlag = flag;
    }

    @Override
    public void setErrorMessage(final String stimulusName, final long duration) {
        errormessage = new FeedbackStimulus(stimulusName, duration);
        errormessageFlag = true;
    }

    @Override
    public void setPostInstructions(final String... pages) {
        postinstructions = pages;
    }

    @Override
    public void setPreInstructions(final String... pages) {
        preinstructions = pages;
    }

    @Override
    public void setScreenColor(final Color screencolor) {
        this.screencolor = screencolor;
    }

    @Override
    public void setScreenColor(final int r, final int g, final int b) {
        screencolor = ElementUtil.createColor(r, g, b);
    }

    public void setTrials(final String trials) {
        this.trials.clear();
        this.trials.addAll(new ItemSequence(trials).getItems());
    }

}
