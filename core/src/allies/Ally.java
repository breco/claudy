package allies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public abstract class Ally extends Sprite {
    //stats
    int HP;
    int ATK;
    int CURRENT_HP;
    int points;
    //others
    int appearance;

    //VISUAL variables
    Animator animator;
    public Ally(int x, int y){
        setPosition(x, y);



    }
    public abstract void shoot();
    public abstract void update();
    public abstract void move();
    public abstract void getWater(int WP);
    public abstract void draw(SpriteBatch batch);
    public void getDamage(int dmg) {

        CURRENT_HP -= dmg;
        if (CURRENT_HP <= 0) {
            CURRENT_HP = 0;
            MainGame.allies.remove(this);
        }

    }
}
