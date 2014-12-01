package pondero.tests.a_subliminal_priming_task;

import java.awt.Color;
import pondero.Constants;
import pondero.task.Task;
import pondero.task.launch.DefaultMonitor;
import pondero.task.launch.DefaultRenderer;
import pondero.task.launch.TaskRenderer;
import pondero.tests.Test;
import pondero.tests.elements.other.Block;
import pondero.tests.elements.other.Experiment;
import pondero.tests.elements.other.Instruct;
import pondero.tests.elements.other.Item;
import pondero.tests.elements.other.Page;
import pondero.tests.elements.stimulus.Text;
import pondero.tests.elements.trial.Trial;
import pondero.tests.interfaces.HasBlockfeedback;
import pondero.tests.management.Artifact;
import pondero.tests.management.ArtifactType;

public class ASubliminalPrimingTask extends Test {

    public static void main(final String... args) {
        final TaskRenderer renderer = new DefaultRenderer();
        final Test test = new ASubliminalPrimingTask();
        final Task task = new Task(renderer, test);
        task.addMonitor(new DefaultMonitor());
        // SwingUtilities.invokeLater(task);
        new Thread(task).start();
    }

    private static final Artifact DESCRIPTOR = new Artifact(ArtifactType.TEST, "ASPT", 0, 1, "dev");

    @Override
    public Artifact getDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    protected void script() {

        new Item("pleasant",
                "       ONOARE        ",
                "        NOROC        ",
                "       DIAMANT       ",
                "        LOIAL        ",
                "      LIBERTATE      ",
                "      CURCUBEU       ",
                "       IUBIRE        ",
                "        ONEST        ",
                "        PACE         ",
                "         RAI         ");

        new Item("unpleasant",
                "       MALITIOS      ",
                "        CANCER       ",
                "        BOAL\u0102        ",
                "       DEZASTRU      ",
                "       S\u0102R\u0102CIE       ",
                "         VOM\u0102        ",
                "        BOMB\u0102        ",
                "       PUTREGAI      ",
                "         ABUZ        ",
                "        CRIM\u0102        ");

        final Text pleasantprime = new Text("pleasantprime", getItem("pleasant"));
        pleasantprime.setTextBgColor(Color.WHITE);
        pleasantprime.setFontStyle("Courier New", 20, true);

        final Text pleasanttarget = new Text("pleasanttarget", getItem("pleasant"));
        pleasanttarget.setTextBgColor(Color.WHITE);
        pleasanttarget.setFontStyle("Courier New", 20, true);

        final Text unpleasantprime = new Text("unpleasantprime", getItem("unpleasant"));
        unpleasantprime.setTextBgColor(Color.WHITE);
        unpleasantprime.setFontStyle("Courier New", 20, true);

        final Text unpleasanttarget = new Text("unpleasanttarget", getItem("unpleasant"));
        unpleasanttarget.setTextBgColor(Color.WHITE);
        unpleasanttarget.setFontStyle("Courier New", 20, true);

        final Text forwardmask = new Text("forwardmask", "    KQHYTPDQFPBYL    ", "    PYLDQFBYTQKPH    ");
        forwardmask.setTextBgColor(Color.WHITE);
        forwardmask.setFontStyle("Courier New", 20, true);

        final Text backwardmask = new Text("backwardmask", "    PYLDQFBYTQKPH    ", "    KQHYTPDQFPBYL    ");
        backwardmask.setTextBgColor(Color.WHITE);
        backwardmask.setFontStyle("Courier New", 20, true);

        final Text pleasantreminder = new Text("pleasantreminder", "5 = pl\u0103cut");
        pleasantreminder.setTextBgColor(Color.WHITE);
        pleasantreminder.setFontStyle("Courier New", 20, true);
        pleasantreminder.setPosition("75%", "25%");

        final Text unpleasantreminder = new Text("unpleasantreminder", "a = nepl\u0103cut");
        unpleasantreminder.setTextBgColor(Color.WHITE);
        unpleasantreminder.setFontStyle("Courier New", 20, true);
        unpleasantreminder.setPosition("25%", "25%");

        final Text errormessage = new Text("errormessage", "      GRESEAL\u0102       ");
        errormessage.setTextBgColor(Color.WHITE);
        errormessage.setTextColor(Color.RED);
        errormessage.setFontStyle("Courier New", 20, true);

        final Text correctmessage = new Text("correctmessage", "       CORECT        ");
        correctmessage.setTextBgColor(Color.WHITE);
        correctmessage.setTextColor(0, 200, 0);
        correctmessage.setFontStyle("Courier New", 20, true);

        final Instruct instruct = new Instruct();
        instruct.setFontStyle("Dialog", 18, true);
        instruct.nextkey('5');
        instruct.prevkey('a');
        instruct.setScreenColor(Constants.DEFAULT_PAGE_SCREEN_COLOR);

        new Page("start", "    " + getCodeName());

        new Page("intro1",
                "    Sarcinile pe care urmeaz\u0103 s\u0103 le efectua\u021bi \u00een acest experiment implic\u0103 EVALUAREA DE CATEGORII." +
                        " Pentru fiecare item va fi afi\u0219at un stimul iar dumneavoastr\u0103 va trebui s\u0103 \u00eel \u00eencadra\u021bi \u00een una din dou\u0103 categorii." +
                        " R\u0103spunsurile de clasificare a stimulului trebuiesc date C\u00c2T MAI CUR\u00c2ND POSIBIL dar nu \u00een m\u0103sura \u00een care s\u0103 face\u021bi prea multe gre\u0219eli." +
                        " (Gre\u0219elile ocazionale sunt totu\u0219i acceptabile.)^^" +
                        "    Categoriile dintre care trebuie s\u0103 distinge\u021bi sunt reprezentate de ^^" +
                        "    cuvinte PL\u0102CUTE \u0219i NEPL\u0102CUTE^^" +
                        "    Folosi\u021bi tasta 'a' pentru a semnala un cuv\u00e2nd NEPL\u0102CUT.^^" +
                        "    Ap\u0103sa\u021bi tasta '5' pentru a indica un cuv\u00e2nt PL\u0102CUT.^^");

        new Page("intro2",
                "    Chair \u00eenaintea afi\u0219\u0103rii fiec\u0103rui cuv\u00e2nt pe care trebuie s\u0103-l categoriza\u021bi ve\u021bi vedea succed\u00e2ndu-se foarte rapid unul sau mai multe cuvinte \u0219i \u0219iruri de caractere f\u0103r\u0103 sens.^^" +
                        "    Sarcina dumneavoastr\u0103 este aceea de a IGNORA ace\u0219ti stimuli rapizi." +
                        "    R\u0103spunde-\u021bi exclusiv la ultimul cuv\u00e2nt vizibil, clar afi\u0219at \u00een fiecare item.");

        new Page("intro3",
                "    C\u00e2nd ve\u021bi ap\u0103sa tasta '5' ve\u021bi vedea un stimul la care va trebui s\u0103 r\u0103spunde\u021bi.^^" +
                        "    Un rezumat al instruc\u021biunilor de r\u0103spuns este urm\u0103torul:^^" +
                        "    Ap\u0103sa\u021bi tasta 'a' dac\u0103 stimulul este un cuv\u00e2nt NEPL\u0102CUT.^^" +
                        "    Ap\u0103sa\u021bi tasta '5' dac\u0103 stimulul este un cuv\u00e2nt PL\u0102CUT.");

        new Page("ready",
                "    La ap\u0103sarea tastei '5' va \u00eencepe un nou bloc de probe apar\u021bin\u00e2nd aceleia\u0219i sarcini.^^" +
                        "    Preg\u0103ti\u021bi-v\u0103 ca la ap\u0103sarea tastei s\u0103 apar\u0103 primul stimul.");

        new Page("end",
                "    Experimentul s-a terminat.^^" +
                        "    Dac\u0103 ave\u021bi \u00eentreb\u0103ri de orice natur\u0103 sau reac\u021bii fa\u021b\u0103 de experiment v\u0103 rug\u0103m s\u0103 le discuta\u021bi cu persoana care v-a ghidat.");

        final Trial pp = new Trial("pp");
        pp.setPreTrialPause(300);
        pp.setValidResponses("a", "5");
        pp.setCorrectResponses("5");
        pp.setStimulusFrames("1=forwardmask; 10=pleasantprime; 13=backwardmask; 14=pleasanttarget");
        pp.setPostTrialPause(100);

        final Trial pu = new Trial("pu");
        pu.setPreTrialPause(300);
        pu.setValidResponses("a", "5");
        pu.setCorrectResponses("a");
        pu.setStimulusFrames("1=forwardmask; 10=pleasantprime; 13=backwardmask; 14=unpleasanttarget");
        pu.setPostTrialPause(100);

        final Trial up = new Trial("up");
        up.setPreTrialPause(300);
        up.setValidResponses("a", "5");
        up.setCorrectResponses("5");
        up.setStimulusFrames("1=forwardmask; 10=unpleasantprime; 13=backwardmask; 14=pleasanttarget");
        up.setPostTrialPause(100);

        final Trial uu = new Trial("uu");
        uu.setPreTrialPause(300);
        uu.setValidResponses("a", "5");
        uu.setCorrectResponses("a");
        uu.setStimulusFrames("1=forwardmask; 10=unpleasantprime; 13=backwardmask; 14=unpleasanttarget");
        uu.setPostTrialPause(100);

        final Block practice = new Block("practice");
        practice.setScreenColor(175, 175, 255);
        practice.setBgstim("pleasantreminder", "unpleasantreminder");
        practice.setPreInstructions("intro1", "intro2", "intro3");
        practice.setTrials("1-5 = random(pp, pu, up, uu)");
        practice.setErrorMessage("errormessage", 200);
        practice.setCorrectMessage("correctmessage", 200);
        practice.setBlockFeedback(HasBlockfeedback.LATENCY, HasBlockfeedback.CORRECT);

        final Block data = new Block("data");
        data.setScreenColor(175, 175, 255);
        data.setBgstim("pleasantreminder", "unpleasantreminder");
        data.setPreInstructions("ready");
        data.setTrials("1-5 = random(pp, pu, up, uu)");
        data.setBlockFeedback(HasBlockfeedback.LATENCY, HasBlockfeedback.CORRECT);

        final Experiment expt = new Experiment();
        expt.setPreInstructions("start");
        expt.setBlocks("1 = practice; 2-5 = data");
        expt.setPostInstructions("end");
    }

}
