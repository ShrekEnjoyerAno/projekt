import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        ImageIcon imageIcon= new ImageIcon("src/Images/ikona.png");
        ImageIcon icon= new ImageIcon("src/Images/logo.png");
        Font f=null;
        f = Font.createFont(Font.TRUETYPE_FONT,new File("src/Font/font.ttf")).deriveFont(25f);
        PlayButton playButton = new PlayButton("Play", f,0);
        OptionsButton optionsButton=new OptionsButton("Controls",f,100);
        ExitButton exitButton= new ExitButton("Quit", f,200);
        MenuPanel p= new MenuPanel();
        MenuLabel l = new MenuLabel(icon);
        MenuFrame m = new MenuFrame(imageIcon);
        p.add(l);
        m.add(playButton);
        m.add(optionsButton);
        m.add(exitButton);
        m.add(p);
        m.setVisible(true);
        m.setTitle("SpaceInvaders");
        m.pack();



        exitButton.action(m);
        playButton.action(m);
        optionsButton.action(m);

    }
}