package movers;

import com.breco.claudy.Principal;

import enemies.Enemy;
import utils.MovementManager;

public class StraightMover extends Mover{
    public StraightMover(Enemy owner, String patron, float final_x, float final_y) {
        super(owner, patron, final_x, final_y);
        finalPatron = "gravity";
        SPEED_X = 4;
        if(patron.contains("R")){
            start_x = Principal.WIDTH;
        }
        else if(patron.contains("L")){
            start_x = (int) (0 - owner.getWidth());
        }
        start_y = final_y;
        owner.setPosition(start_x,start_y);
    }

    @Override
    public void move() {
        if(patron.contains("R")){
            owner.setX(owner.getX() - SPEED_X);
            if(owner.getX() <= final_x){
                owner.setMover(MovementManager.getMover(owner,finalPatron, final_x, final_y));
            }
        }
        else if(patron.contains("L")){
            owner.setX(owner.getX() + SPEED_X);
            if(owner.getX() >= final_x){
                owner.setMover(MovementManager.getMover(owner,finalPatron, final_x, final_y));
            }
        }
    }
}
