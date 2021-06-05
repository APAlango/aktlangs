package toylangs.mash.ast;

import java.util.Collections;
import java.util.List;

public class IntMush extends Mush {
    private int[] values;

    public IntMush(int[] values) {
        this.values = values;
    }

    public int[] getValues() {
        return values;
    }



    public void add(int value) {
        int[] newVals = new int[values.length+1];
        System.arraycopy(values, 0, newVals, 0, values.length);
        newVals[values.length] = value;
        this.values = newVals;
    }

    @Override
    protected List<Object> getChildren() {
        return Collections.singletonList(values);
    }

    @Override
    public <T> T accept(MashAstVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
