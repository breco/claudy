package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.Seed;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/23/18.
 */

public class CactusFlower extends Ally {

    //SHOOT VARIABLES
    int shootTimer = 0;
    int shootInterval = 200;
    Animator bulletAnimator;
    int[] bulletSize = {8,8};

    public CactusFlower(int x, int y){
        super(x,y);
        HP = 1;
        ATK = 1;
        points = 2000;
        CURRENT_HP = HP;
        int[] size = {16,16};
        setSize(54,54);
        animator = new Animator(new Texture(Gdx.files.internal("allies/Cactus Flower.png")),1,2,2,0.4f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("allies/CactusFlowerDeath.png")),3,2,6,0.2f,size);
        dyingDuration = 1f;



    }


    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Bullet Seed.png")),1,2,2,0.5f, bulletSize);
            MainGame.bullets.add(new Seed(bulletAnimator,((int)(getX()+getWidth()/2 - 8)), ((int) (getY()+getHeight()))));

        }
    }

    @Override
    public void update() {
        shoot();
    }

    @Override
    public void move() {

    }

    @Override
    public void getWater(int WP) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        if(isDead()){
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }
    

}
