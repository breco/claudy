package bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by victor on 4/11/18.
 */

public class Bullets {
    private Array<Bullet> bullets,dying;
    public Bullets() {
        bullets = new Array<Bullet>();
        dying = new Array<Bullet>();

    }
    public void draw(SpriteBatch batch){
        for(Bullet bullet : bullets){
            bullet.draw(batch);
        }
        for(Bullet bullet : dying){
            bullet.draw(batch);
        }
    }
    public void update(){
        for(Bullet bullet : dying){
            if(bullet.dyingTimer != null && bullet.dyingTimer.ring()){
                dying.removeValue(bullet,false);
            }
        }
        for(Bullet bullet : bullets){
            bullet.update();

        }
    }
    public void removeForced(Bullet bullet){
        bullets.removeValue(bullet, false);
    }
    public void remove(Bullet bullet){
        bullets.removeValue(bullet, false);
        dying.add(bullet);

    }
    public Array<Bullet> getBullets(){
        return bullets;
    }
    public void add(Bullet bullet){
        bullets.add(bullet);
    }
}
