package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.breco.claudy.Principal;

import bullets.Waterdrop;
import screens.MainGame;
import utils.Animator;
import utils.Counter;

/**
 * Created by victor on 4/10/18.
 */

public class Cloud extends Sprite {


    //VISUAL variables
    public Animator animator;
    public Animator bulletAnimator;
    private boolean blink = false;



    //SPECIAL EFFECTS variables

    Sound pium = Gdx.audio.newSound(Gdx.files.internal("sound effects/shoot-02.wav"));


    //MOVE variables
    public int SPEED = 3;
    public String dirX;


    //SHOT variables

    public int MAX_SHOTS = 3;
    public int CURRENT_SHOTS = 0;

    //LIFE variables

    public int LIFES = 3;
    private Counter impactCounter;
    private int inmunityTimer = 150;
    public int HP = 1;
    public int CURRENT_HP = HP;

    //SPECIAL SHOT variables

    private int AP = 0;
    private int MAX_AP = 5;
    private Cirrus cirrus;



    public Cloud(int x, int y){
        setPosition(x,y);
        setSize(48,48);
        int[] size3 = {16,16};
        animator = new Animator(new Texture(Gdx.files.internal("allies/cloud.png")),1,2,2,0.4f,size3);
        int[] size2 = {8,8};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Waterdrop.png")),1,2,2,0.8f,size2);
        impactCounter = new Counter();
    }
    public void update(){
        move();
        animation();
        impactCounter.update();


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
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            setCirrus();
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            releaseCirrus();
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

    public void setCirrus(){
        if(cirrus != null) return;
        if(AP == MAX_AP){

            AP = 0;
            cirrus = new Cirrus(((int) getX()), ((int) (getY()+getHeight()*2)));
            MainGame.allies.add(cirrus);
        }
    }
    public void releaseCirrus(){
        if(cirrus == null) return;
        cirrus.attack();
        cirrus = null;
    }
    public void shoot(){
        if(isDead()) return;
        if(CURRENT_SHOTS >= MAX_SHOTS) return;
        pium.play(0.5f);
        CURRENT_SHOTS++;
        Waterdrop wd = new Waterdrop(bulletAnimator, ((int) (getX()+getWidth()/3)), ((int) getY()));
        MainGame.bullets.add(wd);
    }
    public void setDamage(int dmg) {

        if(isDead() || impactCounter.started()) return;
        impactCounter.setLimit(inmunityTimer);
        CURRENT_HP -= dmg;
        if (CURRENT_HP <= 0) {
            CURRENT_HP = 0;
            LIFES -=1;
            if(LIFES == 0){
                MainGame.gameOver.start();
            }
            else{
                CURRENT_HP = HP;
            }

        }

    }



    public void setAP(int amount){

        AP+=amount;
        if(AP >= MAX_AP){
            AP = MAX_AP;
        }

    }
    public boolean isDead(){
        if(CURRENT_HP <= 0 || MainGame.allies.length() == 0) return true;
        return false;
    }
    public TextureRegion getTextureRegion(){

        return animator.getStaticTextureRegion();
    }
    //ANIMATION METHODS

    public void animation(){
        if(impactCounter.started()){
            if(blink){
                blink = false;
                setColor(Color.WHITE);
            }
            else{
                setColor(Color.BLACK);
                blink = true;
            }
        }
        else{
            setColor(Color.WHITE);
        }
    }
    @Override
    public Rectangle getBoundingRectangle(){
        return new Rectangle(getX()+getWidth()*0.1f,getY()+getHeight()*0.1f,getWidth()*0.8f,getHeight()*0.8f);
    }

    // GETTERS

    public int getAP(){
        return AP;
    }
    public int getMAX_AP(){
        return MAX_AP;
    }
}
