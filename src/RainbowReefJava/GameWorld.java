/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RainbowReefJava;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;


/**
 * @author carlos-lopez
 */
public class GameWorld extends JPanel {


    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    private BufferedImage world;
    private JFrame jf;
    private blackControlBar blackControlBar;
    private mapCreator mapCreator_factory = mapCreator.getSinglemapCreator ();
    private JPanel makejPanel;
    static long duration;
    private Image startSplash, endSplash, startButton, enterImage;


    public static void main(String[] args) {
        final long startTime = System.nanoTime ();
//        Thread x;
        GameWorld trex = new GameWorld ();
        trex.init ();
        try {
            while (true) {
                duration = System.nanoTime () - startTime;
                duration = duration / 1000000000;
                trex.blackControlBar.update ();
                trex.repaint ();
                sleep (1000 / 144);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void init() {
        this.jf = new JFrame ("Rainbow blackControlBar");
        blackControlBar = new blackControlBar ((SCREEN_WIDTH / 2) + (SCREEN_WIDTH / 4), SCREEN_HEIGHT - 70, 0, 0);
        UserControl Controller = new UserControl (blackControlBar, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_NUMPAD0, KeyEvent.VK_ENTER);

        this.jf.setLayout (new BorderLayout ());
        this.jf.add (this);

        this.jf.addKeyListener (Controller);

        this.jf.setSize (GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT + 30);
        this.jf.setResizable (false);
        jf.setLocationRelativeTo (null);

        this.jf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible (true);

        // creates split screen
        makejPanel = mapCreator_factory.create_jPanelSplit_Factory (jf);

        //  splash screens
        startSplash = mapCreator_factory.readImage ("rsc/introsplash.bmp");
        startSplash = startSplash.getScaledInstance (GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, 0);

        endSplash = mapCreator_factory.readImage ("rsc/rainbowreefspash.png");
        endSplash = endSplash.getScaledInstance (600, 800, 0);

        startButton = mapCreator_factory.readImage ("rsc/Button_start.gif");
        startButton = startButton.getScaledInstance (startButton.getWidth (null) * 2, startButton.getHeight (null) * 2, 0);

        enterImage = mapCreator_factory.readImage ("rsc/pushing_enterButton.jpg");
        enterImage = enterImage.getScaledInstance (enterImage.getWidth (null) / 3, enterImage.getHeight (null) / 3, 0);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent (g2);

        if (!blackControlBar.checkStart ()) {
            g2.drawImage (startSplash, 0, 0, null);
            g2.drawImage (startButton, 115, 170, null);
            g2.drawImage (enterImage, 110, 20, null);

            g2.setColor (Color.BLACK);
            g2.setFont (new Font ("TimesRoman", Font.BOLD, 40));
            g2.drawString ("push", 10, 150);
            g2.drawString ("to", 70, 230);
            g2.drawString (":)", 315, 220);

//            JButton startJButton = new JButton ("YES");
//            startJButton.addActionListener (new ActionListener () {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.out.println ("button pushed");
//                }
//            });
//            startJButton.setPreferredSize (new Dimension (200, 200));
//            jf.add (startJButton);
////            jf.setContentPane (startJButton);
////            jf.pack ();
//            jf.setVisible (true);

        } else if (!blackControlBar.getLife ().isAlive ()) {
            g2.setColor (Color.BLACK);
            g2.fillRect (0, 0, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
            g2.drawImage (endSplash, 300, 0, null);
        } else {
            world = mapCreator_factory.imageCreator (makejPanel);

            // world
            mapCreator_factory.render (world.createGraphics ());

            // black bar
            blackControlBar.drawImage (world.createGraphics ());

            //ball
            blackControlBar.displayBall ();

            g2.drawImage (world, 0, 0, null);

            g2.setColor (Color.white);
            g2.setFont (new Font ("TimesRoman", Font.BOLD, 20));
            g2.drawString ("Game Time: " + duration + " seconds", 5, GameWorld.SCREEN_HEIGHT - 10);
        }
    }
}