package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.breco.claudy.Principal;

import java.util.Random;

import bullets.Bullet;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/14/18.
 */

public class DarkCloud extends Enemy{

    //SHOOT variables


    //VISUAL variables
    Animator bulletAnimator;

    //MOVE variables
    String dirX = "R";
    int SPEED_X = 2;
    String moveType;


    //BLOW variables
    Rectangle blowRect;
    public DarkCloud(int x, int y,String moveType) {
        super(x, y, 5, 1, 1, 500);
        this.moveType = moveType;
        int[] size2 = {8,8};
        int[] size = {16,16};
        setSize(32,32);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Dark Cloud.png")),1,2,2,0.5f,size);
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.5f,size2);
        Random random = new Random();
        SPEED_X = random.nextInt(4) + 1; //[1,3]
        blowRect = new Rectangle(getX()+getWidth(),getY(),96,128);
    }


    @Override
    public void shoot() {

    }

    @Override
    public void update() {
        move();
        shoot();
        blow();
    }

    @Override
    public void draw(SpriteBatch batch) {
        animator.draw(this,batch);
    }

    @Override
    public void move() {
        if(moveType.equals("windy")) return;
        if(dirX.equals("R")){
            setX(getX()+SPEED_X);
            if(getX() + getWidth() > Principal.WIDTH){
                dirX = "L";
                setX( Principal.WIDTH - getWidth());
            }
        }
        else if(dirX.equals("L")){
            setX(getX()-SPEED_X);
            if(getX() < 0){
                setX(0);
                dirX = "R";
            }
        }
    }

    public void blow(){
        if(!moveType.equals("windy")) return;
        for(Bullet bullet : MainGame.bullets.getBullets()){
            if(bullet.type.equals("ally")){
                if(blowRect.overlaps(bullet.getBoundingRectangle())){
                    bullet.blowed("R",2);
                }
            }
        }
    }
}