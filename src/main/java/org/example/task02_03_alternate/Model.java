package org.example.task02_03_alternate;

import java.util.Stack;

public class Model {
    Model() {}

    public double solveExpression(String expression) throws Exception{
        Stack<Double> numbers = new Stack<Double>();
        char[] tokens = expression.toCharArray();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            String numberSymbols = "0123456789.";
            String operatorsAvailable = "+-*/";
            if (numberSymbols.contains("" + tokens[i])) {
                StringBuilder sbuf = new StringBuilder();

                while (i < tokens.length && numberSymbols.contains("" + tokens[i])) {
                    sbuf.append(tokens[i++]);
                }
                try {
                    numbers.push(Double.parseDouble(sbuf.toString()));
                } catch (Exception ex) {
                    throw new Exception("Ошибка парсинга числа!");
                }
                i--;
            } else if (operatorsAvailable.contains("" + tokens[i])) {
                if (numbers.size() < 2) {
                    throw new Exception("Пустой стек!");
                }
                numbers.push(calculate(tokens[i], numbers.pop(), numbers.pop()));
            } else {
                throw new Exception("Неправильный символ!");
            }
        }
        if (numbers.size() > 1) {
            throw new Exception("Неправильное выражение!");
        }
        return numbers.pop();
    }

    public static double calculate(char op, double b, double a) throws Exception
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new Exception("Деление на ноль");
                return a / b;
        }
        return 0;
    }

    public static boolean comparePriority(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
}
