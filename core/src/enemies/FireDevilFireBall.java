package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utils.Animator;

public class FireDevilFireBall extends Enemy{

    int radius = 170;
    float base_x,base_y;
    double degree;
    float SPEED_DEGREE = 0.033f;
    Enemy father;
    public FireDevilFireBall(float x, float y, float degree, float appearance, Enemy enemy){
        super((int) x,(int)y,3,1,appearance,10);
        int[] size = {32,32};
        animator = new Animator(new Texture(Gdx.files.internal("enemies/FireDevilFireBall.png")),1,2,2,0.3f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("enemies/FireDevilFireBallDefeat.png")),3,2,6,0.1f,size);
        setSize(64,64);
        base_x = x;
        base_y = y;
        this.degree = degree * Math.PI/180f;
        father = enemy;
        dyingDuration = 0.5f;

    }
    @Override
    public void shoot() {

    }

    @Override
    public void update() {
        move();
        degree+=SPEED_DEGREE;
        base_x = father.getX() + father.getWidth()/3;
        base_y = father.getY() + father.getHeight()/2;
    }

    @Override
    public void move() {
        setX((float) (base_x + radius * Math.cos(degree)));
        setY((float) (base_y + radius * Math.sin(degree)));
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
