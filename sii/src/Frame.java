import javax.swing.*;

public class Frame extends JFrame {
    public Frame(ImageIcon image){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("SpaceInvaders");
        this.setIconImage(image.getImage());
        this.setBounds(100,100,900,700);
        this.setVisible(true);



    }
}
