
package RainbowReefJava;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.utils.SecuritySupport.getResourceAsStream;

class mapCreator {
    public final int BOX_WIDTH = 64;
    public final int BOX_HEIGHT = 32;
    private int width = GameWorld.SCREEN_WIDTH;
    private int height = (GameWorld.SCREEN_HEIGHT + 30);
    private int[][] map, map2;
    private Image border, background, image2, image3, image4, image5, image6, image7, image8, image9;
    private static mapCreator currentFactory;

    private mapCreator() {
        this.map2 = convertTxtToDoubleArray ("rsc/block_map.txt");
        this.map = convertTxtToDoubleArray ("rsc/borderMap.txt");
        border = readImage ("rsc/border.gif");
        border = border.getScaledInstance (32, 32, Image.SCALE_SMOOTH);
        background = readImage ("rsc/RainbowReefBackground.bmp");
        background = background.getScaledInstance (GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, Image.SCALE_SMOOTH);
        image2 = readImage ("rsc/Block1.gif");
        image2 = image2.getScaledInstance (BOX_WIDTH, BOX_HEIGHT, Image.SCALE_SMOOTH);
        image6 = readImage ("rsc/Block2.gif");
        image6 = image6.getScaledInstance (BOX_WIDTH, BOX_HEIGHT, Image.SCALE_SMOOTH);
        image3 = readImage ("rsc/Block3.gif");
        image3 = image3.getScaledInstance (BOX_WIDTH, BOX_HEIGHT, Image.SCALE_SMOOTH);
        image4 = readImage ("rsc/Block4.gif");
        image4 = image4.getScaledInstance (BOX_WIDTH, BOX_HEIGHT, Image.SCALE_SMOOTH);
        image5 = readImage ("rsc/Block6.gif");
        image5 = image5.getScaledInstance (BOX_WIDTH, BOX_HEIGHT, Image.SCALE_SMOOTH);
        image7 = readImage ("rsc/Block_split.gif");
        image7 = image7.getScaledInstance (BOX_WIDTH, BOX_HEIGHT, Image.SCALE_SMOOTH);
        image8 = readImage ("rsc/Block_life.gif");
        image8 = image8.getScaledInstance (BOX_WIDTH, BOX_HEIGHT, Image.SCALE_SMOOTH);
        image9 = readImage ("rsc/Bigleg.gif");
        image9 = image9.getScaledInstance (BOX_WIDTH * 2, BOX_HEIGHT * 3, Image.SCALE_SMOOTH);
    }

    static mapCreator getSinglemapCreator() {
        if (currentFactory != null) {
            return currentFactory;
        }
        currentFactory = new mapCreator ();
        return currentFactory;
    }

    JPanel create_jPanelSplit_Factory(JFrame jframe) {
        JPanel jPanel = new JPanel ();
        jPanel.setPreferredSize (new Dimension (width, height));
        jframe.add (jPanel);
        return jPanel;
    }


    void render(Graphics g) {

        // general background
        g.drawImage (background, 0, 0, null);

        for (int i = 0; i < map2.length; i++) {
            for (int j = 0; j < map2[0].length; j++) {
                switch (map2[i][j]) {
                    case 2:
                        g.setColor (Color.BLACK);
                        g.drawImage (image2, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                        break;
                    case 3:
                        g.setColor (Color.BLACK);
                        g.drawImage (image3, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                        break;
                    case 4:
                        g.setColor (Color.BLACK);
                        g.drawImage (image4, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                        break;
                    case 5:
                        g.setColor (Color.BLACK);
                        g.drawImage (image5, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                        break;
                    case 6:
                        g.setColor (Color.BLACK);
                        g.drawImage (image6, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                        break;
                    case 7:
                        g.setColor (Color.BLACK);
                        g.drawImage (image7, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                        break;
                    case 8:
                        g.setColor (Color.BLACK);
                        g.drawImage (image8, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                        break;
                    case 9:
                        g.setColor (Color.BLACK);
                        g.drawImage (image9, i * BOX_WIDTH, j * BOX_HEIGHT, null);
                        g.drawRect (i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH * 2, BOX_HEIGHT * 3);


                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 1:
                        g.setColor (Color.BLACK);
                        g.drawImage (border, i * 32, j * 32, null);
                        g.drawRect (i * 32, j * 32, 32, 32);
                        break;
                }
            }
        }
    }

    BufferedImage readImage(String pictureFile) {
        BufferedImage bufferedImage = null;
        try {
//            bufferedImage = ImageIO.read (getClass ().getResourceAsStream ("/Block4.gif"));
            bufferedImage = ImageIO.read (new File (pictureFile));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return bufferedImage;
    }

    BufferedImage imageCreator(JPanel jPanel) {
        BufferedImage bufferedImage = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics ();
        jPanel.print (g2);
        return bufferedImage;

    }

    private int[][] convertTxtToDoubleArray(String txtfile) {
        Scanner scanner = checkScanner (txtfile);
        String line;
        int numberValue;
        int j = 0;
        map = new int[40][30];
        // layout of map
        while (scanner.hasNextLine ()) {
            line = scanner.nextLine ();
            for (int i = 0; i < line.length (); i++) {
                numberValue = Integer.parseInt (String.valueOf (line.charAt (i)));
                map[i][j] = numberValue;
            }
            // increments y component of .txt file
            j++;
        }
        return map;
    }

    int[][] getMap() {
        return map2;
    }

    private Scanner checkScanner(String txtfile) {

//        InputStream inputStream = mapCreator.class.getResourceAsStream (txtfile);
//        InputStreamReader inputStreamReader = new InputStreamReader ((inputStream));
//        BufferedReader bufferedReader = new BufferedReader (inputStreamReader);

        File file = new File (txtfile);
        Scanner scanner = null;
        try {
            scanner = new Scanner (file);
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
        return scanner;
    }

    void setTileChange(int value, int x, int y) {
        map2[x][y] = value;
    }
}