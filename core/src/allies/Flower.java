package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public class Flower extends Ally {

    //STATS variables
    private int WP = 0; //Water Points
    private int MAX_WP = 5;


    public Flower(int x, int y){
        super(x,y);
        HP = 1;
        ATK = 0;
        points = 1000;
        CURRENT_HP = HP;
        int[] size = {16,16};
        setSize(42,42);
        animator = new Animator(new Texture(Gdx.files.internal("allies/Flower.png")),1,2,2,0.5f,size);
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("allies/FlowerDeath.png")),2,2,4,0.2f,size);
        dyingDuration = 0.3f;
    }
    @Override
    public void shoot() {

    }

    @Override
    public void update() {

        grow();

    }



    public void grow(){
        if(WP == MAX_WP){
            int[] size = {16,16};
            dyingDuration = 1.55f;
            dyingAnimator = new Animator(new Texture(Gdx.files.internal("transformations/FlowerToCactus.png")),2,4,8,0.2f,size);
            setDamage(100);

        }
    }

    public boolean isGrowing(){
        if(WP == MAX_WP){
            return true;
        }
        return false;
    }

    @Override
    public void getWater(int WP){
        this.WP += WP;
        if(this.WP >= MAX_WP){
            this.WP = MAX_WP;
        }
    }

    @Override
    public void move() {

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
