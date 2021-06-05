package toylangs.mash.ast;

import java.util.Arrays;
import java.util.List;

public class MushAdd extends Statement {
    private final Mush mush;
    private final Object toAdd;

    public MushAdd(Mush mush, Object toAdd) {
        this.mush = mush;
        this.toAdd = toAdd;
    }

    public Mush getMush() {
        return mush;
    }

    public Object getToAdd() {
        return toAdd;
    }

    @Override
    protected List<Object> getChildren() {
        return Arrays.asList(mush, toAdd);
    }

    @Override
    public <T> T accept(MashAstVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
