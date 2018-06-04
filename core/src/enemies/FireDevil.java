package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.breco.claudy.Principal;

import bullets.FireSpin;
import screens.MainGame;
import utils.Animator;

public class FireDevil extends Enemy {

    //MOVE variables
    String dir = "R";
    int SPEED_X = 2;

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval = 40;
    Animator bulletAnimator;
    int[] bulletSize = {8,8};
    int bulletSpeed = 4;



    public FireDevil(int x, int y,float appearance) {
        super(x, y, 5, 2, appearance, 10000);
        int[] size = {200,200};
        animator = new Animator(new Texture(Gdx.files.internal("enemies/FireDevil.png")),2,2,4,0.15f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/FireDevilDefeat.png")),3,3,9,0.3f,size);
        setSize(250,250);
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2, 0,0,this));
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2,72,0,this));
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2,144,0,this));
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2,216,0,this));
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2,288,0,this));
        dyingDuration = 2f;
    }

    @Override
    public void shoot() {

        shootTimer++;
        if(shootTimer >= shootInterval){
            Gdx.app.log("SHOOT","DEVIL");
            shootTimer = 0;
            bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Fireball.png")),1,2,2,0.3f, bulletSize);
            MainGame.bullets.add(new FireSpin(bulletAnimator,((int)(getX()+getWidth()/2-8)), ((int) (getY()+getHeight())),' ',bulletSpeed));
            MainGame.bullets.add(new FireSpin(bulletAnimator,((int)(getX()+getWidth()/2-8)), ((int) (getY()+getHeight())),'R',bulletSpeed));
            MainGame.bullets.add(new FireSpin(bulletAnimator,((int)(getX()+getWidth()/2-8)), ((int) (getY()+getHeight())),'L',bulletSpeed));

        }

    }

    @Override
    public void update() {
        move();
        shoot();
    }

    @Override
    public void move() {
        if(dir.equals("R")){
            setX(getX()+SPEED_X);
            if(getX()>= Principal.WIDTH*6/11){
                dir ="L";
            }
        }
        else if(dir.equals("L")){
            setX(getX()-SPEED_X);
            if(getX() <= Principal.WIDTH/11){
                dir = "R";
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(isDead()){
            Gdx.app.log("Drawing","dying");
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }
    @Override
    public Rectangle getBoundingRectangle(){
        return new Rectangle(0,0,0,0);
    }
    public void addBulletSpeed(int amount){
        bulletSpeed+=amount;
        shootInterval-=2;
        Gdx.app.log("shootInterval=",shootInterval+"");
    }
}
