package toylangs.mash.ast;

import java.util.Collections;
import java.util.List;

public class MashStr extends Expression {
    private final String value;

    public MashStr(String value) {
        this.value = value;
    }

    public String getValue() {
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
