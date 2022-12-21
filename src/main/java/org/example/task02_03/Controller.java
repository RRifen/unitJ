package org.example.task02_03;

public class Controller {
    //9           9.0
    //9)          Error
    //(2 * 3 - 1 * (2 - 1) - (0 * 2 + 2))         3.0
    //2 \ 3          Error
    //2*3+           Error
    //(2 + 4 - 5 * (4 - 2)           Error


    public static Model model = new Model();
    public static View view;
    public static String getSolveExpression(String expression) throws Exception {
        return Double.toString(model.solveExpression(expression));
    }

    public static void main(String[] args) {
        view = new View();
    }
}
