import javax.swing.*;
import java.awt.*;
public class Panel extends JFrame {
    static final int BOARD_WIDTH = Board.BOARD_WIDTH;
    static final int BOARD_HEIGHT = Board.BOARD_HEIGHT;
    public static Board panel = new Board();

    public Panel() {
        setTitle("Snake");
        this.getContentPane().setPreferredSize(new Dimension(BOARD_WIDTH,  BOARD_HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(panel);
        panel.requestFocus();

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}