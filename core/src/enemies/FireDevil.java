package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.breco.claudy.Principal;

import screens.MainGame;
import utils.Animator;

public class FireDevil extends Enemy {
    String dir = "R";
    int SPEED_X = 2;
    public FireDevil(int x, int y,float appearance) {
        super(x, y, 100, 2, appearance, 10000);
        int[] size = {200,200};
        animator = new Animator(new Texture(Gdx.files.internal("enemies/FireDevil.png")),2,2,4,0.15f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/FireDevilDefeat.png")),3,3,9,0.3f,size);
        setSize(250,250);
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2, 0,0,this));
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2,120,0,this));
        MainGame.enemies.add(new FireDevilFireBall(getX()+getWidth()/3,getY()+getHeight()/2,240,0,this));
        dyingDuration = 0.3f;
    }

    @Override
    public void shoot() {

    }

    @Override
    public void update() {
        move();
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
}
