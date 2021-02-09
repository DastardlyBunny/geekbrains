package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameField extends JPanel {
    static final int CELL_SIZE = 120;
    static final int MAP_SIZE = 3;

    static final byte DOT_HUMAN = PlayerType.HUMAN.getPlayerType();
    static final byte DOT_AI = PlayerType.AI.getPlayerType();
    static final byte DOT_EMPTY = PlayerType.NOBODY.getPlayerType();
    private static boolean isGameOn;
    private static byte[][] map;

    private PlayerType state;

    public GameField() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isGameOn) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        int cellX = e.getX() / CELL_SIZE;
                        int cellY = e.getY() / CELL_SIZE;
                        if (setDotTo(cellX, cellY, DOT_HUMAN)) {
                            checkDraw();
                            aiTurn();
                        }
                    }
                }
            }
        });
        startGame();
    }

    public void checkDraw() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return;
                }
            }
        }
        if (!checkWin(DOT_HUMAN) && !checkWin(DOT_AI)) {
            isGameOn = false;
            setState(DOT_EMPTY);
        }
        repaint();
    }

    private boolean checkWin(byte dotType) {
        if (isWinVY(dotType) || isWinVX(dotType)) {
            setState(dotType);
            return true;
        }
        for (int i = 0; i < MAP_SIZE; i++) {
            if (isWinX(i, dotType) || isWinY(i, dotType)) {
                setState(dotType);
                return true;
            }
        }

        return false;
    }

    public static void setGameOn(boolean gameOn) {
        isGameOn = gameOn;
    }

    public static byte[][] getMap() {
        return map;
    }

    private void setState(byte dotType) {
        isGameOn = false;
        if (dotType == DOT_HUMAN) {
            state = PlayerType.HUMAN;
        } else if (dotType == DOT_AI) {
            state = PlayerType.AI;
        } else {
            state = PlayerType.NOBODY;
        }
    }

    private boolean isWinX (int x, byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[i][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinY (int y, byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[y][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinVY (byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[i][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinVX (byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[i][MAP_SIZE - 1 - i] != dotType) {
                return false;
            }
        }

        return true;
    }

    public void startGame() {
        this.map = new byte[MAP_SIZE][MAP_SIZE];
        this.isGameOn = true;
        repaint();
    }

    public void aiTurn() {
        if (isGameOn) {
            int cellX, cellY;
            do {
                cellX = (int) (Math.random() * MAP_SIZE);
                cellY = (int) (Math.random() * MAP_SIZE);
            } while (!setDotTo(cellX, cellY, DOT_AI));
            repaint();
            checkDraw();
        }
    }

    private boolean setDotTo(int cellX, int cellY, byte dot) {
        if (cellX < 0 || cellY < 0 || cellX >= MAP_SIZE || cellY >= MAP_SIZE) {
            return false;
        }
        if (map[cellX][cellY] == DOT_EMPTY) {
            map[cellX][cellY] = dot;
            checkWin(dot);
            repaint();
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, MAP_SIZE * CELL_SIZE, MAP_SIZE * CELL_SIZE);
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                ((Graphics2D) g).setStroke(new BasicStroke(MAP_SIZE));
                g.setColor(Color.BLACK);
                g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                if (map[i][j] == DOT_HUMAN) {
                    ((Graphics2D) g).setStroke(new BasicStroke(6));
                    g.setColor(Color.GREEN);
                    g.drawOval(i * CELL_SIZE + 10, j * CELL_SIZE + 10, CELL_SIZE - 20, CELL_SIZE - 20);
                }
                if (map[i][j] == DOT_AI) {
                    ((Graphics2D) g).setStroke(new BasicStroke(6));
                    g.setColor(Color.RED);
                    g.drawLine(i * CELL_SIZE + 20, j * CELL_SIZE + 20, (i + 1) * CELL_SIZE - 20, (j + 1) * CELL_SIZE - 20);
                    g.drawLine(i * CELL_SIZE + 20, (j + 1) * CELL_SIZE - 20, (i + 1) * CELL_SIZE - 20, j * CELL_SIZE + 20);
                }
            }
        }
        if (!isGameOn) {
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect(0, 0, getWidth(), getHeight());

            drawCenteredString(
                    g,
                    "GAME OVER",
                    new Rectangle(0, 0, CELL_SIZE * MAP_SIZE, CELL_SIZE * MAP_SIZE),
                    new Font("Times New Roman", Font.BOLD, 48),
                    Color.BLACK,
                    0
            );

            drawCenteredString(
                    g,
                    state.getWinnerMessage(),
                    new Rectangle(0, 0, CELL_SIZE * MAP_SIZE, CELL_SIZE * MAP_SIZE),
                    new Font("Times New Roman", Font.BOLD, 28),
                    Color.BLACK,
                    3
            );
        }
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color, int ascent) {
        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent() * ascent;

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }
}
