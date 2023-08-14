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
    static Apple apple;
    public Board() {
        game = new Game(this);
        addKeyListener(this);
        setFocusable(true);
        snakeNew();
        apple = new Apple(12, 7);
        Timer timer = new Timer(120, this);
        timer.start();
    }
    public static void snakeNew() {
        snakeList(4,7, Snake.Type.HEAD);
        snakeList(3,7, Snake.Type.TAIL);
        snakeList(2,7, Snake.Type.TAIL);
        snakeList(1,7, Snake.Type.TAIL);
    }
    public static void snakeList(int sX, int sY, Snake.Type type) {
        new Snake(sX, sY, type, sn);
    }
    public static void appleNew() {
        boolean collision;
        do {
            collision = false;
            int aX = (int) (Math.random() * BOARD_WIDTH / SQR_SIZE);
            int aY = (int) (Math.random() * BOARD_HEIGHT / SQR_SIZE);

            for (Snake s : sn) {
                if (s.sX == aX && s.sY == aY) {
                    collision = true;
                    break;
                }
            }

            if (!collision) {
                apple = new Apple(aX, aY);
            }
        } while (collision);
    }
    @Override
    public void paint(Graphics g) {
        background(g);
        apple(g);
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
    public void apple(Graphics g) {
        int appleSize = (int) (SQR_SIZE * 0.6);
        int xOffset = (SQR_SIZE - appleSize) / 2;
        int yOffset = (SQR_SIZE - appleSize) / 2;

        g.setColor(Color.red);
        g.fillRect(apple.aX * SQR_SIZE + xOffset, apple.aY * SQR_SIZE + yOffset, appleSize, appleSize);
    }
    public void snake(Graphics g) {
        Snake head = sn.getFirst();
        int snakeSize = (int) (SQR_SIZE * 0.8);
        int xOffset = (SQR_SIZE - snakeSize) / 2;
        int yOffset = (SQR_SIZE - snakeSize) / 2;
        g.setColor(Color.blue);
        g.fillRect(head.sX * SQR_SIZE + xOffset, head.sY * SQR_SIZE + yOffset, snakeSize, snakeSize);

        snakeSize = (int) (SQR_SIZE * 0.6);
        xOffset = (SQR_SIZE - snakeSize) / 2;
        yOffset = (SQR_SIZE - snakeSize) / 2;
        for (int i = 1; i < sn.size(); i++) {
            Snake tailSegment = sn.get(i);
            g.setColor(Color.blue);
            g.fillRect(tailSegment.sX * SQR_SIZE + xOffset, tailSegment.sY * SQR_SIZE + yOffset, snakeSize, snakeSize);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        game.actionPerformed();
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