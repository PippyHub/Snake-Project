import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Game {
    Board board;
    public Game(Board board) {
        this.board = board;
    }
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Snake head = Board.sn.getFirst();

        switch (key) {
            case KeyEvent.VK_LEFT -> head.direction = Snake.Direction.LEFT;
            case KeyEvent.VK_RIGHT -> head.direction = Snake.Direction.RIGHT;
            case KeyEvent.VK_UP -> head.direction = Snake.Direction.UP;
            case KeyEvent.VK_DOWN -> head.direction = Snake.Direction.DOWN;
        }
    }
    public void keyReleased(KeyEvent e) {

    }
}
