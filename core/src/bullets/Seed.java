package bullets;

import com.badlogic.gdx.graphics.Color;

import enemies.Enemy;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/23/18.
 */

public class Seed extends Bullet {
    public Seed(Animator animator, int x, int y ) {
        super(animator, x, y, ' ', 'U', 1, 6);
        setBounds(getX(),getY(),16,16);
        setColor(Color.BROWN);
        type = "ally";
    }
    public void update(){
        move();
        attack();
        destroy();
    }
    public void attack(){
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().overlaps(getBoundingRectangle())){
                enemy.getDamage(ATK);
                MainGame.bullets.remove(this);
                MainGame.highscore.add(enemy.points);
                return;
            }
        }
    }
}
