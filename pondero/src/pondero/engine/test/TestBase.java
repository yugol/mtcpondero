package pondero.engine.test;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import pondero.engine.LockedTaskException;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.IsStimulus;
import pondero.engine.elements.other.Block;
import pondero.engine.elements.other.Defaults;
import pondero.engine.elements.other.Expt;
import pondero.engine.elements.other.Instruct;
import pondero.engine.elements.other.Item;
import pondero.engine.elements.other.Page;
import pondero.engine.elements.stimulus.Picture;
import pondero.engine.elements.stimulus.Text;
import pondero.engine.elements.trial.Trial;
import pondero.engine.test.launch.DefaultLauncher;
import pondero.engine.test.launch.TaskLauncher;
import pondero.model.Workbook;
import pondero.model.entities.Participant;
import pondero.model.entities.TrialRecord;
import pondero.update.ArtifactDescriptor;

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

    private TaskLauncher                 launcher = new DefaultLauncher();
    private long                         startTime;
    private long                         stopTime;

    protected Participant                participant;
    protected TrialRecord                record;
    protected Workbook                   workbook;

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

    public abstract ArtifactDescriptor getArtifactDescriptor();

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

    public String getId() {
        return getArtifactDescriptor().getId();
    }

    public Instruct getInstructions() {
        return instruct;
    }

    public Item getItem(final String name) {
        return items.get(name);
    }

    public TaskLauncher getLauncher() {
        return launcher;
    }

    public Page getPage(final String name) {
        return pages.get(name);
    }

    public long getStartTime() {
        return startTime;
    }

    public IsStimulus getStimulus(final String name) {
        IsStimulus stimulus = getText(name);
        if (stimulus == null) {
            stimulus = pictures.get(name);
        }
        return stimulus;
    }

    public long getStopTime() {
        return stopTime;
    }

    public Text getText(final String name) {
        return texts.get(name);
    }

    public Trial getTrial(final String name) {
        return trials.get(name);
    }

    public void setLauncher(final TaskLauncher launcher) {
        this.launcher = launcher;
    }

    public void setParticipant(final Participant currentParticipant) {
        participant = currentParticipant;
    }

    public void setWorkbook(final Workbook currentWorkbook) {
        workbook = currentWorkbook;
    }

    public void start() {
        EventQueue.invokeLater(this);
    }

    protected long getTaskTime() {
        return System.currentTimeMillis() - startTime;
    }

    protected abstract void script();

    protected void startTimer() {
        startTime = System.currentTimeMillis();
        System.out.println("Test started at " + startTime);
    }

    protected void stopTimer() {
        stopTime = System.currentTimeMillis();
        System.out.println("Test ended at " + stopTime);
    }

}
