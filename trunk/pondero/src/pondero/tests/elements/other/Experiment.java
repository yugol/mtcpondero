package pondero.tests.elements.other;

import java.util.ArrayList;
import java.util.List;
import pondero.task.responses.PrevNextResponse;
import pondero.task.responses.Response;
import pondero.tests.elements.Element;
import pondero.tests.interfaces.HasPostInstructions;
import pondero.tests.interfaces.HasPreInstructions;
import pondero.tests.interfaces.IsController;
import pondero.tests.staples.ItemSequence;

public class Experiment extends Element implements HasPreInstructions, HasPostInstructions, IsController {

    private class DoStatus {

        int currentPreIndex   = -1;
        int currentBlockIndex = 0;
        int currentPostIndex  = -1;

    }

    public static final String TYPENAME         = "expt";

    private final List<String> blocks           = new ArrayList<String>();
    private String[]           postinstructions = null;
    private String[]           preinstructions  = null;

    private DoStatus           doStatus;

    public Experiment() {
        super("");
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
                    test.showCurtains(page, doStatus.currentPreIndex == 0, false);
                    return;
                }
            }
            if (doStatus.currentBlockIndex < blocks.size()) {
                final Block block = test.getBlock(blocks.get(doStatus.currentBlockIndex++));
                block.doBegin();
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
                    test.showCurtains(page, doStatus.currentPostIndex == 0, false);
                    return;
                }
            }
            doEnd();
        }
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public String[] getPostinstructions() {
        return postinstructions;
    }

    public String[] getPreinstructions() {
        return preinstructions;
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    public void setBlocks(final String blocks) {
        this.blocks.clear();
        this.blocks.addAll(new ItemSequence(blocks).getItems());
    }

    @Override
    public void setPostInstructions(final String... pages) {
        postinstructions = pages;
    }

    @Override
    public void setPreInstructions(final String... pages) {
        preinstructions = pages;
    }

}
