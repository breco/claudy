package bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import enemies.Enemy;
import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

/**
 * Created by victor on 4/23/18.
 */

public class Seed extends Bullet {
    boolean destroyed = false;
    public Seed(Animator animator, int x, int y ) {
        super(animator, x, y, ' ', 'U', 1, 6);
        setSize(16,16);
        type = "ally";
        int[] size2= {8,8};
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("bullets/SeedExplosion.png")),1,2,2,0.2f,size2);
        dyingDuration = 0.3f;
    }

    public void update(){
        move();
        attack();
        destroy();
    }
    public void attack(){
        if(destroyed) return;
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().overlaps(getBoundingRectangle())){
                enemy.setDamage(ATK);
                MainGame.bullets.removeForced(this);
                MainGame.highscore.add(enemy.points);
                return;
            }
        }
    }
    public void move(){
        if(destroyed) return;
        setY(getY()+SPD);
    }
    public void destroy(){
        if(destroyed) return;
        if(getY() >= Principal.HEIGHT/4 - getHeight()) {
            destroyed = true;
            dyingTimer = new TimeManager();
            dyingTimer.setChronometer(dyingDuration);
            dyingTimer.start();
            MainGame.bullets.remove(this);
        }
    }
    public void draw(SpriteBatch batch){
        if(destroyed){
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }
}
