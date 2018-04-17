package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import enemies.Enemy;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/11/18.
 */

public class Flower extends Ally {
    public Flower(int x, int y){
        super(x,y);
        HP = 1;
        ATK = 0;
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
                getDamage(enemy.getATK());
                MainGame.enemies.remove(enemy);
                return;
            }
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
