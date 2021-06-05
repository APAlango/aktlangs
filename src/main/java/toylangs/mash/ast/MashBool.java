package toylangs.mash.ast;

import java.util.Collections;
import java.util.List;

public class MashBool extends Expression {
    private final boolean value;

    public MashBool(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    @Override
    protected List<Object> getChildren() {
        return Collections.singletonList(value);
    }

    @Override
    public <T> T accept(MashAstVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
