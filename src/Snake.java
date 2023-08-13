import java.util.LinkedList;
public class Snake {
    enum Type {
        HEAD, TAIL
    }
    enum Direction {
        UP, DOWN, LEFT, RIGHT, STILL
    }
    static final int SQR_SIZE = Board.SQR_SIZE;
    int x, y, sX, sY;
    LinkedList<Snake> sn;
    Type type;
    Direction direction = Direction.STILL;
    static int length = 0;
    public Snake(int sX, int sY, Type type, LinkedList<Snake> sn) {
        this.sX = sX;
        this.sY = sY;
        this.type = type;
        this.sn = sn;
        sn.add(this);
        length++;
    }
}