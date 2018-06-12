package movers;

import com.badlogic.gdx.Gdx;
import com.breco.claudy.Principal;

import enemies.Enemy;
import utils.MovementManager;

public class SpiralMover extends Mover {

    String dirX;
    int phase;
    float SPEED_X = 4;
    float r;
    double angle = -1.55;
    float angleHelper;
    float center_x, center_y;
    public SpiralMover(Enemy owner, String patron, float final_x, float final_y) {
        super(owner, patron, final_x, final_y);
        phase = 1;
        center_x = Principal.WIDTH/2;


        if(patron.contains("R")){
            start_x = ((int) (0 - owner.getWidth())); // 0 || Principal.WIDTH
            dirX = "R";
        }
        else if(patron.contains("L")){
            start_x = Principal.WIDTH;
            dirX = "L";
        }
        if(patron.contains("M")){
            start_y = Principal.HEIGHT/4;

        }
        else if(patron.contains("D")){
            start_y = Principal.HEIGHT/8;
        }
        r = (this.final_y - start_y)/2f;
        center_y = start_y + r;
        owner.setPosition(start_x,start_y);
    }

    @Override
    public void move() {
        switch(phase){
            case 1:
                if(dirX.equals("R")){
                    owner.setX(owner.getX() + SPEED_X);
                    if(owner.getX() >= Principal.WIDTH/2){
                        phase++;
                        center_x = owner.getX();
                        center_y = owner.getY() + r;
                        angleHelper=0;
                    }
                }
                else if(dirX.equals("L")){
                    owner.setX(owner.getX() - SPEED_X);
                    if(owner.getX() <= Principal.WIDTH/2) {
                        phase++;
                        center_x = owner.getX();
                        center_y = owner.getY() + r;
                        angleHelper=0;
                    }

                }
                break;
            case 2:
                if(dirX.equals("L")){
                    owner.setX(center_x + (float) (r*Math.cos(angle)));
                    owner.setY(center_y + (float) (r*Math.sin(angle)));
                    angle-= 0.04;


                }
                else if(dirX.equals("R")){
                    owner.setX(center_x + (float) (r*Math.cos(angle)));
                    owner.setY(center_y + (float) (r*Math.sin(angle)));
                    angle+= 0.04;

                }
                angleHelper += 0.04f;

                if(angleHelper >=  6.28f){
                    angle = -1.55;
                    angleHelper = 0;
                    phase++;
                }
                break;
            case 3:
                if(dirX.equals("R")){
                    owner.setX(owner.getX() + SPEED_X);
                    if(owner.getX() > Principal.WIDTH*3/4f){
                        center_x = owner.getX();
                        phase++;
                    }
                }
                else if(dirX.equals("L")){
                    owner.setX(owner.getX() - SPEED_X);
                    if(owner.getX() <= Principal.WIDTH/4){
                        center_x = owner.getX();
                        phase++;
                    }
                }
                break;
            case 4:
                if(dirX.equals("R")){

                    owner.setX(center_x + (float) (r*Math.cos(angle)));
                    owner.setY(center_y + (float) (r*Math.sin(angle)));
                    angle+= 0.04;


                }
                else if(dirX.equals("L")){
                    Gdx.app.log("phase","4");
                    owner.setX(center_x + (float) (r*Math.cos(angle)));
                    owner.setY(center_y + (float) (r*Math.sin(angle)));
                    angle-= 0.04;

                }
                angleHelper+=0.04f;
                if(angleHelper >= 3.1f){
                    phase++;
                }
                break;
            case 5:
                if(final_x > owner.getX()){
                    owner.setX(owner.getX() + SPEED_X);
                    if(owner.getX() >= final_x){
                        owner.setMover(MovementManager.getMover(owner,finalPatron,final_x,final_y));
                    }
                }
                if(final_x < owner.getX()){
                    owner.setX(owner.getX() - SPEED_X);
                    if(owner.getX() <= final_x){
                        owner.setMover(MovementManager.getMover(owner,finalPatron,final_x,final_y));
                    }
                }
                break;

        }
    }
}
