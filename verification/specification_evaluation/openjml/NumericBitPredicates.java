package verification.compat;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TreeScanner;
import com.sun.source.util.Trees;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCBinary;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCIdent;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.tree.JCTree.JCParens;

/** Exact integer predicate simplifications before OpenJML's ESC translation.
 * Runs after attribution/flow, preserving the checked source and all contracts.
 * Only a primitive int local/parameter read is eligible; no side effects,
 * field access, unboxing, or general bitvector arithmetic is rewritten.
 */
public final class NumericBitPredicates implements Plugin {
    public String getName() { return "NumericBitPredicates"; }

    private static JCExpression unparen(JCExpression e) {
        while (e instanceof JCParens p) e = p.expr;
        return e;
    }

    public void init(JavacTask task, String... args) {
        if (args.length != 1) throw new IllegalArgumentException("Expected the target source URI");
        Trees trees = Trees.instance(task);
        task.addTaskListener(new TaskListener() {
            public void finished(TaskEvent event) {
                if (event.getKind() != TaskEvent.Kind.ANALYZE) return;
                if (!event.getCompilationUnit().getSourceFile().toUri().toASCIIString().equals(args[0])) return;
                TreeScanner<Void, Void> scanner = new TreeScanner<Void, Void>() {
                    public Void scan(Tree tree, Void unused) {
                        // JML clauses are deliberately left untouched.
                        if (tree != null && tree.getKind() == Tree.Kind.OTHER) return null;
                        return super.scan(tree, unused);
                    }
                    public Void visitBinary(BinaryTree tree, Void unused) {
                        JCBinary eq = (JCBinary) tree;
                        if (eq.getTag() == JCTree.Tag.EQ
                                && unparen(eq.rhs) instanceof JCLiteral zero
                                && zero.typetag == TypeTag.INT && Integer.valueOf(0).equals(zero.value)
                                && unparen(eq.lhs) instanceof JCBinary bit
                                && (bit.getTag() == JCTree.Tag.BITXOR || bit.getTag() == JCTree.Tag.BITOR)
                                && unparen(bit.lhs) instanceof JCIdent id
                                && id.type != null && id.type.hasTag(TypeTag.INT)
                                && id.sym != null && id.sym.owner != null
                                && id.sym.owner.kind == com.sun.tools.javac.code.Kinds.Kind.MTH
                                && unparen(bit.rhs) instanceof JCLiteral one
                                && one.typetag == TypeTag.INT && Integer.valueOf(1).equals(one.value)) {
                            String rule;
                            if (bit.getTag() == JCTree.Tag.BITXOR) {
                                // For every 32-bit x: (x XOR 1) == 0 iff x == 1.
                                eq.lhs = bit.lhs;
                                eq.rhs = bit.rhs;
                                rule = "(int_local ^ 1) == 0 -> int_local == 1";
                            } else {
                                // For every 32-bit x: (x OR 1) has its low bit set.
                                eq.lhs = bit.rhs;
                                rule = "(int_local | 1) == 0 -> 1 == 0";
                            }
                            System.out.println("[NumericBitPredicates] offset=" + eq.pos + " rule=" + rule);
                        }
                        return super.visitBinary(tree, unused);
                    }
                };
                ClassTree declaration = trees.getTree(event.getTypeElement());
                for (Tree member : declaration.getMembers()) {
                    if (member instanceof MethodTree method) scanner.scan(method.getBody(), null);
                }
            }
        });
    }
}
