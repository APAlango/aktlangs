package toylangs.mash.ast;

import toylangs.AbstractNode;

import java.util.List;

public abstract class MashNode extends AbstractNode {

    public static MashAdd add(MashNode left, MashNode right) {
        return new MashAdd(left, right);
    }

    public static MashDiv div(MashNode left, MashNode right) {
        return new MashDiv(left, right);
    }

    public static MashInt num(int value) {
        return new MashInt(value);
    }

    public static MashStr str(String value) {
        return new MashStr(value);
    }

    public static IntMush intMush(int[] value) {
        return new IntMush(value);
    }

    public static StringMush strMush(String[] value) {
        return new StringMush(value);
    }

    public static MashBool bool(boolean value) {
        return new MashBool(value);
    }

    public static MashMul mul(MashNode left, MashNode right) {
        return new MashMul(left, right);
    }

    public static MashNeg neg(MashNode expr) {
        return new MashNeg(expr);
    }

    public static MashVar var(String name) {
        return new MashVar(name);
    }

    protected abstract List<Object> getChildren();

    public abstract <T> T accept(MashAstVisitor<T> visitor);
}