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
    int sX, sY;
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
    public void move() {
        snakeCollide();
        appleCollide(Board.apple);
        if (direction != Direction.STILL) {
            for (int i = length - 1; i > 0; i--) {
                Snake current = sn.get(i);
                Snake previous = sn.get(i - 1);
                current.sX = previous.sX;
                current.sY = previous.sY;
            }
            switch (direction) {
                case LEFT -> sX--;
                case RIGHT -> sX++;
                case UP -> sY--;
                case DOWN -> sY++;
            }
        }
        Game.canMove = true;
    }
    public void snakeCollide() {
        if (snakeCollision()) {
            switch (direction) {
                case UP, DOWN -> sY = (direction == Direction.UP) ? 0 : 14;
                case LEFT, RIGHT -> sX = (direction == Direction.LEFT) ? 0 : 16;
            }
            Game.canMove = false;
            health = Health.DEAD;
            direction = Direction.STILL;
        }
    }
    public boolean snakeCollision() {
        return switch (direction) {
            case LEFT -> this.sX < 0;
            case RIGHT -> this.sX > 16;
            case UP -> this.sY < 0;
            case DOWN -> this.sY > 14;
            case STILL -> false;
        };
    }
    public void appleCollide(Apple apple) {
        if (appleCollision(apple)) {
            new Snake(sn.getLast().sX, sn.getLast().sY, Snake.Type.TAIL, sn);
            Board.appleNew();
        }
    }
    public boolean appleCollision(Apple apple) {
        return apple.aX == sX && apple.aY == sY;
    }
}