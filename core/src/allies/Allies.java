package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import enemies.BurningFlower;
import screens.MainGame;

/**
 * Created by victor on 4/11/18.
 */

public class Allies {
    private Array<Ally> allies,dying;
    public Allies() {
        allies = new Array<Ally>();
        dying = new Array<Ally>();
    }
    public void draw(SpriteBatch batch){
        for(Ally ally : allies){
            ally.draw(batch);
        }
        for(Ally ally : dying){
            ally.draw(batch);
        }
    }
    public void update(){
        for(Ally ally : dying){
            if(ally.dyingTimer != null && ally.dyingTimer.ring()){
                dying.removeValue(ally,false);
                if(ally instanceof Flower && ((Flower) ally).isGrowing()){
                    MainGame.allies.add(new CactusFlower(((int) ally.getX()), ((int) ally.getY())));
                }
                if(allies.size == 0 && dying.size == 0){
                    Gdx.app.log("allies.size==0",""+allies.size);
                    Gdx.app.log("dying.size==0",""+dying.size);
                    MainGame.gameOver.start();
                }

                if((ally instanceof Flower || ally instanceof CactusFlower) && ally.isEaten()){
                    MainGame.enemies.add(new BurningFlower(((int) ally.getX()), ((int) ally.getY()),""));
                    ally.setEaten(false);
                }
            }
        }


        for(Ally ally : allies){
            ally.update();

        }
    }
    public void removeForced(Ally ally){
        allies.removeValue(ally, false);
    }
    public void remove(Ally ally){
        allies.removeValue(ally, false);
        dying.add(ally);
    }
    public Array<Ally> getAllies(){
        return allies;
    }
    public void add(Ally ally){
        allies.add(ally);
    }
    public int length(){
        return allies.size + dying.size;
    }
    public int getFlowerbonus(){
        int bonus = 0;
        for(Ally ally : allies){
            if(ally instanceof Flower || ally instanceof CactusFlower){

                bonus += ally.points;
            }
        }
        return bonus;
    }
    public void dispose(){
        for(Ally ally : allies){
            ally.dispose();
        }
    }
}
