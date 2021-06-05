//package toylangs.mash;
//
//import org.antlr.v4.runtime.BailErrorStrategy;
//import org.antlr.v4.runtime.CharStreams;
//import org.antlr.v4.runtime.CommonTokenStream;
//import org.antlr.v4.runtime.tree.ParseTree;
//import toylangs.mash.ast.MashAstVisitor;
//import toylangs.mash.ast.MashNode;
//import utils.ExceptionErrorListener;
//
//import java.util.ArrayList;
//
//public class MashAst {
//
//    public static void main(String[] args) {
//        String input = "let i = 1;";
//        MashNode actualAst = MashAst.makeMashAst(input);
//        int iii = 1;
//    }
//
//    public static MashNode makeMashAst(String input) {
//        MashLexer lexer = new MashLexer(CharStreams.fromString(input));
//        lexer.removeErrorListeners();
//        lexer.addErrorListener(new ExceptionErrorListener());
//
//        MashParser parser = new MashParser(new CommonTokenStream(lexer));
//        parser.removeErrorListeners();
//        parser.setErrorHandler(new BailErrorStrategy());
//
//        ParseTree tree = parser.init();
//        return parseTreeToAst(tree);
//    }
//
//    private static MashNode parseTreeToAst(ParseTree tree) {
//        MashAstStatementVisitor statementVisitor = new MashAstStatementVisitor();
//        return statementVisitor.visit(tree);
//    }
//
//    private static class MashAstExpressionVisitor extends MashBaseVisitor<MashParser.ExpressionContext> {
//        @Override
//        public MashParser.ExpressionContext visitVariable(MashParser.VariableContext ctx) {
//            return super.visitVariable(ctx);
//        }
//
//        @Override
//        public MashParser.ExpressionContext visitIntLiteral(MashParser.IntLiteralContext ctx) {
//            return super.visitIntLiteral(ctx);
//        }
//
//        @Override
//        public MashParser.ExpressionContext visitStrLiteral(MashParser.StrLiteralContext ctx) {
//            return super.visitStrLiteral(ctx);
//        }
//
//        @Override
//        public MashParser.ExpressionContext visitBoolLiteral(MashParser.BoolLiteralContext ctx) {
//            return super.visitBoolLiteral(ctx);
//        }
//    }
//
//    private static class MashAstStatementVisitor extends MashBaseVisitor<MashParser.StatementsContext> {
//        private final MashAstExpressionVisitor expressionVisitor = new MashAstExpressionVisitor();
//    }
//}
