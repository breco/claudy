package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import bullets.AutoBullet;
import bullets.Bullet;
import screens.MainGame;
import utils.Animator;
import utils.MovementManager;

/**
 * Created by victor on 4/14/18.
 */

public class DarkCloud extends Enemy{

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval = 200;
    int[] bulletSize = {8,8};
    Animator bulletAnimator;


    //VISUAL variables

    //MOVE variables
    int SPEED_X = 4;



    //BLOW variables
    Rectangle blowRect;
    public DarkCloud(int x, int y,String patron, float appearance) {
        super(x, y, 3, 1, appearance, 200);
        this.patron = patron;
        int[] size2 = {8,8};
        int[] size = {16,16};
        setSize(32,32);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Dark Cloud.png")),1,2,2,0.5f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/EnemyDefeat3.png")),1,2,2,0.2f,size2);
        dyingDuration = 0.3f;

        mover = MovementManager.getMover(this,patron,x,y);
        mover.setSpeed(SPEED_X,0);
        //TEST
        blowRect = new Rectangle(getX()+getWidth(),getY(),96,128);
    }


    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.5f, bulletSize);
            MainGame.bullets.add(new AutoBullet(bulletAnimator,((int)(getX()+getWidth()/2-8)), ((int) (getY()-16))));
        }
    }

    @Override
    public void update() {
        move();
        attack();
        shoot();
        blow();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(isDead()){
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }

    @Override
    public void move() {
        mover.move();
        return;

    }

    public void blow(){
        if(!patron.equals("windy")) return;
        for(Bullet bullet : MainGame.bullets.getBullets()){
            if(bullet.type.equals("ally")){
                if(blowRect.overlaps(bullet.getBoundingRectangle())){
                    bullet.blowed("R",2);
                }
            }
        }
    }
}
