package movers;

import com.badlogic.gdx.Gdx;
import com.breco.claudy.Principal;

import enemies.Enemy;

public class HorizontalMover extends Mover {
    String dirX;
    public HorizontalMover(Enemy owner, String patron, float final_x, float final_y) {
        super(owner, patron, final_x, final_y);
        start_x = final_x;
        start_y = final_y;
        owner.setPosition(start_x,start_y);
        if(patron.contains("R")){
            dirX = "R";
        }
        else if(patron.contains("L")){
            dirX = "L";
        }
    }

    @Override
    public void move() {
        Gdx.app.log("moving","from normal mover");
        Gdx.app.log("SPEED_X",SPEED_X+"");
        Gdx.app.log("SPEED_Y",SPEED_Y+"");

        if(dirX.equals("R")){
            owner.setX(owner.getX()+SPEED_X);
            if(owner.getX() + owner.getWidth() > Principal.WIDTH){
                dirX = "L";
                owner.setX( Principal.WIDTH - owner.getWidth());
            }
        }
        else if(dirX.equals("L")){
            owner.setX(owner.getX()-SPEED_X);
            if(owner.getX() < 0){
                owner.setX(0);
                dirX = "R";
            }
        }
    }
}
