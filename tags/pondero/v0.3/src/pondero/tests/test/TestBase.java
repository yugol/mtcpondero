package pondero.tests.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import pondero.tests.LockedTaskException;
import pondero.tests.elements.Element;
import pondero.tests.elements.interfaces.IsStimulus;
import pondero.tests.elements.other.Block;
import pondero.tests.elements.other.Defaults;
import pondero.tests.elements.other.Expt;
import pondero.tests.elements.other.Instruct;
import pondero.tests.elements.other.Item;
import pondero.tests.elements.other.Page;
import pondero.tests.elements.stimulus.Picture;
import pondero.tests.elements.stimulus.Text;
import pondero.tests.elements.trial.Trial;
import pondero.tests.update.Artifact;

public abstract class TestBase implements Runnable {

    private static TestBase instance;
    private static boolean  locked = false;

    public static Test instance() {
        return (Test) instance;
    }

    private Defaults                     defaults;
    private Instruct                     instruct;
    private Expt                         experiment;
    protected final Map<String, Block>   blocks   = new LinkedHashMap<String, Block>();
    protected final Map<String, Trial>   trials   = new LinkedHashMap<String, Trial>();
    protected final Map<String, Text>    texts    = new LinkedHashMap<String, Text>();
    protected final Map<String, Picture> pictures = new LinkedHashMap<String, Picture>();
    protected final Map<String, Page>    pages    = new HashMap<String, Page>();
    protected final Map<String, Item>    items    = new HashMap<String, Item>();

    public TestBase() {
        if (locked) { throw new LockedTaskException(); }
        locked = true;
        instance = this;
        try {
            script();
            if (defaults == null) {
                add(new Defaults());
            }
        } finally {
            instance = null;
            locked = false;
        }
    }

    public void add(final Element element) {
        if (Item.TYPENAME.equals(element.$typename())) {
            if (items.get(element.$name()) == null) {
                items.put(element.$name(), (Item) element);
            }
        } else if (Text.TYPENAME.equals(element.$typename())) {
            if (texts.get(element.$name()) == null) {
                texts.put(element.$name(), (Text) element);
            }
        } else if (Picture.TYPENAME.equals(element.$typename())) {
            if (pictures.get(element.$name()) == null) {
                pictures.put(element.$name(), (Picture) element);
            }
        } else if (Page.TYPENAME.equals(element.$typename())) {
            if (pages.get(element.$name()) == null) {
                pages.put(element.$name(), (Page) element);
            }
        } else if (Block.TYPENAME.equals(element.$typename())) {
            if (blocks.get(element.$name()) == null) {
                blocks.put(element.$name(), (Block) element);
            }
        } else if (Trial.TYPENAME.equals(element.$typename())) {
            if (trials.get(element.$name()) == null) {
                trials.put(element.$name(), (Trial) element);
            }
        } else if (Expt.TYPENAME.equals(element.$typename())) {
            experiment = (Expt) element;
        } else if (Instruct.TYPENAME.equals(element.$typename())) {
            instruct = (Instruct) element;
        } else if (Defaults.TYPENAME.equals(element.$typename())) {
            defaults = (Defaults) element;
        } else {
            throw new RuntimeException("Name " + element.$name() + " cannot be used for " + element.$typename());
        }
    }

    public abstract Artifact getArtifactDescriptor();

    public Block getBlock(final String name) {
        return blocks.get(name);
    }

    public String getCodeName() {
        return getArtifactDescriptor().getCodeName();
    }

    public Defaults getDefaults() {
        return defaults;
    }

    public Expt getExperiment() {
        return experiment;
    }

    public Instruct getInstructions() {
        return instruct;
    }

    public Item getItem(final String name) {
        return items.get(name);
    }

    public Page getPage(final String name) {
        return pages.get(name);
    }

    public IsStimulus getStimulus(final String name) {
        IsStimulus stimulus = getText(name);
        if (stimulus == null) {
            stimulus = pictures.get(name);
        }
        return stimulus;
    }

    public String getTestId() {
        return getArtifactDescriptor().getId();
    }

    public Text getText(final String name) {
        return texts.get(name);
    }

    public Trial getTrial(final String name) {
        return trials.get(name);
    }

    protected abstract void script();

}