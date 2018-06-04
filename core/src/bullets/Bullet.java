package bullets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.breco.claudy.Principal;

import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

/**
 * Created by victor on 4/11/18.
 */

public class Bullet extends Sprite {
    char oriX,oriY;
    int SPD,ATK;
    public Animator animator;
    public String type;
    Rectangle rect;

    //VISUAL variables
    Animator dyingAnimator;
    TimeManager dyingTimer;
    float dyingDuration;

    public Bullet(Animator animator, int x, int y,char oriX,char oriY,int ATK,int SPD){
        setPosition(x, y);
        this.oriX = oriX;
        this.oriY = oriY;
        this.SPD = SPD;
        this.ATK = ATK;
        this.animator = animator;

    }
    public void move(){
        if(oriX == 'L'){
            setX(getX()-SPD);
        }
        else if(oriX == 'R'){
            setX(getX()+SPD);
        }
        if(oriY == 'D'){
            setY(getY()-SPD);
        }
        else if(oriY == 'U'){
            setY(getY()+SPD);
        }
    }
    public void destroy(){

        if(getY() <= 0 && type.equals("ally")){
            MainGame.cloud.CURRENT_SHOTS--;
            MainGame.bullets.removeForced(this);
        } else if (getY() >= Principal.HEIGHT && type.equals("enemy")){
            MainGame.bullets.removeForced(this);
        }
    }
    public void update(){
    }
    public void attack(){

    }
    public void draw(SpriteBatch batch){
        //
        animator.draw(this,batch);
    }
    public void blowed(String ori, float amount){
        if(ori.equals("R")){
            setX(getX()+amount);
        }
        else if(ori.equals("L")){
            setX(getX()-amount);
        }
    }
}
