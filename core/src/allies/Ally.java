package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

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
    Animator dyingAnimator;
    TimeManager dyingTimer;
    float dyingDuration;

    //CONDITION variables
    boolean eaten;

    public Ally(int x, int y){
        setPosition(x, y);
        eaten = false;


    }
    public abstract void shoot();
    public abstract void update();
    public abstract void move();
    public abstract void getWater(int WP);
    public abstract void draw(SpriteBatch batch);
    public abstract void dispose();
    public void setDamage(int dmg) {

        CURRENT_HP -= dmg;
        if (CURRENT_HP <= 0) {
            CURRENT_HP = 0;

            dyingTimer = new TimeManager();
            dyingTimer.setChronometer(dyingDuration);
            dyingTimer.start();
            MainGame.allies.remove(this);
        }

    }
    public boolean isDead(){
        if(CURRENT_HP <= 0) return true;
        return false;
    }
    public void setDyingAnimator(String direction,float time, int width, int height, int rows, int columns, float speed){
        dyingDuration = time;
        int[] size = {width, height};
        dyingAnimator = new Animator(new Texture(Gdx.files.internal(direction)),rows,columns,rows*columns,speed,size);
    }
    public boolean isEaten(){
        return eaten;
    }
    public void setEaten(boolean flag){
        eaten = flag;
    }

}
