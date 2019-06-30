/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RainbowReefJava;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * @author carlos-lopez
 */
public class UserControl implements KeyListener {

    private blackControlBar controlBar;
    private final int up;
    private final int down;
    private final int shoot;
    private  int startGame;

    UserControl(blackControlBar t1, int up, int down, int shoot, int start) {
        this.controlBar = t1;
        this.up = up;
        this.down = down;
        this.shoot = shoot;
        this.startGame = start;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode ();
        if (keyPressed == up) {
            this.controlBar.toggleUpPressed ();
        }
        if (keyPressed == down) {
            this.controlBar.toggleDownPressed ();
        }
        if (keyPressed == shoot) {
            controlBar.addBall (new Ball (controlBar.getX (), controlBar.getY (), -5, 5));

        }
        if(keyPressed == startGame){
            this.controlBar.toggleStartPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode ();
        if (keyReleased == up) {
            this.controlBar.unToggleUpPressed ();
        }
        if (keyReleased == down) {
            this.controlBar.unToggleDownPressed ();
        }
    }
}
