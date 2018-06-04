package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import java.util.Random;

import allies.Ally;
import bullets.Smoke;
import screens.MainGame;
import utils.Animator;

public class FireEater extends Enemy {
    //SHOOT variables
    int shootTimer = 0;
    int shootInterval;
    int[] bulletSize = {8,8};
    Animator bulletAnimator;
    String dirX = "R";
    //stats
    int SPEED_X = 3;
    float SPEED_Y = 2f;

    //MOVE variables
    double r = 100;
    double angle = 0;
    int start_x;
    int start_y;
    String moveType;
    public FireEater(int x, int y, String moveType,float appearance){
        super(x,y,3,1,appearance,500);
        start_x = x;
        start_y = y;
        this.moveType = moveType;
        int[] size = {24,24};
        setSize(48,48);

        animator = new Animator(new Texture(Gdx.files.internal("enemies/Fire Eater.png")),1,2,2,0.3f,size);
        int[] size2= {24,24};
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/EnemyDefeat2.png")),1,2,2,0.2f,size2);
        dyingDuration = 0.3f;
        Random random = new Random();
        shootInterval = random.nextInt(200) + 100; //[1,3]
    }
    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.4f, bulletSize);
            Smoke smoke = new Smoke(bulletAnimator,((int)(getX()+getWidth()/2)), ((int) (getY()+getHeight())));
            smoke.setSize(20,20);
            MainGame.bullets.add(smoke);
        }
    }

    @Override
    public void update() {
        move();
        shoot();
        attack();
    }

    public void attack(){
        for(Ally ally : MainGame.allies.getAllies()){
            if(ally.getBoundingRectangle().overlaps(getBoundingRectangle())){
                ally.setDyingAnimator("transformations/FlowerToBurning.png",1.3f,24,24,2,4,0.4f);
                setDamage(100);
                ally.setEaten(true);
                ally.setDamage(100);
                MainGame.allies.remove(ally);
                MainGame.enemies.removeForced(this);

                break;
            }
        }
    }

    @Override
    public void move() {
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

        if(isDead()){
            dyingAnimator.draw(this,batch);
            return;
        }animator.draw(this,batch);
    }
}
