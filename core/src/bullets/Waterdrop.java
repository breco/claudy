package bullets;

import enemies.Enemy;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public class Waterdrop extends Bullet {

    public Waterdrop(Animator animator, int x, int y) {
        super(animator, x, y, ' ', 'D', 1, 8);

        setBounds(getX(),getY(),16,16);
        type = "ally";
    }
    public void update(){
        move();
        attack();
        destroy();
        //
    }
    public void attack(){
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().overlaps(getBoundingRectangle())){
                enemy.getDamage(ATK);
                MainGame.bullets.remove(this);
                MainGame.cloud.CURRENT_SHOTS--;
                MainGame.highscore.add(enemy.points);
                return;
            }
        }
    }


}
