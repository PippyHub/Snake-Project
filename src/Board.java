import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements ActionListener, KeyListener {
    static final int SQR_SIZE = 30;
    static final int BOARD_WIDTH = SQR_SIZE * 24;
    static final int BOARD_HEIGHT = SQR_SIZE * 20;
    static final int MENU_HEIGHT = SQR_SIZE * 3;
    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}