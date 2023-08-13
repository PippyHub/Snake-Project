import java.util.LinkedList;
public class Snake {
    enum Type {
        HEAD, TAIL
    }
    enum Direction {
        UP, DOWN, LEFT, RIGHT, STILL
    }
    enum Health {
        ALIVE, DEAD
    }
    static final int SQR_SIZE = Board.SQR_SIZE;
    static final int BOARD_WIDTH = Board.BOARD_WIDTH;
    static final int BOARD_HEIGHT = Board.BOARD_HEIGHT;
    int x, y, sX, sY;
    LinkedList<Snake> sn;
    Type type;
    Direction direction = Direction.STILL;
    Health health = Health.ALIVE;
    static int length = 0;
    public Snake(int sX, int sY, Type type, LinkedList<Snake> sn) {
        this.sX = sX;
        this.sY = sY;
        this.type = type;
        this.sn = sn;
        sn.add(this);
        length++;
    }
    public void collide() {
        if (collision()) {
            System.out.println(true);
            health = Health.DEAD;
            switch (direction) {
                case UP -> sY = 0;
                case DOWN -> sY = BOARD_HEIGHT;
                case LEFT -> sX = 0;
                case RIGHT -> sX = BOARD_WIDTH;
            }
            direction = Direction.STILL;
        }
    }
    public boolean collision() {
        return switch (direction) {
            case LEFT -> this.sX < 0;
            case RIGHT -> this.sX + SQR_SIZE > BOARD_WIDTH / SQR_SIZE;
            case UP -> this.sY < 0;
            case DOWN -> this.sY + SQR_SIZE > BOARD_HEIGHT / SQR_SIZE;
            case STILL -> false;
        };
    }
}