package toylangs.mash.ast;

import java.util.Arrays;
import java.util.List;

public class MashAdd extends Expression {
    private final MashNode left;
    private final MashNode right;

    public MashAdd(MashNode left, MashNode right) {
        this.left = left;
        this.right = right;
    }

    public MashNode getLeft() {
        return left;
    }

    public MashNode getRight() {
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
