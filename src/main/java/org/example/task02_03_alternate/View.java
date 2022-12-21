package org.example.task02_03_alternate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    JTextArea textArea;
    JTextArea textAreaResult;

    View() {
        super("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 400);

        JPanel panelButtons = new JPanel(new GridLayout(5, 4, 10, 10));
        String[] buttonsLabel = {"C", ".", "Backspace", "=", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "(", "0", ")", "+"};
        JButton[] buttons = new JButton[buttonsLabel.length];
        for (int i = 0; i < buttonsLabel.length; i++) {
            buttons[i] = new JButton(buttonsLabel[i]);
            if (i == 0) {
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textArea.setText("");
                    }
                });
            } else if (i == 2) {
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = textArea.getText();
                        if (text.length() > 0) {
                            textArea.setText(text.substring(0, text.length() - 1));
                        }
                    }
                });
            } else if (i == 3) {
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String expression = textArea.getText().trim();
                            String result = Controller.getSolveExpression(expression);
                            textAreaResult.setText(expression + " = " + result + "\n" + textAreaResult.getText());
                        } catch (Exception ex) {
                            textAreaResult.setText(ex.getMessage() + "\n" + textAreaResult.getText());
                        }

                    }
                });
            } else {
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textArea.append(((JButton) e.getSource()).getText());
                    }
                });
            }
            panelButtons.add(buttons[i]);
        }

        JPanel leftSide = new JPanel();
        leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
        leftSide.add(Box.createVerticalStrut(10));

        textArea = new JTextArea(3, 20);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        leftSide.add(textArea);
        leftSide.add(panelButtons);
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(leftSide);
        textAreaResult = new JTextArea(10, 20);
        textAreaResult.setEditable(false);
        textAreaResult.setFont(new Font("Arial", Font.PLAIN, 14));
        add(textAreaResult, BorderLayout.EAST);
        setResizable(false);
        setVisible(true);
    }
}