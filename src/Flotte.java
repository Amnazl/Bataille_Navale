import javax.swing.*;
import java.awt.*;

public class Flotte extends JPanel {


    public void paintComponent (Graphics g){
        super.paintComponent(g);

        drawFixGrid(g,6,6,30);
    }

    public void drawFixGrid(Graphics g, int rows, int cols, int size) {
        g.setColor(Color.BLACK);
        for (int i=0;i<=rows;i++)
            g.drawLine(150, i*size, cols*size, i*size);
        for (int i=0;i<=cols;i++)
            g.drawLine(i*size, 0, i*size, rows*size);
    }


}
