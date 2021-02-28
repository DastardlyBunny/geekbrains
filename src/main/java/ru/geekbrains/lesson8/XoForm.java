package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;

public class XoForm extends JFrame {
    private final GameField gameField;

    public XoForm() {
        this.setTitle("XO");
        this.setBounds(500, 300, GameField.MAP_SIZE * GameField.CELL_SIZE + 20, GameField.MAP_SIZE * GameField.CELL_SIZE + 80);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.gameField = new GameField();
        this.add(gameField);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        Button btnStart = new Button("Start New Game");
        Button btnExit = new Button("Exit Game");
        bottomPanel.add(btnStart);
        bottomPanel.add(btnExit);
        bottomPanel.setPreferredSize(new Dimension(1, 40));
        this.add(bottomPanel, BorderLayout.SOUTH);

        btnStart.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnStart.setBackground(Color.WHITE);
        btnExit.setBackground(Color.WHITE);

        btnStart.addActionListener(actionEvent -> gameField.startGame());

        btnExit.addActionListener(actionEvent -> System.exit(0));

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new XoForm();
    }
}
