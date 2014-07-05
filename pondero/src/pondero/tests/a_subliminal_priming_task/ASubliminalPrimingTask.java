package pondero.tests.a_subliminal_priming_task;

import java.awt.Color;
import pondero.engine.elements.interfaces.HasBlockfeedback;
import pondero.engine.elements.other.Block;
import pondero.engine.elements.other.Expt;
import pondero.engine.elements.other.Instruct;
import pondero.engine.elements.other.Item;
import pondero.engine.elements.other.Page;
import pondero.engine.elements.stimulus.Text;
import pondero.engine.elements.trial.Trial;
import pondero.engine.test.Test;
import pondero.update.Artifact;
import pondero.update.ArtifactType;

public class ASubliminalPrimingTask extends Test {

    private static final Artifact DESCRIPTOR = new Artifact(ArtifactType.TEST, "ASPT", 0, 1, "dev");

    public static void main(final String... args) {
        new ASubliminalPrimingTask().start(null);
    }

    @Override
    public Artifact getArtifactDescriptor() {
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
        pleasantprime._setTxbgcolor(Color.WHITE);
        pleasantprime.fontstyle("Courier New", 20, true);

        final Text pleasanttarget = new Text("pleasanttarget", getItem("pleasant"));
        pleasanttarget._setTxbgcolor(Color.WHITE);
        pleasanttarget.fontstyle("Courier New", 20, true);

        final Text unpleasantprime = new Text("unpleasantprime", getItem("unpleasant"));
        unpleasantprime._setTxbgcolor(Color.WHITE);
        unpleasantprime.fontstyle("Courier New", 20, true);

        final Text unpleasanttarget = new Text("unpleasanttarget", getItem("unpleasant"));
        unpleasanttarget._setTxbgcolor(Color.WHITE);
        unpleasanttarget.fontstyle("Courier New", 20, true);

        final Text forwardmask = new Text("forwardmask", "    KQHYTPDQFPBYL    ", "    PYLDQFBYTQKPH    ");
        forwardmask._setTxbgcolor(Color.WHITE);
        forwardmask.fontstyle("Courier New", 20, true);

        final Text backwardmask = new Text("backwardmask", "    PYLDQFBYTQKPH    ", "    KQHYTPDQFPBYL    ");
        backwardmask._setTxbgcolor(Color.WHITE);
        backwardmask.fontstyle("Courier New", 20, true);

        final Text pleasantreminder = new Text("pleasantreminder", "5 = pl\u0103cut");
        pleasantreminder._setTxbgcolor(Color.WHITE);
        pleasantreminder.fontstyle("Courier New", 20, true);
        pleasantreminder.position("75%", "25%");

        final Text unpleasantreminder = new Text("unpleasantreminder", "a = nepl\u0103cut");
        unpleasantreminder._setTxbgcolor(Color.WHITE);
        unpleasantreminder.fontstyle("Courier New", 20, true);
        unpleasantreminder.position("25%", "25%");

        final Text errormessage = new Text("errormessage", "      GRESEAL\u0102       ");
        errormessage._setTxbgcolor(Color.WHITE);
        errormessage._setTxcolor(Color.RED);
        errormessage.fontstyle("Courier New", 20, true);

        final Text correctmessage = new Text("correctmessage", "       CORECT        ");
        correctmessage._setTxbgcolor(Color.WHITE);
        correctmessage.txcolor(0, 200, 0);
        correctmessage.fontstyle("Courier New", 20, true);

        final Instruct instruct = new Instruct();
        instruct.fontstyle("Dialog", 18, true);
        instruct.nextkey('5');
        instruct.prevkey('a');
        instruct.screencolor(224, 224, 224);

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
        pp.pretrialpause(300);
        pp.validresponse("a", "5");
        pp.correctresponse("5");
        pp.stimulusframes("1=forwardmask; 10=pleasantprime; 13=backwardmask; 14=pleasanttarget");
        pp.posttrialpause(100);

        final Trial pu = new Trial("pu");
        pu.pretrialpause(300);
        pu.validresponse("a", "5");
        pu.correctresponse("a");
        pu.stimulusframes("1=forwardmask; 10=pleasantprime; 13=backwardmask; 14=unpleasanttarget");
        pu.posttrialpause(100);

        final Trial up = new Trial("up");
        up.pretrialpause(300);
        up.validresponse("a", "5");
        up.correctresponse("5");
        up.stimulusframes("1=forwardmask; 10=unpleasantprime; 13=backwardmask; 14=pleasanttarget");
        up.posttrialpause(100);

        final Trial uu = new Trial("uu");
        uu.pretrialpause(300);
        uu.validresponse("a", "5");
        uu.correctresponse("a");
        uu.stimulusframes("1=forwardmask; 10=unpleasantprime; 13=backwardmask; 14=unpleasanttarget");
        uu.posttrialpause(100);

        final Block practice = new Block("practice");
        practice.screencolor(175, 175, 255);
        practice.bgstim("pleasantreminder", "unpleasantreminder");
        practice.preinstructions("intro1", "intro2", "intro3");
        practice.trials("1-5 = random(pp, pu, up, uu)");
        practice.errormessage("errormessage", 200);
        practice.correctmessage("correctmessage", 200);
        practice.blockfeedback(HasBlockfeedback.LATENCY, HasBlockfeedback.CORRECT);

        final Block data = new Block("data");
        data.screencolor(175, 175, 255);
        data.bgstim("pleasantreminder", "unpleasantreminder");
        data.preinstructions("ready");
        data.trials("1-5 = random(pp, pu, up, uu)");
        data.blockfeedback(HasBlockfeedback.LATENCY, HasBlockfeedback.CORRECT);

        final Expt expt = new Expt();
        expt.preinstructions("start");
        expt.blocks("1 = practice; 2-5 = data");
        expt.postinstructions("end");
    }

}
