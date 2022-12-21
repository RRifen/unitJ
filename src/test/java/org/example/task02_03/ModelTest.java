package org.example.task02_03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model = new Model();

    @Test
    @DisplayName("Check calculations")
    void calculate() throws Exception {
        assertAll(
                () -> assertEquals(2.0, Model.calculate('+', 1.0, 1.0)),
                () -> assertEquals(0.0, Model.calculate('-', 1.0, 1.0)),
                () -> assertEquals(1.0, Model.calculate('*', 1.0, 1.0)),
                () -> assertEquals(1.0, Model.calculate('/', 1.0, 1.0))
        );
    }

    @Test
    @DisplayName("Check priority")
    void comparePriority() {
        assertAll(
                () -> assertTrue(Model.comparePriority('-', '+')),
                () -> assertTrue(Model.comparePriority('+', '*')),
                () -> assertFalse(Model.comparePriority('/', '+')),
                () -> assertTrue(Model.comparePriority('+', '/'))
        );
    }

    @Test
    @DisplayName("Check solvable expressixons")
    void solveExpression() {
        assertAll(
            () -> assertEquals(-7.0, model.solveExpression("(3 + 4)*(2 - 3)*1*1*1")),
            () -> assertEquals(0, model.solveExpression("(128.9/2*3-23)*0")),
            () -> assertEquals(-18.0, model.solveExpression("10 - (23 + 5)")),
            ()-> assertEquals(11110, model.solveExpression("(9999+1111)"))
        );
    }

    @Test
    @DisplayName("Throw expression testing")
    void exceptionSolveExpression() {
        Exception exception = assertThrows(
                Exception.class, () -> model.solveExpression("++"));
                assertEquals("Пустой стек!", exception.getMessage());
    }

    @Test
    @DisplayName("Test controller solveExpression")
    void testController() {
        assertAll(
            () -> assertEquals("-7.0", Controller.getSolveExpression("(3 + 4)*(2 - 3)*1*1*1")),
                    () -> assertEquals("0.0", Controller.getSolveExpression("(128.9/2*3-23)*0")),
                    () -> assertEquals("-18.0", Controller.getSolveExpression("10 - (23 + 5)")),
                    ()-> assertEquals("11110.0", Controller.getSolveExpression("(9999+1111)"))
        );
    }

}