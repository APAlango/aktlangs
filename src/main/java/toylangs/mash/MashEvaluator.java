package toylangs.mash;

import toylangs.mash.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MashEvaluator {
    public static class MashException extends RuntimeException {

        public MashException(String s) {
            super(s);
        }
    }
    public static int evalInt(MashNode node, Map<String, MashNode> env) {
        return node.accept(new MashAstVisitor<>() {
            @Override
            protected Integer visit(IntMush intMush) {
                return null;
            }

            @Override
            protected Integer visit(MashBool bool) {
                return null;
            }

            @Override
            protected Integer visit(MashAdd add) {
                return visit(add.getLeft()) + visit(add.getRight());
            }

            @Override
            protected Integer visit(MashDiv div) {
                return visit(div.getLeft()) / visit(div.getRight());
            }

            @Override
            protected Integer visit(MashInt num) {
                return num.getValue();
            }

            @Override
            protected Integer visit(MashIntersect intersect) {
                return null;
            }

            @Override
            protected Integer visit(MashMul mul) {
                return visit(mul.getLeft()) * visit(mul.getRight());
            }

            @Override
            protected Integer visit(MashNeg neg) {
                return -visit(neg.getExpr());
            }

            @Override
            protected Integer visit(MashStr str) {
                return null;
            }

            @Override
            protected Integer visit(MashVar var) {
                return visit(env.get(var.getName()));
            }

            @Override
            protected Integer visit(MushAdd mushAdd) {
                return null;
            }

            @Override
            protected Integer visit(MushExcept except) {
                return null;
            }

            @Override
            protected Integer visit(MushMash mash) {
                return null;
            }

            @Override
            protected Integer visit(MushUnion union) {
                return null;
            }

            @Override
            protected Integer visit(StringMush stringMush) {
                return null;
            }
        });
    }

    public static String evalString(MashNode node, Map<String, MashNode> env) {
        return node.accept(new MashAstVisitor<>() {
            @Override
            protected String visit(IntMush intMush) {
                return null;
            }

            @Override
            protected String visit(MashAdd add) {
                return visit(add.getLeft()).concat(visit(add.getRight()));
            }

            @Override
            protected String visit(MashDiv div) {
                return null;
            }

            @Override
            protected String visit(MashInt num) {
                return "" + num.getValue();
            }

            @Override
            protected String visit(MashIntersect intersect) {
                return null;
            }

            @Override
            protected String visit(MashMul mul) {
                return null;
            }

            @Override
            protected String visit(MashNeg neg) {
                return null;
            }

            @Override
            protected String visit(MashStr str) {
                return str.getValue();
            }

            @Override
            protected String visit(MashVar var) {
                return visit(env.get(var));
            }

            @Override
            protected String visit(MushAdd mushAdd) {
                return null;
            }

            @Override
            protected String visit(MushExcept except) {
                return null;
            }

            @Override
            protected String visit(MushMash mash) {
                return null;
            }

            @Override
            protected String visit(MashBool bool) {
                return "" + bool.isValue();
            }

            @Override
            protected String visit(MushUnion union) {
                return null;
            }

            @Override
            protected String visit(StringMush stringMush) {
                String[] values = stringMush.getValues();
                return "[" + String.join(",", values) + "]";
            }
        });
    }

    public static int[] evalMush(MashNode node, Map<String, MashNode> env) {
        return node.accept(new MashAstVisitor<int[]>() {
            @Override
            protected int[] visit(IntMush intMush) {
                return intMush.getValues();
            }

            @Override
            protected int[] visit(MashAdd add) {
                return null;
            }

            @Override
            protected int[] visit(MashBool bool) {
                return null;
            }

            @Override
            protected int[] visit(MashDiv div) {
                return null;
            }

            @Override
            protected int[] visit(MashInt num) {
                return null;
            }

            @Override
            protected int[] visit(MashIntersect intersect) {
                int[] left = visit(intersect.getLeft());
                int[] right = visit(intersect.getRight());
                List<Integer> together = new ArrayList<>();
                for (int e1 : left) {
                    for (int e2 : right) {
                        if (e1 == e2 && !together.contains(e1))
                            together.add(e1);
                    }
                }
                int[] intersectVal = new int[together.size()];
                for (int i = 0; i < together.size(); i++) {
                    intersectVal[i] = together.get(i);
                }
                return intersectVal;
            }

            @Override
            protected int[] visit(MashMul mul) {
                return null;
            }

            @Override
            protected int[] visit(MashNeg neg) {
                return null;
            }

            @Override
            protected int[] visit(MashStr str) {
                return null;
            }

            @Override
            protected int[] visit(MashVar var) {
                return visit(env.get(var.getName()));
            }

            @Override
            protected int[] visit(MushAdd mushAdd) {
                return null;
            }

            @Override
            protected int[] visit(MushExcept except) {
                List<Integer> together = new ArrayList<>();
                int[] left = visit(except.getLeft());
                int[] right = visit(except.getRight());
                for (int each : left) together.add(each);
                for (int each : right) {
                    if (together.contains(each))
                        together.remove(each);
                }
                int[] exceptVal = new int[together.size()];
                for (int i = 0; i < together.size(); i++) {
                    exceptVal[i] = together.get(i);
                }
                return exceptVal;
            }

            @Override
            protected int[] visit(MushMash mash) {
                int[] left = visit(mash.getLeft());
                int[] right = visit(mash.getRight());
                int[] mushmash = new int[left.length + right.length];
                int i = 0;
                while (i < left.length)
                    mushmash[i] = left[i++];
                int j = 0;
                while (j < right.length)
                    mushmash[i++] = right[j++];
                return mushmash;
            }

            @Override
            protected int[] visit(MushUnion union) {
                int[] left = visit(union.getLeft());
                int[] right = visit(union.getRight());
                List<Integer> together = new ArrayList<>();
                for (int[] arr : List.of(left, right)) {
                    for (Integer el : arr) {
                        if (!together.contains(el))
                            together.add(el);
                    }
                }
                int[] unionVal = new int[together.size()];
                for (int i = 0; i < together.size(); i++) {
                    unionVal[i] = together.get(i);
                }
                return unionVal;
            }

            @Override
            protected int[] visit(StringMush stringMush) {
                return null;
            }
        });
    }
}
