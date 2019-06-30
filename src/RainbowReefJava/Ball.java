package RainbowReefJava;

import java.awt.*;

class Ball {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private Rectangle rectangle = new Rectangle ();
    private goodCollidable goodCollidable = new goodCollidable ();
    mapCreator mapCreator = RainbowReefJava.mapCreator.getSinglemapCreator ();

    Ball(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    Ball() {

    }

    void update() {
        this.moveForwards ();
    }

    private void moveForwards() {
        if (x < 20)
            vx = 4;
        if (x >= GameWorld.SCREEN_WIDTH - 100)
            vx = -4;
        if (y < 60)
            vy = 4;
        if (goodCollidable.checkCollide (blackControlBar.getControllerinit ().getRectangle (), this.getRectangle ()))
            vy = -4;
        if (goodCollidable.checkCollide (this.getRectangle ())) {
            mapCreator.setTileChange (0, Math.round (x / mapCreator.BOX_WIDTH), Math.round (y / mapCreator.BOX_HEIGHT));
            vy = 4;
        }
        x += vx;
        y += vy;
        rectangle.setRect (x, y, 24, 24);


    }

    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor (Color.white);
        g2d.fillOval (x + 30, y - 25, 24, 24);
    }

    Rectangle getRectangle() {
        return rectangle;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

}
