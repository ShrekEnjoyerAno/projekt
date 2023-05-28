import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    ImageIcon icon = new ImageIcon("src/Images/rocket.png");
    ImageIcon imageIcon = new ImageIcon("src/Images/alien.png");
    ImageIcon shot1 = new ImageIcon("src/Images/bullet.png");
    ImageIcon game= new ImageIcon("src/Images/GameOver.png");

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int width = tileSize * maxScreenCol;
    final int height = tileSize * maxScreenRow;
    int FPS = 60;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    int shotX = 300;
    int shotY = 500;
    int shotSpeed = 10;

    int playerSpeed = 3;

    int alienSpeed = 2;
    int alienReversedSpeed=alienSpeed*-1;
    boolean isShooting = false;
    Player player = new Player();
    private Shot shot = null;
    int numberOfDestroyedAliens=0;
    private ArrayList<Alien> aliens;
    private ArrayList<Alien> aliensToRemove;



    public GamePanel() {//constructor
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        aliens = new ArrayList<>();
        shot = new Shot();
        createEnemies();
        aliensToRemove = new ArrayList<>();
    }

    public void startGameThread() {//starting game
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void gameOver() {//game over method
        GameOver gameOver= new GameOver();
        JLabel label= new JLabel(game);
        gameOver.add(label);
        gameThread.stop();
    }

    @Override
    public void run() { //method run
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void update() {//updating positions of each component
        playerMoving();
        shooting();

        ArrayList<Alien> aliensToRemove = new ArrayList<>(); // list for removing aliens

        Iterator<Alien> iterator = aliens.iterator();
        while (iterator.hasNext()) {
            Alien alien = iterator.next();
            if (isShooting && checkCollision(alien, shotX, shotY)) {//destroying aliens
                aliensToRemove.add(alien);
                isShooting = false;
                numberOfDestroyedAliens++;
            }

            if (alien.getY() == player.getY()) {//game over
                gameOver();
            }
        }

        //removing aliens from the list
        aliens.removeAll(aliensToRemove);

        if (numberOfDestroyedAliens == 24) {//if aliens are destroyed they will be regenerated new ones
            if(alienSpeed<0){
                alienSpeed=alienSpeed*-1 ;
                alienSpeed++;
                createEnemies();
                numberOfDestroyedAliens = 0;
            }else{
                alienSpeed++;
                createEnemies();
                numberOfDestroyedAliens = 0;
            }
        }

        alienMoving();
    }

    private boolean checkCollision(Alien alien, int shotX, int shotY) { //collision detection
        Rectangle enemyRect = new Rectangle(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
        Rectangle shotRect = new Rectangle(shot.getX(), shot.getY(), tileSize, tileSize);
        return enemyRect.intersects(shotRect);
    }

    public void playerMoving() {//player moving
        if (keyHandler.leftPressed) {
            player.x = player.x - playerSpeed;
        } else if (keyHandler.rightPressed) {
            player.x = player.x + playerSpeed;
        }
        if (player.x <= 0) {//player cant go through window
            player.x = 0;
        } else if (player.x >= width - tileSize) {
            player.x = width - tileSize;
        }
    }

    public void alienMoving() {//moving aliens
        boolean reverseDirection = false;

        for (Alien alien : aliens) {
            alien.x += alienSpeed;
            if (alien.x <= 0 || alien.x >= width - alien.getWidth()) {//if alienX smaller than 0 or is bigger than window width aliens will change direction
                reverseDirection = true;
            }
        }
        if (reverseDirection) {// changing direction
            alienSpeed *= -1;
            for (Alien alien : aliens) {//aliens wil go down
                alien.y += 20;
            }
        }
    }


    public void shooting() {//method for shooting
        if (keyHandler.shotPressed) {
            if (!isShooting) {
                shot=new Shot();
                isShooting = true;
                shot.setX(player.x);
                shot.setY(player.y);
            }
        }
        if (isShooting) {
            shot.y -= shotSpeed;
            if (shot.y < 0) {
                isShooting = false;
            }
        }
    }

    private void createEnemies() {//creating enemies
        int startX = 0;
        int startY = 0;
        int gap = 10;
        int rows = 4;
        int cols = 6;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = startX + (tileSize + gap) * j;
                int y = startY + (tileSize + gap) * i;
                aliens.add(new Alien(x, y, tileSize, tileSize));

            }
        }
    }


    public void paintComponent (Graphics graphics){//painting method
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(icon.getImage(), player.getX(), player.getY(), tileSize, tileSize, Color.black, null);//painting player
        ArrayList<Alien> aliensCopy = new ArrayList<>(aliens);

        for (Alien alien : aliensCopy) {//painting aliens
            alien.draw(g2d);
        }


        if (isShooting) {//painting bullet
            g2d.drawImage(shot1.getImage(), shot.getX(), shot.getY(), tileSize, tileSize, null);
        }

        g2d.setColor(Color.GREEN);//painting green line on playerX
        g2d.drawLine(0, player.y + tileSize, 9999, player.y + tileSize);
        g2d.dispose();
    }

}