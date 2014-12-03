package pondero.tests;

import java.util.LinkedHashMap;
import java.util.Map;
import pondero.data.drivers.pdf.PdfPageCanvas;
import pondero.data.drivers.pdf.PdfReport;
import pondero.data.evaluation.scoring.Evaluation;
import pondero.data.model.basic.TestInstance;
import pondero.tests.elements.Element;
import pondero.tests.elements.other.Defaults;
import pondero.tests.elements.other.Instruct;
import pondero.tests.elements.other.Item;
import pondero.tests.elements.other.Page;
import pondero.tests.elements.stimulus.Picture;
import pondero.tests.elements.stimulus.Sound;
import pondero.tests.elements.stimulus.Text;
import pondero.tests.elements.workflow.Block;
import pondero.tests.elements.workflow.Experiment;
import pondero.tests.elements.workflow.Trial;
import pondero.tests.interfaces.IsStimulus;
import pondero.tests.management.Artifact;
import pondero.util.TimeUtil;

public abstract class Test {

    public static Test instance() {
        return instance;
    }

    private static Test                  instance;
    private static boolean               locked   = false;

    private Defaults                     defaults;
    private Experiment                   experiment;
    private Instruct                     instruct;

    protected final Map<String, Block>   blocks   = new LinkedHashMap<String, Block>();
    protected final Map<String, Item>    items    = new LinkedHashMap<String, Item>();
    protected final Map<String, Page>    pages    = new LinkedHashMap<String, Page>();
    protected final Map<String, Picture> pictures = new LinkedHashMap<String, Picture>();
    protected final Map<String, Sound>   sounds   = new LinkedHashMap<String, Sound>();
    protected final Map<String, Text>    texts    = new LinkedHashMap<String, Text>();
    protected final Map<String, Trial>   trials   = new LinkedHashMap<String, Trial>();

    public Test() {
        if (locked) { throw new TestLockedException(); }
        locked = true;
        instance = this;
        try {
            script();
            if (defaults == null) {
                add(new Defaults());
            }
            if (instruct == null) {
                add(new Instruct());
            }
        } finally {
            instance = null;
            locked = false;
        }
    }

    public void add(final Element element) {
        switch (element.getTypeName()) {
            case Item.TYPENAME:
                if (items.get(element.getName()) == null) {
                    items.put(element.getName(), (Item) element);
                }
                break;
            case Text.TYPENAME:
                if (texts.get(element.getName()) == null) {
                    texts.put(element.getName(), (Text) element);
                }
                break;
            case Picture.TYPENAME:
                if (pictures.get(element.getName()) == null) {
                    pictures.put(element.getName(), (Picture) element);
                }
                break;
            case Sound.TYPENAME:
                if (sounds.get(element.getName()) == null) {
                    sounds.put(element.getName(), (Sound) element);
                }
                break;
            case Page.TYPENAME:
                if (pages.get(element.getName()) == null) {
                    pages.put(element.getName(), (Page) element);
                }
                break;
            case Block.TYPENAME:
                if (blocks.get(element.getName()) == null) {
                    blocks.put(element.getName(), (Block) element);
                }
                break;
            case Trial.TYPENAME:
                if (trials.get(element.getName()) == null) {
                    trials.put(element.getName(), (Trial) element);
                }
                break;
            case Experiment.TYPENAME:
                experiment = (Experiment) element;
                break;
            case Instruct.TYPENAME:
                instruct = (Instruct) element;
                break;
            case Defaults.TYPENAME:
                defaults = (Defaults) element;
                break;
            default:
                throw new RuntimeException("Name " + element.getName() + " cannot be used for " + element.getTypeName());
        }
    }

    public Block getBlock(final String name) {
        return blocks.get(name);
    }

    public Defaults getDefaults() {
        return defaults;
    }

    public abstract Artifact getDescriptor();

    public Evaluation getEvaluation(final TestInstance instance) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Experiment getExperiment() {
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
        if (stimulus == null) {
            stimulus = sounds.get(name);
        }
        return stimulus;
    }

    public Text getText(final String name) {
        return texts.get(name);
    }

    public Trial getTrial(final String name) {
        return trials.get(name);
    }

    public void renderReport(final PdfReport report, final TestInstance instance) throws Exception {
        final String title = getDescriptor().getId() + "   " + TimeUtil.toIsoTimestamp(instance.getTestTime());
        final PdfPageCanvas canvas = report.getCanvas(report.addPage());
        canvas.setFont(report.AR_B, 16);
        canvas.drawString(title, 100, 700);
        canvas.close();
    }

    protected abstract void script();

}
