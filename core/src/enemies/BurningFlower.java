package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.Smoke;
import screens.MainGame;
import utils.Animator;

public class BurningFlower extends Enemy {

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval = 100;
    Animator bulletAnimator;
    int[] bulletSize = {8,8};




    String moveType;

    public BurningFlower(int x, int y, String moveType) {
        super(x, y, 3, 1, 1, 50);
        this.moveType = moveType;
        int[] size = {24,24};
        setSize(54,54);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Burning Flower.png")),1,2,2,0.5f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/BurningFlowerDefeat.png")),2,3,6,0.2f,size);
        dyingDuration = 1f;




    }

    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Fireball.png")),1,2,2,0.3f, bulletSize);
            MainGame.bullets.add(new Smoke(bulletAnimator,((int)(getX()+getWidth()/2-8)), ((int) (getY()+getHeight()))));
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
    public void draw(SpriteBatch batch) {
        if(isDead()){
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }
}
