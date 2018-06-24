package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.breco.claudy.Principal;

import bullets.Waterdrop;
import screens.MainGame;
import utils.Animator;
import utils.Counter;
import utils.TimeManager;

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
    Animator dying;
    TimeManager dyingTimer;
    //MOVE variables
    public float BASIC_SPEED = 3;
    public float CURRENT_SPEED = BASIC_SPEED;
    public String dirX;
    public String dirY;

    //SHOT variables
    private int BASIC_MAX_SHOTS = 3;
    private int MAX_SHOTS = BASIC_MAX_SHOTS;
    private int CURRENT_SHOTS = 0;
    private float BASIC_BULLET_SPD = 8;
    private float CURRENT_BULLET_SPD = BASIC_BULLET_SPD;
    int[] bulletSize = {8,8};

    //LIFE variables

    public int LIFES;
    private Counter impactCounter;
    private int inmunityTimer = 150;
    public int MAX_HP = 8;
    public int HP;

    //SPECIAL SHOT variables

    private int AP = 0;
    private int MAX_AP = 10;
    private Cirrus cirrus;



    public Cloud(int x, int y,String name, int width, int height){
        setPosition(x,y);
        setSize(width*3,height*3);
        int[] size3 = {width,height};
        animator = new Animator(new Texture(Gdx.files.internal("allies/cloud/"+name+".png")),1,2,2,0.4f,size3);
        dying = new Animator(new Texture(Gdx.files.internal("allies/cloud/"+name+"Defeat.png")),2,3,6,0.15f,size3);
        impactCounter = new Counter();
        Preferences prefs = Gdx.app.getPreferences("Preferences");
        LIFES = 1;
        HP = prefs.getInteger("HP");
    }
    public void update(){
        move();
        animation();
        impactCounter.update();


    }
    public void input(){
        if(isDead()) return;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            changeDirX("R");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            changeDirX("L");
        }
        else{
            changeDirX("");
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            changeDirY("U");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            changeDirY("D");
        }
        else{
            changeDirY("");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            shoot();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            setCirrus();
            releaseCirrus();
        }

    }
    public void draw(SpriteBatch batch){
        if(isDead()){
            if(dyingTimer == null || dyingTimer.ring()) return;
            dying.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }
    public void move(){
        if(dirX.equals("L")){
            setX(getX()-CURRENT_SPEED);
            if(getX() < 0){
                setX(0);
            }
        }
        else if (dirX.equals("R")) {
            setX(getX()+CURRENT_SPEED);
            if(getX() + getWidth() > Principal.WIDTH){
                setX(Principal.WIDTH - getWidth());
            }
        }
        if(dirY.equals("U")){
            setY(getY()+CURRENT_SPEED);
            if(getY() > Principal.HEIGHT - getHeight() - 50){
                setY(Principal.HEIGHT - getHeight() - 50);
            }
        }
        else if(dirY.equals("D")){
            setY(getY()-CURRENT_SPEED);
            if(getY() < Principal.HEIGHT/2){
                setY(Principal.HEIGHT/2);
            }
        }
    }
    public void changeDirX(String dir){
        if(dir.equals(dirX)){
            return;
        }
        dirX = dir;

    }
    public void changeDirY(String dir){
        if(dir.equals(dirY)){
            return;
        }
        dirY = dir;
    }

    public void setCirrus(){
        if(cirrus != null) return;
        if(AP == MAX_AP){

            AP = 0;
            cirrus = new Cirrus(((int) getX()), Principal.HEIGHT - 90);
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
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Waterdrop.png")),1,2,2, 0.3f, bulletSize);
        Waterdrop wd = new Waterdrop(bulletAnimator, ((int) (getX()+getWidth()/3)), ((int) getY()));
        wd.setSPEED(CURRENT_BULLET_SPD);
        MainGame.bullets.add(wd);
    }

    //SETTERS

    public void setCurrentShots(int amount){
        CURRENT_SHOTS += amount;
    }
    public void setMaxBulletShots(int amount){
        MAX_SHOTS = amount;
    }
    public void resetMaxBulletShots(){
        MAX_SHOTS = BASIC_MAX_SHOTS;
    }
    public void setBulletSPD(float speed){
        CURRENT_BULLET_SPD = speed;
    }
    public void resetBulletSPD(){
        CURRENT_BULLET_SPD = BASIC_BULLET_SPD;
    }
    public void setSPEED(float speed){
        CURRENT_SPEED = speed;
    }
    public void resetSPEED(){
        CURRENT_SPEED = BASIC_SPEED;
    }
    public void healHP(int amount){
        HP += amount;
        if(HP > MAX_HP){
            HP = MAX_HP;
        }
    }
    public void setDamage(int dmg) {

        if(isDead() || impactCounter.started()) return;
        impactCounter.setLimit(inmunityTimer);
        HP -= dmg;
        if (HP <= 0) {
            HP = 0;
            LIFES -=1;
            if(LIFES == 0){
                dyingTimer = new TimeManager();
                dyingTimer.setChronometer(0.85f);
                dyingTimer.start();
                MainGame.gameOver.start();
            }
            else{
                HP = MAX_HP;
            }

        }

    }



    public void setAP(int amount){
        if(cirrus != null) return;
        AP+=amount;
        if(AP >= MAX_AP){
            AP = MAX_AP;
        }

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




    // GETTERS

    @Override
    public Rectangle getBoundingRectangle(){
        return new Rectangle(getX()+getWidth()*0.1f,getY()+getHeight()*0.1f,getWidth()*0.8f,getHeight()*0.8f);
    }

    public boolean isDead(){
        if(HP <= 0 || MainGame.allies.length() == 0) return true;
        return false;
    }
    public int getAP(){
        return AP;
    }
    public int getMAX_AP(){
        return MAX_AP;
    }

    public int getHP() {
        return HP;
    }

    public int getMAX_HP() {
        return MAX_HP;
    }

    public boolean inmunity(){
        return impactCounter.started();
    }
}
