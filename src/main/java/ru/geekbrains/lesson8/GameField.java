package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameField extends JPanel {
    static final int CELL_SIZE = 120;
    static final int MAP_SIZE = 3;

    static final byte DOT_HUMAN = PlayerType.HUMAN.getPlayerType();
    static final byte DOT_AI = PlayerType.AI.getPlayerType();
    static final byte DOT_EMPTY = PlayerType.NOBODY.getPlayerType();

    private boolean isGameOn;

    private byte[][] map;

    private final GameState state = new GameState();

    public GameField() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isGameOn) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (humanTurn(e)) {
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
        if (!state.checkWin(map, DOT_HUMAN) && !state.checkWin(map, DOT_AI)) {
            isGameOn = false;
            state.setState(DOT_EMPTY);
        }
        repaint();
    }

    public void startGame() {
        this.map = new byte[MAP_SIZE][MAP_SIZE];
        this.isGameOn = true;
        repaint();
    }

    public void aiTurn() {
        if (isGameOn) {
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    if (map[i][j] == DOT_EMPTY) {
                        map[i][j] = DOT_AI;
                        if (state.checkWin(map, DOT_AI)) {
                            isGameOn = false;
                            state.setState(DOT_AI);
                            repaint();
                            return;
                        } else {
                            map[i][j] = DOT_HUMAN;
                            if (state.checkWin(map, DOT_HUMAN)) {
                                map[i][j] = DOT_AI;
                                repaint();
                                return;
                            } else {
                                map[i][j] = DOT_EMPTY;
                            }
                        }
                    }
                }
            }

            Random random = new Random();

            do {
                int x = random.nextInt(MAP_SIZE);
                int y = random.nextInt(MAP_SIZE);

                if (map[x][y] == DOT_EMPTY) {
                    map[x][y] = DOT_AI;
                    break;
                }
            } while (true);

            repaint();
            checkDraw();
        }
    }

    private boolean humanTurn(MouseEvent e) {
        int x = e.getX() / CELL_SIZE;
        int y = e.getY() / CELL_SIZE;

        if (x < 0 || y < 0 || x >= MAP_SIZE || y >= MAP_SIZE) {
            return false;
        }

        if (map[x][y] == DOT_EMPTY) {
            map[x][y] = DOT_HUMAN;
            if (state.checkWin(map, DOT_HUMAN)) {
                isGameOn = false;
                state.setState(DOT_HUMAN);
            }
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
                ((Graphics2D) g).setStroke(new BasicStroke(6));
                if (map[i][j] == DOT_HUMAN) {
                    g.setColor(Color.GREEN);
                    g.drawOval(i * CELL_SIZE + 10, j * CELL_SIZE + 10, CELL_SIZE - 20, CELL_SIZE - 20);
                } else if (map[i][j] == DOT_AI) {
                    g.setColor(Color.RED);
                    g.drawLine(i * CELL_SIZE + 20, j * CELL_SIZE + 20, (i + 1) * CELL_SIZE - 20, (j + 1) * CELL_SIZE - 20);
                    g.drawLine(i * CELL_SIZE + 20, (j + 1) * CELL_SIZE - 20, (i + 1) * CELL_SIZE - 20, j * CELL_SIZE + 20);
                }
            }
        }
        if (!isGameOn) {
            drawWinnerMessage(g);
        }
    }

    public void drawWinnerMessage(Graphics g) {
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

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color, int ascent) {
        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent() * ascent;

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }
}
