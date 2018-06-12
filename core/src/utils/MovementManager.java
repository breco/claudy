package utils;

import enemies.Enemy;
import movers.CircularMover;
import movers.GravityMover;
import movers.Mover;
import movers.NormalMover;
import movers.SpiralMover;
import movers.StraightMover;
import movers.ZigzagMover;

public class MovementManager {
    public static Mover getMover(Enemy owner, String patron, float final_x, float final_y){
        if(patron.contains("zig-zag")){
            return new ZigzagMover(owner,patron, final_x, final_y);
        }
        else if(patron.contains("gravity")){
            return new GravityMover(owner, patron, final_x, final_y);
        }
        else if(patron.contains("normal")){
            return new NormalMover(owner,patron,final_x,final_y);
        }
        else if(patron.contains("circular")){
            return new CircularMover(owner,patron,final_x,final_y);
        }
        else if(patron.contains("straight")){
            return new StraightMover(owner,patron,final_x,final_y);
        }
        else if(patron.contains("spiral")){
            return new SpiralMover(owner,patron,final_x,final_y);
        }
        return null;
    }
}
