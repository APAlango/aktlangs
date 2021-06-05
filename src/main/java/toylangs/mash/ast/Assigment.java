package toylangs.mash.ast;

import java.util.Arrays;
import java.util.List;

public class Assigment extends Statement {
    private final String variableName;
    private final Expression expression;

    //TODO VariableBinding


    public Assigment(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    protected List<Object> getChildren() {
        return Arrays.asList(variableName, expression);
    }

    @Override
    public <T> T accept(MashAstVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
