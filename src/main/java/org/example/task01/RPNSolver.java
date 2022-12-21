package org.example.task01;

import java.util.Scanner;
import java.util.Stack;

public class RPNSolver {
    public static void main(String[] args) {
        System.out.print("Введите обратную польскую нотацию: ");
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        Stack<Double> numbers = new Stack<Double>();
        char[] tokens = expression.toCharArray();
        try {
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
            System.out.println(numbers.pop());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
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
            default:
                throw new Exception("Неправильный символ!");
        }
    }
}
