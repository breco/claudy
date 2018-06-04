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
    int MAX_Y = Principal.HEIGHT - 90;

    //VISUAL variables
    Animator thunderAnimator;

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval = 200;
    int[] bulletSize = {8,8};

    public ThunderCloud(int x, int y,String moveType,float appearance) {
        super(x, y, 5, 1, appearance, 1000);
        int[] size = {16,16};
        int[] size2 = {8,8};
        setSize(40,40);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Thunder Cloud.png")),1,2,2,0.5f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/EnemyDefeat3.png")),1,2,2,0.2f,size2);
        dyingDuration = 0.3f;
    }

    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;

            if(getY() >= MAX_Y){
                thunderAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Thunder.png")),1,3,3,0.1f,bulletSize);
                MainGame.bullets.add(new Thunder(thunderAnimator,((int)(getX()+8)), ((int) (getY())),'D'));

            }
            else {
                thunderAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Thunder.png")),1,3,3,0.1f,bulletSize);
                MainGame.bullets.add(new Thunder(thunderAnimator,((int)(getX()+8)), ((int) (getY()+getHeight())),'U'));
            }

        }
    }

    @Override
    public void update() {
        move();
        shoot();
        attack();
    }

    public void attack(){
        if(MainGame.cloud.getBoundingRectangle().overlaps(getBoundingRectangle())){

            MainGame.cloud.setDamage(getATK());
            MainGame.enemies.remove(this);
            return;
        }
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
        if(isDead()){
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }

}
