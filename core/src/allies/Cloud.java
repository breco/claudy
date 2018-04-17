package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.breco.claudy.Principal;

import bullets.Waterdrop;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/10/18.
 */

public class Cloud extends Sprite {


    //VISUAL variables
    public Animator animator;
    public Animator bulletAnimator;


    //MOVE variables
    public int SPEED = 3;
    public int HP = 1;
    public int CURRENT_HP = HP;
    public String dirX;


    //SHOT variables

    public int MAX_SHOTS = 3;
    public int CURRENT_SHOTS = 0;

    //LIFE variables

    public int lifes = 3;

    public Cloud(int x, int y){
        //super(new Texture(Gdx.files.internal("allies/cloud.png")));
        setPosition(x,y);
        setSize(48,48);
        int[] size3 = {16,16};
        animator = new Animator(new Texture(Gdx.files.internal("allies/cloud.png")),1,2,2,0.4f,size3);
        int[] size2 = {8,8};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Waterdrop.png")),1,2,2,0.8f,size2);
    }
    public void update(){
        move();
    }
    public void input(){
        if(isDead()) return;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            changeDir("R");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            changeDir("L");
        }
        else{
            changeDir("");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            shoot();
        }
    }
    public void draw(SpriteBatch batch){
        if(isDead()) return;
        //animator.drawResized(this,batch,48,48);
        animator.draw(this,batch);
    }
    public void move(){
        if(dirX.equals("L")){
            setX(getX()-SPEED);
            if(getX() < 0){
                setX(0);
            }
        }
        else if (dirX.equals("R")) {
            setX(getX()+SPEED);
            if(getX() + getWidth() > Principal.WIDTH){
                setX(Principal.WIDTH - getWidth());
            }
        }
        else if(dirX.equals("")){
            return;
        }
    }
    public void changeDir(String dir){
        if(dir.equals(dirX)){
            return;
        }

        dirX = dir;


    }
    public void shoot(){
        if(isDead()) return;
        if(CURRENT_SHOTS >= MAX_SHOTS) return;
        CURRENT_SHOTS++;
        Waterdrop wd = new Waterdrop(bulletAnimator, ((int) (getX()+getWidth()/3)), ((int) getY()));
        MainGame.bullets.add(wd);
    }
    public void getDamage(int dmg) {
        if(isDead()) return;
        CURRENT_HP -= dmg;
        if (CURRENT_HP <= 0) {
            CURRENT_HP = 0;
            lifes -=1;
            if(lifes == 0){
                MainGame.gameOver.start();
            }
            else{
                CURRENT_HP = HP;
            }

        }

    }
    public boolean isDead(){
        if(CURRENT_HP <= 0) return true;
        return false;
    }
    public TextureRegion getTextureRegion(){

        return animator.getStaticTextureRegion();
    }
}
