import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Board extends JPanel implements ActionListener, KeyListener {
    static final int SQR_SIZE = 30;
    static final int BOARD_WIDTH = SQR_SIZE * 17;
    static final int BOARD_HEIGHT = SQR_SIZE * 15;
    static LinkedList<Snake> sn = new LinkedList<>();
    private final Game game;
    public Board() {
        game = new Game(this);
        addKeyListener(this);
        setFocusable(true);
        snakeList(4,7, Snake.Type.HEAD);
        Timer timer = new Timer(120, this);
        timer.start();
    }
    public static void snakeList(int sX, int sY, Snake.Type type) {
        new Snake(sX, sY, type, sn);
    }
    @Override
    public void paint(Graphics g) {
        background(g);
        snake(g);
    }
    public void background(Graphics g) {
        for (int boardY = 0; boardY < BOARD_HEIGHT / SQR_SIZE; boardY++) {
            for (int boardX = 0; boardX < BOARD_WIDTH / SQR_SIZE; boardX++) {
                Color color;
                color = (boardX + boardY) % 2 == 0 ? new Color(170, 215, 80) : new Color(162, 209, 72);
                g.setColor(color);
                g.fillRect(boardX * SQR_SIZE, boardY * SQR_SIZE, SQR_SIZE, SQR_SIZE);
            }
        }
    }
    public void snake(Graphics g) {
        g.setColor(Color.blue);
        for (Snake s : sn) {
            int xOffset = (SQR_SIZE - (int) (SQR_SIZE * 0.8)) / 2;
            int yOffset = (SQR_SIZE - (int) (SQR_SIZE * 0.8)) / 2;
            int snakeSize = (int) (SQR_SIZE * 0.8);

            g.setColor(Color.blue);
            g.fillRect(s.sX * SQR_SIZE + xOffset, s.sY * SQR_SIZE + yOffset, snakeSize, snakeSize);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Snake head = sn.getFirst();
        head.collide();
        switch (head.direction) {
            case LEFT -> head.sX--;
            case RIGHT -> head.sX++;
            case UP -> head.sY--;
            case DOWN -> head.sY++;
        }
        game.canMove = true;
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}