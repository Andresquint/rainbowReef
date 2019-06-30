package RainbowReefJava;

import java.awt.*;
import java.util.ArrayList;

public class goodCollidable {
    brickBreakableWalls brickBreakableWalls;
    ArrayList<brickBreakableWalls> arrayOf_Walls;
    ArrayList<Integer> xy = new ArrayList<> ();
    mapCreator mapCreator = RainbowReefJava.mapCreator.getSinglemapCreator ();

    public boolean checkCollide(Rectangle a, Rectangle b) {
        if (a != null && b != null)
            return a.intersects (b);
        return false;
    }

    goodCollidable() {
        brickBreakableWalls = new brickBreakableWalls ();
        arrayOf_Walls = brickBreakableWalls.init_breakWalls ();
    }

    public boolean checkCollide(Rectangle r) {
        for (int i = 0; i < arrayOf_Walls.size (); i++) {
            Rectangle singleRect = arrayOf_Walls.get (i).getRectangle ();
            if (r.intersects (singleRect)) {
//                int x = brickBreakableWalls.table.get (arrayOf_Walls.get (i)).get (0);
//                int y = brickBreakableWalls.table.get (arrayOf_Walls.get (i)).get (1);
//                mapCreator.setTileChange (0,x,y);
                arrayOf_Walls.remove (i);
                return true;
            }
        }
        return false;
    }
}
