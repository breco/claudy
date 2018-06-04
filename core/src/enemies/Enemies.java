package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by victor on 4/11/18.
 */

public class Enemies {
    private Array<Enemy> onScreenEnemies,dying;
    private Array<Enemy> enemies,escapedEnemies,deadEnemies;
    private Sound boom = Gdx.audio.newSound(Gdx.files.internal("sound effects/explosion-02.wav"));
    public Enemies() {
        enemies = new Array<Enemy>();
        onScreenEnemies = new Array<Enemy>();
        dying = new Array<Enemy>();
        onScreenEnemies = new Array<Enemy>();
        escapedEnemies = new Array<Enemy>();
        deadEnemies = new Array<Enemy>();
    }
    public void draw(SpriteBatch batch){
        for(Enemy enemy : onScreenEnemies){
            enemy.draw(batch);
        }
        for(Enemy enemy : dying){
            enemy.draw(batch);
        }
    }
    public void update(){
        for(Enemy enemy : dying){
            if(enemy.dyingTimer != null && enemy.dyingTimer.ring()){
                dying.removeValue(enemy,false);
                deadEnemies.add(enemy);
            }
        }
        for(Enemy enemy : enemies){
            if(enemy.onScreen() && !onScreenEnemies.contains(enemy,false) && !deadEnemies.contains(enemy,false)){
                //Gdx.app.log("ENEMY ENTERS THE SCREEN",""+enemy.appearance);
                onScreenEnemies.add(enemy);
                enemies.removeValue(enemy,false);
            }
            if(!enemy.onScreen() && onScreenEnemies.contains(enemy,false) && !escapedEnemies.contains(enemy,false)){
                onScreenEnemies.removeValue(enemy,false);
                escapedEnemies.add(enemy);
            }
        }
        for(Enemy enemy : onScreenEnemies){
            enemy.update();
        }
    }
    public void remove(Enemy enemy){
        onScreenEnemies.removeValue(enemy, false);
        dying.add(enemy);
        boom.play(0.5f);

    }
    public void removeForced(Enemy enemy){
        onScreenEnemies.removeValue(enemy,false);
        deadEnemies.add(enemy);
    }
    public Array<Enemy> getEnemies(){
        return onScreenEnemies;
    }
    public void add(Enemy enemy){
        enemies.add(enemy);
    }
    public int length(){
        int length = 0;
        for(Enemy enemy : onScreenEnemies){
            if(enemy instanceof ThunderCloud) continue;
            length++;
        }
        return length + dying.size;
    }
}
