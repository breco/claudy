package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by victor on 4/11/18.
 */

public class Enemies {
    private Array<Enemy> enemies;
    private Sound boom = Gdx.audio.newSound(Gdx.files.internal("sound effects/explosion-02.wav"));
    public Enemies() {
        enemies = new Array<Enemy>();


    }
    public void draw(SpriteBatch batch){
        for(Enemy enemy : enemies){
            enemy.draw(batch);
        }
    }
    public void update(){
        for(Enemy enemy : enemies){
            enemy.update();

        }
    }
    public void remove(Enemy enemy){
        enemies.removeValue(enemy, false);
        boom.play(0.5f);
    }
    public Array<Enemy> getEnemies(){
        return enemies;
    }
    public void add(Enemy enemy){
        enemies.add(enemy);
    }
    public int length(){
        int length = 0;
        for(Enemy enemy : enemies){
            if(enemy instanceof ThunderCloud) continue;
            length++;
        }
        return length;
    }
}
