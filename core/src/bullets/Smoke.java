package bullets;

import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public class Smoke extends Bullet {
    public Smoke(Animator animator, int x, int y) {
        super(animator, x, y, ' ', 'U', 1, 3);
        setBounds(getX(),getY(),16,16);

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

                MainGame.cloud.setDamage(ATK);
                MainGame.bullets.remove(this);
                return;
            }

    }

}
