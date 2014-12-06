package pondero.tests.elements.workflow;

import java.util.ArrayList;
import java.util.List;
import pondero.tests.elements.Element;
import pondero.tests.elements.other.Page;
import pondero.tests.interfaces.HasPostInstructions;
import pondero.tests.interfaces.HasPreInstructions;
import pondero.tests.staples.ItemSequence;

public class Experiment extends Element implements HasPreInstructions, HasPostInstructions {

    public static final String TYPENAME         = "expt";

    private String[]           preInstructions  = null;
    private final List<String> blocks           = new ArrayList<String>();
    private String[]           postInstructions = null;

    public Experiment() {
        super("");
    }

    public void addBlock(final Block block) {
        blocks.add(block.getName());
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public String[] getPostinstructions() {
        return postInstructions;
    }

    public String[] getPreinstructions() {
        return preInstructions;
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
    public void setPostInstructions(final Page... pages) {
        postInstructions = new String[pages.length];
        for (int i = 0; i < pages.length; ++i) {
            postInstructions[i] = pages[i].getName();
        }
    }

    @Override
    public void setPostInstructions(final String... pages) {
        postInstructions = pages;
    }

    @Override
    public void setPreInstructions(final Page... pages) {
        preInstructions = new String[pages.length];
        for (int i = 0; i < pages.length; ++i) {
            preInstructions[i] = pages[i].getName();
        }
    }

    @Override
    public void setPreInstructions(final String... pages) {
        preInstructions = pages;
    }

}
