package toylangs.mash;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import toylangs.mash.ast.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static toylangs.mash.ast.MashNode.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MashEvaluatorTest {
    private Map<String, MashNode> env;

    @Before
    public void setUp() {
        env = new HashMap<>();
        env.put("a", new IntMush(new int[]{1,2,3}));
        env.put("b", new MashInt(4));
        env.put("c", new MashInt(-2));
        env.put("foo", new MashStr("FOO"));
        env.put("bar", new MashStr("BAR"));

        env = Collections.unmodifiableMap(env);
    }

    @Test
    public void test01_literals() {
        checkEval(num(1), 1);
        checkEval(num(0), 0);
        checkEval(num(-1), -1);

        checkEval(str("kalamaja"), "kalamaja");
        checkEval(str(""), "");
        checkEval(str("S201"), "S201");

        checkEval(bool(true), "true");
        checkEval(bool(false), "false");

        checkEval(intMush(new int[]{10,11,12}), new int[]{10,11,12})
    }

    private void checkEval(MashNode node, int expected) {
        assertEquals(expected, MashEvaluator.evalInt(node, env));
    }

    private void checkEval(MashNode node, String expected) {
        assertEquals(expected, MashEvaluator.evalString(node, env));
    }

    private void checkEval(MashNode node, int[] expected) {
        assertEquals(expected, MashEvaluator.evalMush(node, env));
    }

    private void checkEvalError(MashNode node) {
        try {
            MashEvaluator.evalInt(node, env);
            fail("expected MashException");
        }
        catch (MashEvaluator.MashException ignored) {

        }
    }
}
