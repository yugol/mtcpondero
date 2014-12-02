package pondero.tests.elements.workflow;

import java.util.ArrayList;
import java.util.List;
import pondero.tests.elements.Element;
import pondero.tests.interfaces.HasPostInstructions;
import pondero.tests.interfaces.HasPreInstructions;
import pondero.tests.staples.ItemSequence;

public class Experiment extends Element implements HasPreInstructions, HasPostInstructions {

    public static final String TYPENAME         = "expt";

    private final List<String> blocks           = new ArrayList<String>();
    private String[]           postinstructions = null;
    private String[]           preinstructions  = null;

    public Experiment() {
        super("");
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
