package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.Seed;
import enemies.Enemy;
import screens.MainGame;
import utils.Animator;

/**
 * Created by victor on 4/23/18.
 */

public class SuperFlower extends Ally {

    //SHOOT VARIABLES
    int shootTimer = 0;
    int shootInterval = 250;
    Animator bulletAnimator;

    public SuperFlower(int x, int y){
        super(x,y);
        HP = 1;
        ATK = 1;
        points = 10000;
        CURRENT_HP = HP;
        int[] size = {16,16};
        setSize(48,48);
        setColor(Color.YELLOW);
        animator = new Animator(new Texture(Gdx.files.internal("allies/Flower.png")),1,2,2,0.5f,size);

        int[] size2 = {8,8};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Waterdrop.png")),1,2,2,0.5f,size2);



    }


    @Override
    public void shoot() {
        shootTimer++;
        if(shootTimer == shootInterval){
            shootTimer = 0;
            MainGame.bullets.add(new Seed(bulletAnimator,((int)(getX()+getWidth()/2 - 8)), ((int) (getY()+getHeight()))));
        }
    }

    @Override
    public void update() {
        shoot();
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
    public void getWater(int WP) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        animator.draw(this,batch);
    }
    
    public void getDamage(int dmg) {

        CURRENT_HP -= dmg;
        if (CURRENT_HP <= 0) {
            CURRENT_HP = 0;
            MainGame.allies.remove(this);
            MainGame.allies.add(new Flower(((int) getX()), ((int) getY())));
        }

    }
}
