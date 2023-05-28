import javax.swing.*;
import java.awt.*;

public class Alien extends Sprite{
    private int height;
    private int width;
    ImageIcon icon= new ImageIcon("src/Images/alien.png");
    public Alien(int x, int y,int width,int height) {

        initAlien(x, y);
        this.width=width;
        this.height=height;
    }

    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;



    }

    public void draw(Graphics g) {//drawing aliens
        g.drawImage(icon.getImage(),x,y,width,height,Color.black,null);
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
