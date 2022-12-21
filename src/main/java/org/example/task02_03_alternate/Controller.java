package org.example.task02_03_alternate;

public class Controller {

    //2+3                       Error
    //13 48                     Error
    //2 3 4 5 6 * + - /         -0.06451612903225806
    //(23)*                     Error
    //A B C D E F G H I J K L   Error
    //2 3*-                     Error
    //2 3*4 5*+                 26.0



    public static Model model = new Model();
    public static View view;
    public static String getSolveExpression(String expression) throws Exception {
        return Double.toString(model.solveExpression(expression));
    }

    public static void main(String[] args) {
        view = new View();
    }
}
