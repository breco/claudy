package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

    Animator bulletAnimator;
    String dirX = "R";
    //stats
    int SPEED_X = 2;
    float SPEED_Y = 0.6f;

    //MOVE variables
    double r = 100;
    double angle = 0;
    int start_x;
    int start_y;
    String moveType;
    public FireEater(int x, int y, String moveType){
        super(x,y,3,1,1,500);
        start_x = x;
        start_y = y;
        this.moveType = moveType;
        int[] size = {16,16};
        setSize(48,48);
        //setColor(Color.PINK);
        //setColor(Color.PURPLE);
        setColor(Color.SCARLET);
        //setColor(Color.SKY);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Fire.png")),1,2,2,0.5f,size);
        int[] size2 = {8,8};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.4f,size2);
        Random random = new Random();
        shootInterval = random.nextInt(200) + 100; //[1,3]
    }
    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
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
                MainGame.allies.remove(ally);
                MainGame.enemies.remove(this);
                MainGame.enemies.add(new BurningFlower(((int) ally.getX()), ((int) ally.getY()),""));
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
        animator.draw(this,batch);
    }
}
