package dk.dtu.compute.course02324.mini_java;

import dk.dtu.compute.course02324.mini_java.model.*;
import dk.dtu.compute.course02324.mini_java.semantics.ProgramExecutorVisitor;
import dk.dtu.compute.course02324.mini_java.semantics.ProgramTypeVisitor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dk.dtu.compute.course02324.mini_java.model.Operator.*;
import static dk.dtu.compute.course02324.mini_java.utils.Shortcuts.FLOAT;
import static dk.dtu.compute.course02324.mini_java.utils.Shortcuts.INT;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramVisitorTest {


    private ProgramTypeVisitor typeCheck(Statement program) {
        ProgramTypeVisitor ptv = new ProgramTypeVisitor();
        ptv.visit(program);
        return ptv;
    }

    private ProgramExecutorVisitor execute(Statement program, ProgramTypeVisitor ptv) {
        ProgramExecutorVisitor pev = new ProgramExecutorVisitor(ptv);
        pev.visit(program);
        return pev;
    }

    @Test
    void testUnaryPlusInt() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x,
                        new OperatorExpression(PLUS1, new IntLiteral(5)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(5, pev.values.get(x).intValue());
    }

    @Test
    void testUnaryPlusFloat() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(FLOAT, x,
                        new OperatorExpression(PLUS1, new FloatLiteral(2.5f)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(2.5f, pev.values.get(x).floatValue(), 0.0001f);
    }

    @Test
    void testUnaryMinusInt() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x,
                        new OperatorExpression(MINUS1, new IntLiteral(5)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(-5, pev.values.get(x).intValue());
    }

    @Test
    void testUnaryMinusFloat() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(FLOAT, x,
                        new OperatorExpression(MINUS1, new FloatLiteral(2.5f)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(-2.5f, pev.values.get(x).floatValue(), 0.0001f);
    }

    @Test
    void testBinaryMinusInt() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x,
                        new OperatorExpression(MINUS2, new IntLiteral(9), new IntLiteral(4)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(5, pev.values.get(x).intValue());
    }

    @Test
    void testBinaryMinusFloat() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(FLOAT, x,
                        new OperatorExpression(MINUS2, new FloatLiteral(9.5f), new FloatLiteral(4.0f)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(5.5f, pev.values.get(x).floatValue(), 0.0001f);
    }

    @Test
    void testMultiplyInt() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x,
                        new OperatorExpression(MULT, new IntLiteral(3), new IntLiteral(4)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(12, pev.values.get(x).intValue());
    }

    @Test
    void testMultiplyFloat() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(FLOAT, x,
                        new OperatorExpression(MULT, new FloatLiteral(1.5f), new FloatLiteral(2.0f)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(3.0f, pev.values.get(x).floatValue(), 0.0001f);
    }

    @Test
    void testDivisionInt() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x,
                        new OperatorExpression(DIV, new IntLiteral(8), new IntLiteral(2)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(4, pev.values.get(x).intValue());
    }

    @Test
    void testDivisionFloat() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(FLOAT, x,
                        new OperatorExpression(DIV, new FloatLiteral(7.5f), new FloatLiteral(2.5f)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(3.0f, pev.values.get(x).floatValue(), 0.0001f);
    }

    @Test
    void testModInt() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x,
                        new OperatorExpression(MOD, new IntLiteral(10), new IntLiteral(3)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(1, pev.values.get(x).intValue());
    }

    @Test
    void testModFloat() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(FLOAT, x,
                        new OperatorExpression(MOD, new FloatLiteral(7.5f), new FloatLiteral(2.0f)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(1.5f, pev.values.get(x).floatValue(), 0.0001f);
    }

    @Test
    void testPrintStatement() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x, new IntLiteral(7)),
                new PrintStatement("x = ", x)
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(out));

        try {
            execute(program, ptv);
        } finally {
            System.setOut(oldOut);
        }

        assertTrue(out.toString().contains("x = 7"));
    }

    @Test
    void testPrintStatementWithExpression() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x, new IntLiteral(7)),
                new PrintStatement("x = ", x)
        );

        ProgramTypeVisitor ptv = new ProgramTypeVisitor();
        ptv.visit(program);
        assertTrue(ptv.problems.isEmpty());
    }

    @Test
    void testAssignmentUpdatesVariable() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x, new IntLiteral(1)),
                new Assignment(x, new IntLiteral(9))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(9, pev.values.get(x).intValue());
    }

    @Test
    void testWhileLoopRuns() {
        Var i = new Var("i");

        Statement program = new Sequence(
                new Declaration(INT, i, new IntLiteral(3)),
                new WhileLoop(
                        i,
                        new Assignment(i,
                                new OperatorExpression(MINUS2, i, new IntLiteral(1)))
                )
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertTrue(ptv.problems.isEmpty());

        ProgramExecutorVisitor pev = execute(program, ptv);
        assertEquals(-1, pev.values.get(i).intValue());
    }

    @Test
    void testWhileLoopBodyIsChecked() {
        Var i = new Var("i");
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, i, new IntLiteral(1)),
                new WhileLoop(
                        i,
                        new Assignment(x, new IntLiteral(5))
                )
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertFalse(ptv.problems.isEmpty());
    }

    @Test
    void testWhileLoopWrongType() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(FLOAT, x, new FloatLiteral(1.5f)),
                new WhileLoop(
                        x,
                        new Assignment(x,
                                new OperatorExpression(MINUS2, x, new FloatLiteral(1.0f)))
                )
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertFalse(ptv.problems.isEmpty());
    }

    @Test
    void testVariableDeclaredTwice() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x, new IntLiteral(1)),
                new Declaration(INT, x, new IntLiteral(2))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertFalse(ptv.problems.isEmpty());
    }

    @Test
    void testUndeclaredVariableAssignment() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Assignment(x, new IntLiteral(1))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertFalse(ptv.problems.isEmpty());
    }

    @Test
    void testDeclarationTypeMismatch() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x, new FloatLiteral(1.2f))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertFalse(ptv.problems.isEmpty());
    }

    @Test
    void testAssignmentTypeMismatch() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x, new IntLiteral(1)),
                new Assignment(x, new FloatLiteral(2.0f))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertFalse(ptv.problems.isEmpty());
    }

    @Test
    void testOperatorOperandTypeMismatch() {
        Var x = new Var("x");

        Statement program = new Sequence(
                new Declaration(INT, x,
                        new OperatorExpression(PLUS2, new IntLiteral(1), new FloatLiteral(2.0f)))
        );

        ProgramTypeVisitor ptv = typeCheck(program);
        assertFalse(ptv.problems.isEmpty());
    }

    @Test
    public void test2LoopProgram() {
        int i = 5;
        int j = 0;
        int sum = 0;

        while (i + 0 >= 0) {
            j = i;
            while (j >= 0) {
                sum = sum + j;
                j = j - 1;
            }
            i = i - 1;
        }

        Var iVar = new Var("i");
        Var jVar = new Var("j");
        Var sumVar = new Var("sum");

        ProgramTypeVisitor ptv = new ProgramTypeVisitor();
        ProgramExecutorVisitor pev = new ProgramExecutorVisitor(ptv);

        Statement statement = new Sequence(
                new Declaration(INT, iVar, new IntLiteral(5)),
                new Declaration(INT, sumVar, new IntLiteral(0)),
                new WhileLoop(
                        new OperatorExpression(PLUS2, iVar, new IntLiteral(0)),
                        new Sequence(
                                new Declaration(INT, jVar, iVar),
                                new WhileLoop(
                                        jVar,
                                        new Sequence(
                                                new Assignment(
                                                        sumVar,
                                                        new OperatorExpression(PLUS2, sumVar, jVar)
                                                ),
                                                new Assignment(
                                                        jVar,
                                                        new OperatorExpression(MINUS2, jVar, new IntLiteral(1))
                                                ),
                                                new PrintStatement(" i: ", iVar),
                                                new PrintStatement(" j: ", jVar)
                                        )
                                ),
                                new Assignment(
                                        iVar,
                                        new OperatorExpression(MINUS2, iVar, new IntLiteral(1))
                                )
                        )
                )
        );

        ptv.visit(statement);
        assertTrue(ptv.problems.isEmpty(),
                "The type visitor detected typing problems, which should not be there!");

        pev.visit(statement);

        assertEquals(i, pev.values.get(iVar), "Value of variable i should be " + i + ".");
        assertEquals(j, pev.values.get(jVar), "Value of variable j should be " + j + ".");
        assertEquals(sum, pev.values.get(sumVar), "Value of variable sum should be " + sum + ".");
    }
}