import java.awt.event.KeyEvent;
public class Game {
    Board board;
    static boolean canMove;
    public Game(Board board) {
        this.board = board;
    }
    public void actionPerformed() {
        Snake head = Board.sn.getFirst();
        head.move();
        board.repaint();
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Snake head = Board.sn.getFirst();
        head.snakeCollide();
        if (canMove && head.health == Snake.Health.ALIVE) {
            switch (key) {
                case KeyEvent.VK_LEFT -> {
                    if (head.direction != Snake.Direction.STILL && head.direction != Snake.Direction.RIGHT)
                        head.direction = Snake.Direction.LEFT;
                    canMove = false;
                }
                case KeyEvent.VK_RIGHT -> {
                    if (head.direction != Snake.Direction.LEFT) head.direction = Snake.Direction.RIGHT;
                    canMove = false;
                }
                case KeyEvent.VK_UP -> {
                    if (head.direction != Snake.Direction.DOWN) head.direction = Snake.Direction.UP;
                    canMove = false;
                }
                case KeyEvent.VK_DOWN -> {
                    if (head.direction != Snake.Direction.UP) head.direction = Snake.Direction.DOWN;
                    canMove = false;
                }
            }
        }
    }
}
