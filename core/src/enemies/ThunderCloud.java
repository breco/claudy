package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import bullets.Thunder;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/14/18.
 */

public class ThunderCloud extends Enemy {

    //MOVE variables
    float SPEED_Y = 0.5f;
    int MAX_Y = Principal.HEIGHT - 75;

    //VISUAL variables
    Animator bulletAnimator;

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval = 400;

    public ThunderCloud(int x, int y,String moveType) {
        super(x, y, 5, 1, 1, 1000);
        int[] size2 = {8,8};
        int[] size = {16,16};
        setSize(32,32);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Thunder Cloud.png")),1,2,2,0.5f,size);
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Thunder.png")),1,1,1,0.5f,size2);
    }

    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            if(getY() >= MAX_Y){
                MainGame.bullets.add(new Thunder(bulletAnimator,((int)(getX())), ((int) (getY())),'D'));

            }
            else {
                MainGame.bullets.add(new Thunder(bulletAnimator,((int)(getX())), ((int) (getY()+getHeight())),'U'));
            }

        }
    }

    @Override
    public void update() {
        move();
        shoot();
    }

    @Override
    public void move() {
        if(getY() >= MAX_Y){
            return;
        }
        setY(getY()+SPEED_Y);
        if(getY() > MAX_Y){
            setY(MAX_Y);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        animator.draw(this,batch);
    }
}
