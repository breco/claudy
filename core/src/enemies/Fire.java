package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import java.util.Random;

import bullets.Smoke;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public class Fire extends Enemy {

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval;

    Animator bulletAnimator;
    String dirX = "R";
    //stats
    int SPEED_X = 2;
    float SPEED_Y = 0.15f;

    //MOVE variables
    double r = 100;
    double angle = 0;
    int start_x;
    int start_y;
    String moveType;
    public Fire(int x, int y,String moveType){
        super(x,y,1,1,1,100);
        start_x = x;
        start_y = y;
        this.moveType = moveType;
        int[] size = {16,16};
        setSize(32,32);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Fire.png")),1,2,2,0.5f,size);
        int[] size2 = {8,8};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.5f,size2);
        Random random = new Random();
        shootInterval = random.nextInt(200) + 100; //[1,3]
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
        move();
        shoot();
    }

    public void staticMove(){

    }

    public void polarMove(){
        r = 50*Math.cos(angle*2);
        setX(start_x + (float) (r*Math.cos(angle)));
        setY(start_y + (float) (r*Math.sin(angle)));
        angle+= 0.025;
    }

    @Override
    public void move() {
        if(moveType.equals("polar")){
            polarMove();
            return;
        }
        if(moveType.equals("static")){

            dirX = " ";
        }
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
        setY(getY() - SPEED_Y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        animator.draw(this,batch);
    }
}
