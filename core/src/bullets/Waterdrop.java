package bullets;

import allies.Ally;
import enemies.Enemy;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public class Waterdrop extends Bullet {

    public Waterdrop(Animator animator, int x, int y) {
        super(animator, x, y, ' ', 'D', 1, 8); // SPD 8

        setBounds(getX(),getY(),16,16);
        type = "ally";
    }
    public void update(){
        move();
        attack();
        water();
        destroy();
        //
    }
    public void attack(){
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().overlaps(getBoundingRectangle())){
                enemy.setDamage(ATK);
                MainGame.bullets.removeForced(this);
                MainGame.cloud.CURRENT_SHOTS--;
                if (enemy.getHP() ==0){
                    MainGame.highscore.add(enemy.points);
                    MainGame.cloud.setAP(1);
                }
                return;
            }
        }
    }
    public void water(){
        for (Ally ally : MainGame.allies.getAllies()){
            if(ally.getBoundingRectangle().overlaps(getBoundingRectangle())){
                ally.getWater(ATK);
                MainGame.bullets.removeForced(this);
                MainGame.cloud.CURRENT_SHOTS--;

            }
        }
    }

}
