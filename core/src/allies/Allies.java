package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import screens.MainGame;

/**
 * Created by victor on 4/11/18.
 */

public class Allies {
    private Array<Ally> allies;
    public Allies() {
        allies = new Array<Ally>();

    }
    public void draw(SpriteBatch batch){
        for(Ally ally : allies){
            ally.draw(batch);
        }
    }
    public void update(){
        for(Ally ally : allies){
            ally.update();

        }
    }
    public void remove(Ally ally){
        allies.removeValue(ally, false);
        if(allies.size == 0){
            MainGame.gameOver.start();
        }
    }
    public Array<Ally> getAllies(){
        return allies;
    }
    public void add(Ally ally){
        allies.add(ally);
    }
    public int length(){
        return allies.size;
    }
    public int getFlowerbonus(){
        int bonus = 0;
        for(Ally ally : allies){
            Gdx.app.log("POITNS",""+ally.points);
            if(ally instanceof Flower || ally instanceof SuperFlower){

                bonus += ally.points;
            }
        }
        return bonus;
    }
}
