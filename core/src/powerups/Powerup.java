package powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import allies.Cloud;
import screens.MainGame;
import utils.Animator;

public abstract class Powerup extends Sprite {

    Animator animator;
    String dirX, dirY;
    int SPEED;
    float appearance;
    Sound sound;

    public Powerup(int x, int y, float appearance){
        setPosition(x,y);
        SPEED = 3;
        dirX = "R";
        dirY = "L";
        sound = Gdx.audio.newSound(Gdx.files.internal("sound effects/powerup.ogg"));
        this.appearance = appearance;

    }

    public abstract void update();
    public void activate(){
        sound.play();
    }
    public void move(){

        if(dirX.equals("R")){
            setX(getX()+SPEED);
            if(getX() > Principal.WIDTH - getWidth()){
                dirX = "L";
                setX(Principal.WIDTH - getWidth());
            }

        }
        else if(dirX.equals("L")){
            setX(getX()-SPEED);
            if(getX() < 0){
                dirX = "R";
                setX(0);
            }
        }
        if(dirY.equals("U")){
            setY(getY()+SPEED);
            if(getY() > Principal.HEIGHT-50 - getHeight()){
                dirY = "D";
                setY(Principal.HEIGHT-50 - getHeight());
            }
        }
        else if(dirY.equals("D")){
            setY(getY()-SPEED);
            if(getY() < 0){
                dirY = "U";
                setY(0);
            }
        }
    }
    public abstract void effect(Cloud cloud);
    public abstract void draw(SpriteBatch batch);

    public void attack(){
        if(MainGame.cloud.getBoundingRectangle().overlaps(getBoundingRectangle())){
            activate();
            effect(MainGame.cloud);
            return;
        }
    }
    public boolean onScreen(){
        if(appearance <= MainGame.time.getTime()) return true;
        return false;
    }
}
