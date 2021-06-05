package toylangs.mash.ast;

import java.util.Collections;
import java.util.List;

public class MashInt extends Expression {
    private final int value;

    public MashInt(int value) {
        this.value = value;
    }

    public int getValue() {
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
