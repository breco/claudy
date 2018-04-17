package bullets;

import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/14/18.
 */

public class Thunder extends Bullet {
    public Thunder(Animator animator, int x, int y,char oriY) {
        super(animator, x, y, ' ', oriY, 1, 2);
        setBounds(getX(),getY(),32,32);

        type = "enemy";
    }
    public void update(){
        move();
        attack();
        destroy();
        //
    }
    public void attack(){
        if(MainGame.cloud.getBoundingRectangle().overlaps(getBoundingRectangle())){

            MainGame.cloud.getDamage(ATK);
            MainGame.bullets.remove(this);
            return;
        }

    }
}
