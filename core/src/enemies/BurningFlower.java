package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.Smoke;
import screens.MainGame;
import utils.Animator;

public class BurningFlower extends Enemy {

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval;

    Animator bulletAnimator;




    String moveType;

    public BurningFlower(int x, int y, String moveType) {
        super(x, y, 3, 1, 1, 0);
        this.moveType = moveType;
        int[] size = {16,16};
        setSize(48,48);
        setColor(Color.DARK_GRAY);
        animator = new Animator(new Texture(Gdx.files.internal("allies/Flower.png")),1,2,2,0.5f,size);
        int[] size2 = {8,8};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.5f,size2);

        shootInterval = 50;
    }

    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            MainGame.bullets.add(new Smoke(bulletAnimator,((int)(getX()+getWidth()/2)), ((int) (getY()+getHeight()))));
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
        animator.draw(this,batch);
    }
}
