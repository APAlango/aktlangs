package toylangs.mash.ast;

import toylangs.AbstractNode;

import java.util.Collections;
import java.util.List;

public class MashNeg extends Expression {
    private final MashNode expr;

    public MashNeg(MashNode expr) {
        this.expr = expr;
    }

    public MashNode getExpr() {
        return expr;
    }

    @Override
    protected List<Object> getChildren() {
        return Collections.singletonList(expr);
    }

    @Override
    public <T> T accept(MashAstVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
