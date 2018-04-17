package allies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

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
    }
    public Array<Ally> getAlly(){
        return allies;
    }
    public void add(Ally ally){
        allies.add(ally);
    }
}
