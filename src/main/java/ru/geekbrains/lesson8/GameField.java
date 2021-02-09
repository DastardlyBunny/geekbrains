package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameField extends JPanel {
    private static final int CELL_SIZE = 120;
    private static final int MAP_SIZE = 3;
    private static final byte DOT_HUMAN = 1;
    private static final byte DOT_AI = 2;
    private static final byte DOT_EMPTY = 0;
    private byte[][] map;
    private boolean isGameOn;

    private GameState state;

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
            state = GameState.NOBODY;
        }
        repaint();
    }

    private boolean checkWin(byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (isWinX(j, i, dotType) || isWinY(j, i, dotType) || isWinVY(j, i, dotType) || isWinVX(j, i, dotType)) {
                    isGameOn = false;
                    if (dotType == DOT_HUMAN) {
                        state = GameState.USER;
                    } else if (dotType == DOT_AI) {
                        state = GameState.AI;
                    } else {
                        state = GameState.NOBODY;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isWinX (int x, int y, byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[i][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinY (int x, int y, byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[y][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinVY (int x, int y, byte dotType) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[i][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinVX (int x, int y, byte dotType) {
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
                if (map[i][j] == 1) {
                    ((Graphics2D) g).setStroke(new BasicStroke(6));
                    g.setColor(Color.GREEN);
                    g.fillOval(i * CELL_SIZE + 10, j * CELL_SIZE + 10, CELL_SIZE - 20, CELL_SIZE - 20);
                }
                if (map[i][j] == 2) {
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
                    true
            );

            drawCenteredString(
                    g,
                    state.winnerMessage(),
                    new Rectangle(0, 0, CELL_SIZE * MAP_SIZE, CELL_SIZE * MAP_SIZE),
                    new Font("Times New Roman", Font.BOLD, 28),
                    Color.BLACK,
                    false
            );
        }
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color, Boolean isMainText) {
        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2);

        if (!isMainText) {
            y += metrics.getAscent() * 3;
        }
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }
}
