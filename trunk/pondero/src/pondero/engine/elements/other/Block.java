package pondero.engine.elements.other;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.HasBlockfeedback;
import pondero.engine.elements.interfaces.HasFeedback;
import pondero.engine.elements.interfaces.HasPostinstructions;
import pondero.engine.elements.interfaces.HasPreinstructions;
import pondero.engine.elements.interfaces.HasScreencolor;
import pondero.engine.elements.interfaces.IsController;
import pondero.engine.elements.trial.Trial;
import pondero.engine.staples.ElementUtil;
import pondero.engine.staples.ItemSequence;
import pondero.engine.test.responses.PrevNextResponse;
import pondero.engine.test.responses.Response;

public class Block extends Element implements HasBlockfeedback, HasFeedback, HasPreinstructions, HasPostinstructions, HasScreencolor, IsController {

    private class DoStatus {

        int currentPreIndex   = -1;
        int currentTrialIndex = 0;
        int currentPostIndex  = -1;

    }

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

    private DoStatus           doStatus;

    public Block(final String name) {
        super(name);
    }

    @Override
    public void doBegin() {
        doStatus = new DoStatus();
        test.pushController(this);
    }

    @Override
    public void doEnd() throws Exception {
        doStatus = null;
        test.popController();
    }

    @Override
    public void doStep(final Response input) throws Exception {
        if (doStatus != null) {
            if (preinstructions != null) {
                if (input != null && input instanceof PrevNextResponse) {
                    final PrevNextResponse foo = (PrevNextResponse) input;
                    if (foo.isNext()) {
                        doStatus.currentPreIndex++;
                    } else if (foo.isPrev()) {
                        doStatus.currentPreIndex--;
                    }
                }
                if (doStatus.currentPreIndex < 0) {
                    doStatus.currentPreIndex = 0;
                }
                if (doStatus.currentPreIndex < preinstructions.length) {
                    final Page page = test.getPage(preinstructions[doStatus.currentPreIndex]);
                    test.showInstructions(page, doStatus.currentPreIndex == 0, false);
                    return;
                }
            }
            if (doStatus.currentTrialIndex < trials.size()) {
                final Trial trial = test.getTrial(trials.get(doStatus.currentTrialIndex++));
                trial.doBegin();
                test.doStep(null);
                return;
            }
            if (postinstructions != null) {
                if (input != null && input instanceof PrevNextResponse) {
                    final PrevNextResponse foo = (PrevNextResponse) input;
                    if (foo.isNext()) {
                        doStatus.currentPostIndex++;
                    } else if (foo.isPrev()) {
                        doStatus.currentPostIndex--;
                    }
                }
                if (doStatus.currentPostIndex < 0) {
                    doStatus.currentPostIndex = 0;
                }
                if (doStatus.currentPostIndex < postinstructions.length) {
                    final Page page = test.getPage(postinstructions[doStatus.currentPostIndex]);
                    test.showInstructions(page, doStatus.currentPostIndex == 0, false);
                    return;
                }
            }
            // TODO show block feedback
            doEnd();
        }
    }

    public List<String> _getBgstim() {
        return bgstim;
    }

    @Override
    public Set<String> _getBlockfeedback() {
        return blockfeedback;
    }

    @Override
    public FeedbackStimulus _getCorrectmessage() {
        return correctmessageFlag && correctmessage != null ? correctmessage : null;
    }

    @Override
    public FeedbackStimulus _getErrormessage() {
        return errormessageFlag && errormessage != null ? errormessage : null;
    }

    @Override
    public Color _getScreencolor() {
        return screencolor == null ? test.getDefaults()._getScreencolor() : screencolor;
    }

    @Override
    public void _setScreencolor(final Color screencolor) {
        this.screencolor = screencolor;
    }

    @Override
    public String $typename() {
        return TYPENAME;
    }

    public void bgstim(final String... bgstim) {
        for (final String name : bgstim) {
            this.bgstim.add(name);
        }
    }

    @Override
    public void blockfeedback(final String... blockfeedback) {
        ElementUtil.fillBlockfeedback(this.blockfeedback, blockfeedback);
    }

    @Override
    public void correctmessage(final boolean flag) {
        correctmessageFlag = flag;
    }

    @Override
    public void correctmessage(final String stimulusName, final long duration) {
        correctmessage = new FeedbackStimulus(stimulusName, duration);
        correctmessageFlag = true;
    }

    @Override
    public void errormessage(final boolean flag) {
        errormessageFlag = flag;
    }

    @Override
    public void errormessage(final String stimulusName, final long duration) {
        errormessage = new FeedbackStimulus(stimulusName, duration);
        errormessageFlag = true;
    }

    @Override
    public void postinstructions(final String... pages) {
        postinstructions = pages;
    }

    @Override
    public void preinstructions(final String... pages) {
        preinstructions = pages;
    }

    @Override
    public void screencolor(final int r, final int g, final int b) {
        screencolor = ElementUtil.createColor(r, g, b);
    }

    public void trials(final String trials) {
        this.trials.clear();
        this.trials.addAll(new ItemSequence(trials).getItems());
    }

}
