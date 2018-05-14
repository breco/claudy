package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import enemies.Enemy;
import enemies.FireEater;
import screens.MainGame;
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
        points = 5000;
        CURRENT_HP = HP;
        int[] size = {16,16};
        setSize(48,48);
        animator = new Animator(new Texture(Gdx.files.internal("allies/Flower.png")),1,2,2,0.5f,size);
    }
    @Override
    public void shoot() {

    }

    @Override
    public void update() {
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().overlaps(getBoundingRectangle())){
                if (enemy instanceof FireEater){
                    MainGame.enemies.remove(enemy);
                    MainGame.allies.remove(this);
                    //MainGame.enemies.add();
                    break;
                }
                getDamage(enemy.getATK());
                MainGame.enemies.remove(enemy);
                return;
            }
        }
        grow();
    }


    public void grow(){
        if(WP == MAX_WP){
            MainGame.allies.remove(this);
            MainGame.allies.add(new SuperFlower(((int) this.getX()), ((int) this.getY())));
        }
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
        animator.draw(this,batch);
    }
}
