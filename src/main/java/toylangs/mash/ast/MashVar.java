package toylangs.mash.ast;

import toylangs.AbstractNode;

import java.util.Collections;
import java.util.List;

public class MashVar extends Expression {
    private final String name;

    public MashVar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected List<? extends AbstractNode> getAbstractNodeList() {
        return Collections.singletonList(dataNode(name));
    }

    @Override
    protected List<Object> getChildren() {
        return Collections.singletonList(name);
    }

    @Override
    public <T> T accept(MashAstVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
