
package RainbowReefJava;


import java.awt.*;
import java.util.ArrayList;

/**
 * @author carlos-lopez
 */
public class blackControlBar {


    private int x;
    private int y;
    private int vx;
    private int vy;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean startPressed;

    private ArrayList<Ball> ballArrayList = new ArrayList<> ();
    private Ball ball = new Ball ();
    private boolean ballToggle;
    static private Rectangle rectangle = new Rectangle ();
    public static blackControlBar oneBlackControlBar;
    Life life = new Life ();


    blackControlBar(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    private blackControlBar() {

    }

    static blackControlBar getControllerinit() {
        if (oneBlackControlBar != null)
            return oneBlackControlBar;
        oneBlackControlBar = new blackControlBar ();
        return oneBlackControlBar;

    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void toggleStartPressed(){this.startPressed = true;}

    void update() {
        if (this.UpPressed) {
            this.moveForwards ();
        }
        if (this.DownPressed) {
            this.moveBackwards ();
        }
    }

    private void moveBackwards() {
        x -= 6;
        checkBorder ();
    }

    private void moveForwards() {
        x += 6;
        checkBorder ();
    }


    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        // 170 comes from length of bar
        if (x >= GameWorld.SCREEN_WIDTH - 170) {
            x = GameWorld.SCREEN_WIDTH - 170;
        }
    }


    void drawImage(Graphics g) {
        life.render (g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor (Color.BLACK);
        g2d.fillRect (x, y, 140, 25);
        rectangle.setRect (x - 5, y, 150, 30);
        if (ballToggle) {
            for (Ball value : ballArrayList) {
                ball = value;
                ball.drawImage (g);
            }
        }

    }

    void displayBall() {
        for (int i = 0; i < ballArrayList.size (); i++) {
            ball = ballArrayList.get (i);
            if (ball.getY () < 0 || ball.getX () < 0 || ball.getY () > GameWorld.SCREEN_HEIGHT || ball.getX () > GameWorld.SCREEN_WIDTH) {
                removeBall (ball);
                life.decreaseLife ();
            }
            ball.update ();
        }
    }

    void addBall(Ball bullets) {
        life.increaseLevel ();
        ballArrayList.add (bullets);
        ballToggle = true;
    }

    void removeBall(Ball bullets) {
        life.decreaseLevel ();
        ballArrayList.remove (bullets);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Rectangle getRectangle() {
        return rectangle;
    }

    Life getLife(){return life;}

    boolean checkStart(){
        return startPressed;
    }
}
