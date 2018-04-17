package enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public abstract class Enemy extends Sprite {
    //stats
    int HP;
    int ATK;
    int CURRENT_HP;
    public int points;
    //others
    int appearance;

    //VISUAL variables
    Animator animator;
    public Enemy(int x, int y, int HP, int ATK, int appearance,int points){
        setPosition(x, y);

        this.HP = HP;
        this.ATK = ATK;
        CURRENT_HP = HP;
        this.appearance = appearance;
        this.points = points;


    }
    public abstract void shoot();
    public abstract void update();
    public abstract void move();
    public abstract void draw(SpriteBatch batch);
    public void getDamage(int dmg) {

        CURRENT_HP -= dmg;
        if (CURRENT_HP <= 0) {
            CURRENT_HP = 0;
            MainGame.enemies.remove(this);
        }

    }
    public int getATK(){
        return ATK;
    }


}
