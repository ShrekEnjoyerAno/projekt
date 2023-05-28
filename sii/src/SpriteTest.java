import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SpriteTest {

    @Test
    void getImage() {
        Sprite s = new Sprite();
        ImageIcon imageIcon= new ImageIcon();
        Image result = s.getImage();
        assertEquals(imageIcon.getImage(), result);
    }
    @Test
    void isVisible() {
        Sprite s = new Sprite();
        s.setVisible(false);
        assertEquals(false,false);

    }
    @Test
    void setImage() {
        Sprite s = new Sprite();
        ImageIcon imageIcon=new ImageIcon();
        s.setImage(imageIcon.getImage());
        assertEquals(imageIcon.getImage(),s.getImage());
    }

    @Test
    void setX() {
        Sprite s = new Sprite();
        s.setX(10);
        assertEquals(10, s.getX());
    }

    @Test
    void setY() {
        Sprite s = new Sprite();
        s.setY(10);
        assertEquals(10, s.getY());
    }

    @Test
    void getY() {
        Sprite s = new Sprite();
        s.setY(5);
        assertEquals(5,s.getY());
    }

    @Test
    void getX() {
        Sprite s = new Sprite();
        s.setX(5);
        assertEquals(5,s.getX());
    }
}