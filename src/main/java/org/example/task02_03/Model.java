package org.example.task02_03;

import java.util.Stack;

public class Model {
    Model() {}

    public double solveExpression(String expression) throws Exception{
        char[] tokens = expression.toCharArray();
        Stack<Double> numbers = new Stack<Double>();
        Stack<Character> operators = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            String numberSymbols = "0123456789.";
            if (numberSymbols.contains("" + tokens[i])) {
                StringBuilder sbuf = new StringBuilder();

                while (i < tokens.length && numberSymbols.contains("" + tokens[i]))
                    sbuf.append(tokens[i++]);
                try {
                    numbers.push(Double.parseDouble(sbuf.toString()));
                } catch (Exception ex) {
                    throw new Exception("Ошибка парсинга числа!");
                }
                i--;
            } else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                if (operators.empty()) {
                    throw new Exception("Пустой стек!");
                }
                while (operators.peek() != '(') {
                    if (numbers.size() < 2) {
                        throw new Exception("Пустой стек!");
                    }
                    numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
                    if (operators.empty()) {
                        throw new Exception("Пустой стек!");
                    }
                }
                operators.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.empty() && comparePriority(tokens[i], operators.peek())) {
                    if (numbers.size() < 2) {
                        throw new Exception("Пустой стек!");
                    }
                    numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(tokens[i]);

            }
            else {
                throw new Exception("Неправильный символ!");
            }
        }

        while (!operators.empty()) {
            if (numbers.size() < 2) {
                throw new Exception("Пустой стек!");
            }
            numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
        }
        if (numbers.size() > 1) {
            throw new Exception("В выражении есть лишние числа!");
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
