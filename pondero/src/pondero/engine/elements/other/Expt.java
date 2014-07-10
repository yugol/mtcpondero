package pondero.engine.elements.other;

import java.util.ArrayList;
import java.util.List;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.HasPostinstructions;
import pondero.engine.elements.interfaces.HasPreinstructions;
import pondero.engine.elements.interfaces.IsController;
import pondero.engine.staples.ItemSequence;
import pondero.engine.test.responses.PrevNextResponse;
import pondero.engine.test.responses.Response;

public class Expt extends Element implements HasPreinstructions, HasPostinstructions, IsController {

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

    public Expt() {
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
                    test.showInstructions(page, doStatus.currentPreIndex == 0, false);
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
                    test.showInstructions(page, doStatus.currentPostIndex == 0, false);
                    return;
                }
            }
            doEnd();
        }
    }

    @Override
    public String $typename() {
        return TYPENAME;
    }

    public void blocks(final String blocks) {
        this.blocks.clear();
        this.blocks.addAll(new ItemSequence(blocks).getItems());
    }

    @Override
    public void postinstructions(final String... pages) {
        postinstructions = pages;
    }

    @Override
    public void preinstructions(final String... pages) {
        preinstructions = pages;
    }

}
