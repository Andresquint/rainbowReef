package RainbowReefJava;

import java.awt.*;

class Life {
    private int life = 10;
    private int ball_positionforLife = 120;
    private int ball_positionforLevel = GameWorld.SCREEN_WIDTH - 130;
    private int level = 0;

    void decreaseLife() {
        life--;
        if (life <= 0)
            life = 0;
    }


    void increaseLevel() {
        level++;
    }

    void decreaseLevel() {
        level--;

    }

    boolean isAlive() {
        return life > 0;
    }

    void render(Graphics graphics) {
        int j = 0;
        graphics.setFont (new Font ("TimesRoman", Font.BOLD, 20));
        graphics.drawString ("Life Count:", 5, 25);
        for (int i = 0; i < life; i++) {
            graphics.fillOval (ball_positionforLife + j, 15, 10, 10);
            j += 20;
        }
        int k = 0;
        graphics.drawString ("Level:", GameWorld.SCREEN_WIDTH - 200, 25);
        for (int i = 0; i < level; i++) {
            graphics.fillOval (ball_positionforLevel + k, 15, 10, 10);
            k += 20;
        }
    }
}
