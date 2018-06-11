package powerups;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Powerups {
    private Array<Powerup> onScreenPowerups,powerups;
    public Powerups() {
        onScreenPowerups = new Array<Powerup>();
        powerups = new Array<Powerup>();
    }
    public void draw(SpriteBatch batch){

        for(Powerup powerup : onScreenPowerups){
            powerup.draw(batch);
        }


    }
    public void update(){

        for(Powerup powerup : powerups){
            if(powerup.onScreen() && !onScreenPowerups.contains(powerup,false)){
                onScreenPowerups.add(powerup);
                powerups.removeValue(powerup,false);
            }
            if(!powerup.onScreen() && onScreenPowerups.contains(powerup,false)){
                onScreenPowerups.removeValue(powerup,false);

            }
        }
        for(Powerup powerup : onScreenPowerups){
            powerup.update();
        }

    }
    public void removeForced(Powerup powerup){
        //
        onScreenPowerups.removeValue(powerup, false);
    }
    public void remove(Powerup powerup){
        //
        onScreenPowerups.removeValue(powerup, false);
    }
    public Array<Powerup> getPowerups(){
        return onScreenPowerups;
    }
    public void add(Powerup powerup){
        powerups.add(powerup);
    }
    public int length(){
        return onScreenPowerups.size;
    }

}
