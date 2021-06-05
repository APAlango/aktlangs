package toylangs.mash.ast;

import toylangs.mash.MashEvaluator;

import java.util.Arrays;
import java.util.List;

public abstract class MushOp extends Expression {
    private final Mush left;
    private final Mush right;

    public MushOp(Mush left, Mush right) {
        if (left instanceof StringMush && right instanceof StringMush ||
                left instanceof IntMush && right instanceof IntMush) {
            this.left = left;
            this.right = right;
        } else throw new MashEvaluator.MashException("Viga! Mushi töötlus operatsioone tuleb teha sama tüüpi Mushidel!");
    }

    public Mush getLeft() {
        return left;
    }

    public Mush getRight() {
        return right;
    }

    @Override
    protected List<Object> getChildren() {
        return Arrays.asList(left, right);
    }

    @Override
    public <T> T accept(MashAstVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
