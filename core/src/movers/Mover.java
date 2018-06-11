package movers;

import com.badlogic.gdx.Gdx;

import enemies.Enemy;

public abstract class Mover {
    float start_x, start_y, final_x, final_y;
    Enemy owner;
    String patron, finalPatron;
    float SPEED_X,SPEED_Y;
    public Mover(Enemy owner, String patron,float final_x, float final_y){
            this.final_x = final_x;
            this.final_y = final_y;
            this.owner = owner;
            this.patron = patron;
            if(patron.contains("G")){
                finalPatron = "gravity";
            }
            else if(patron.contains("C")){
                finalPatron = "circular";
            }
            else if(patron.contains("N")){
                finalPatron = "normal";
            }

    }
    public void setSpeed(float x, float y){

        this.SPEED_X = x;
        this.SPEED_Y = y;
        Gdx.app.log("SET SPEED",this.SPEED_X+","+this.SPEED_Y);
    }
    public abstract void  move();
}
