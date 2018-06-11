package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

import allies.Ally;
import bullets.Smoke;
import screens.MainGame;
import utils.Animator;
import utils.MovementManager;

/**
 * Created by victor on 4/11/18.
 */

public class Fire extends Enemy {

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval;
    int[] bulletSize = {8,8};
    Animator bulletAnimator;

    //MOVE variables
    int final_x;
    int final_y;
    String moveType;

    public Fire(int x, int y,String moveType,float appearance){
        super(x,y,1,1,appearance,100);
        final_x = x;
        final_y = y;
        SPEED_X = 2;
        SPEED_Y = 0.15f;
        this.moveType = moveType;
        Gdx.app.log("moveType",moveType+"_");
        mover = MovementManager.getMover(this,moveType, x, y);
        mover.setSpeed(SPEED_X,SPEED_Y);
        int[] size = {16,16};
        setSize(32,32);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Fire.png")),1,2,2,0.5f,size);
        int[] size2= {8,8};
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/EnemyDefeat.png")),1,2,2,0.2f,size2);
        dyingDuration = 0.3f;
        Random random = new Random();
        shootInterval = random.nextInt(200) + 100; //[1,3]
    }
    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.5f, bulletSize);
            MainGame.bullets.add(new Smoke(bulletAnimator,((int)(getX()+getWidth()/2-8)), ((int) (getY()+getHeight()))));
        }
    }

    public void attack(){
        if(!MainGame.cloud.inmunity() && MainGame.cloud.getBoundingRectangle().overlaps(getBoundingRectangle())){
            MainGame.cloud.setDamage(getATK());
            setDamage(CURRENT_HP);
            return;
        }
        for(Ally ally : MainGame.allies.getAllies()){
            if(ally.getBoundingRectangle().overlaps(getBoundingRectangle())){
                ally.setDamage(getATK());
                setDamage(CURRENT_HP);
                return;
            }
        }
    }

    @Override
    public void update() {
        move();

        shoot();
        attack();
        destroy();
    }


    @Override
    public void move() {
         mover.move();

    }

    public void destroy(){
        if(getY() <= 0){
            setDamage(CURRENT_HP);
            LittleFire lf = new LittleFire(((int) (getX()+getWidth()/2-8)),0,"LR",0);
            if(lf.canLive()){
                MainGame.enemies.add(lf);
            }

        }
    }

    @Override
    public void draw(SpriteBatch batch) {

        if(isDead()){
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }

    //GETTERS


}
