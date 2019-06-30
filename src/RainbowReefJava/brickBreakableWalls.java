package RainbowReefJava;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.List;

public class brickBreakableWalls {
    int x;
    int y;
    int w;
    int h;
    int[][] mapper;
    mapCreator mapCreator = RainbowReefJava.mapCreator.getSinglemapCreator ();
    ArrayList<brickBreakableWalls> wallsArrayList = new ArrayList<> ();
    Hashtable<brickBreakableWalls, ArrayList<Integer>> table = new Hashtable<> ();


    brickBreakableWalls(int xval, int yval, int wi, int he) {
        x = xval;
        y = yval;
        w = wi;
        h = he;

    }

    brickBreakableWalls() {
    }

    Rectangle getRectangle() {
        return new Rectangle (x, y, w, h);
    }

    ArrayList<brickBreakableWalls> init_breakWalls() {
        mapper = mapCreator.getMap ();
        int wallIndex = 0;
        for (int i = 0; i < mapper.length; i++) {
            for (int j = 0; j < mapper[0].length; j++) {
                if (mapper[i][j] >= 2 && mapper[i][j] <= 9) {
                    wallsArrayList.add (wallIndex, new brickBreakableWalls (i * mapCreator.BOX_WIDTH, j * mapCreator.BOX_HEIGHT, mapCreator.BOX_WIDTH, mapCreator.BOX_HEIGHT));

                    ArrayList<Integer> cartesianvalues = new ArrayList<> ();

                    cartesianvalues.add (0, i);
                    cartesianvalues.add (1, j);
                    table.put (wallsArrayList.get (wallIndex), cartesianvalues);
                    wallIndex++;

                }
            }
        }
        return wallsArrayList;
    }
}
