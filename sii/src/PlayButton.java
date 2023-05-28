import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayButton extends MenuButton {
    ImageIcon imageIcon= new ImageIcon("src/Images/ikona.png");
    public PlayButton(String text, Font font,int number) {
        super(text, font,number);

    }
    @Override
    public void action(MenuFrame m) {//creating game after play button was pressed
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m.dispose();
                GamePanel panel= new GamePanel();
                SpaceInvaders sp= new SpaceInvaders(imageIcon);
                sp.setVisible(true);
                sp.add(panel);
                sp.pack();
                panel.startGameThread();
            }
        });
    }
}
