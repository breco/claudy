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

/**
 * Created by victor on 4/11/18.
 */

public class Fire extends Enemy {

    //SHOOT variables
    int shootTimer = 0;
    int shootInterval;
    int[] bulletSize = {8,8};
    Animator bulletAnimator;
    String dirX = "R";
    //stats
    int SPEED_X = 2;
    float SPEED_Y = 0.15f; //0.15f

    //MOVE variables
    int final_x;
    int final_y;
    String moveType;
    //---POLAR MOVE variables
    double r = 100;
    double angle = 0;

    //TEST MOVE variables
    int start_x; // 0 || Principal.WIDTH
    int start_y;
    boolean zigzagVertical = true,zigzagHorizontal = true;
    int zigzagX = 2,zigzagY = 3;
    String finalTypeMove = "";
    public Fire(int x, int y,String moveType,float appearance){
        super(x,y,1,1,appearance,100);
        final_x = x;
        final_y = y;
        this.moveType = moveType;

        int[] size = {16,16};
        setSize(32,32);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/Fire.png")),1,2,2,0.5f,size);
        int[] size2= {8,8};
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/EnemyDefeat.png")),1,2,2,0.2f,size2);
        dyingDuration = 0.3f;
        Random random = new Random();
        shootInterval = random.nextInt(200) + 100; //[1,3]
        moveSetup();
    }
    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Smoke.png")),1,2,2,0.5f, bulletSize);
            MainGame.bullets.add(new Smoke(bulletAnimator,((int)(getX()+getWidth()/2-8)), ((int) (getY()+getHeight()))));
        }
    }

    public void attack(){
        if(!MainGame.cloud.inmunity() && MainGame.cloud.getBoundingRectangle().overlaps(getBoundingRectangle())){
            MainGame.cloud.setDamage(getATK());
            setDamage(CURRENT_HP);
            return;
        }
    }

    @Override
    public void update() {
        move();
        for(Ally ally : MainGame.allies.getAllies()){
            if(ally.getBoundingRectangle().overlaps(getBoundingRectangle())){
                ally.setDamage(getATK());
                setDamage(100);
                return;
            }
        }
        shoot();
        attack();
    }

    public void moveSetup(){
        Gdx.app.log("MOVESETUP",moveType);
        if(moveType.contains("zig-zag")){
            zigzagSetup();
        }
        else if(moveType.contains("static") || (moveType.equals(""))){
            start_x = final_x;
            start_y = final_y;
        }
        else if(moveType.contains("straight")){
            Gdx.app.log("STRAIGHT","!!");
            if(moveType.contains("R")){
                start_x = Principal.WIDTH;
            }
            else if(moveType.contains("L")){
                start_x = (int) (0 - getWidth());
            }
            start_y = final_y;
        }
        setPosition(start_x,start_y);
    }
    public void zigzagSetup(){
        if(moveType.contains("R")){
            Gdx.app.log("CONTAINS","R");
            start_x = ((int) (0 - getWidth())); // 0 || Principal.WIDTH
            zigzagHorizontal = true;
        }
        else if(moveType.contains("L")){
            Gdx.app.log("CONTAINS","L");
            zigzagHorizontal = false;
            start_x = Principal.WIDTH;
        }
        if(moveType.contains("M")){
            start_y = Principal.HEIGHT/4;
        }
        else if(moveType.contains("D")){
            start_y = Principal.HEIGHT/8;
        }
        if(moveType.contains("N")){
            finalTypeMove = "";
        }
        else if(moveType.contains("P")){
            finalTypeMove = "polar";
        }
        else if(moveType.contains("S")){
            finalTypeMove = "static";
        }
    }

    public void zigzagMove(){
        if((zigzagHorizontal && getX() >= final_x) || (!zigzagHorizontal && getX() <= final_x)){
            setY(getY()+ zigzagY);
            if(getY() >= final_y){
                moveType = "";
            }
            return;
        }


        if(zigzagHorizontal){
            setX(getX()+zigzagX);
        }
        else{
            setX(getX()-zigzagX);
        }


        if(zigzagVertical){
            setY(getY()+zigzagY);
            if(getY() >= final_y){
                zigzagVertical = !zigzagVertical;
            }
        }
        else{
            setY(getY()-zigzagY);
            if(getY() <= start_y){
                zigzagVertical = !zigzagVertical;
            }
        }
    }


    public void polarMove(){
        r = 50*Math.cos(angle*2);
        setX(final_x + (float) (r*Math.cos(angle)));
        setY(final_y + (float) (r*Math.sin(angle)));
        angle+= 0.025;
    }

    public void moveStraight(){
        if(moveType.contains("R")){
            setX(getX() - zigzagX);
            if(getX() <= final_x){
                moveType = "static";
            }
        }
        else if(moveType.contains("L")){
            setX(getX() + zigzagX);
            if(getX() >= final_x){
                moveType = "static";
            }
        }
    }
    @Override
    public void move() {
        if(moveType.equals("polar")){
            polarMove();
            return;
        }
        else if(moveType.contains("zig-zag")){
            zigzagMove();
            return;
        }
        else if(moveType.equals("static")){

            dirX = " ";
        }
        else if(moveType.contains("straight")){
            moveStraight();
            return;
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
        if(getY() <= 0){
            setDamage(100);
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
