package toylangs.mash.ast;

import java.util.Collections;
import java.util.List;

public class StringMush extends Mush {
    private String[] values;

    public StringMush(String[] values) {
        this.values = values;
    }

    public String[] getValues() {
        return values;
    }

    public void add(String value) {
        String[] newVals = new String[values.length+1];
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
