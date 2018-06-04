package enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

/**
 * Created by victor on 4/11/18.
 */

public abstract class Enemy extends Sprite {
    //stats
    private int HP;
    private int ATK;
    private int  CURRENT_HP;
    public int points;
    //others
    float appearance;

    //VISUAL variables
    Animator animator;
    Animator dyingAnimator;
    TimeManager dyingTimer;
    float dyingDuration;
    public Enemy(int x, int y, int HP, int ATK, float appearance,int points){
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
    public void setDamage(int dmg) {

        CURRENT_HP -= dmg;
        if (CURRENT_HP <= 0) {
            dyingTimer = new TimeManager();
            dyingTimer.setChronometer(dyingDuration);
            dyingTimer.start();

            CURRENT_HP = 0;
            MainGame.enemies.remove(this);
        }

    }
    public int getATK(){
        return ATK;
    }

    public int getHP(){return CURRENT_HP;}

    public boolean isDead(){
        if(CURRENT_HP <= 0) return true;
        return false;
    }
    public boolean onScreen(){
        if(appearance <= MainGame.time.getTime()) return true;
        return false;
    }
}
