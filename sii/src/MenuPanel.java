import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    final int width=750;
    final int height=750;
    public MenuPanel(){
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

}
