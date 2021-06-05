package toylangs.mash.ast;

public abstract class MashAstVisitor<T> {
    protected abstract T visit(IntMush intMush);
    protected abstract T visit(MashAdd add);
    protected abstract T visit(MashBool bool);
    protected abstract T visit(MashDiv div);
    protected abstract T visit(MashInt num);
    protected abstract T visit(MashIntersect intersect);
    protected abstract T visit(MashMul mul);
    protected abstract T visit(MashNeg neg);
    protected abstract T visit(MashStr str);
    protected abstract T visit(MashVar var);
    protected abstract T visit(MushAdd mushAdd);
    protected abstract T visit(MushExcept except);
    protected abstract T visit(MushMash mash);
    protected abstract T visit(MushUnion union);
    protected abstract T visit(StringMush stringMush);

    public final T visit(MashNode node) {
        return node.accept(this);
    }
}
