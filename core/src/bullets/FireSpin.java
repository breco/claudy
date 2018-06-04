package bullets;

import screens.MainGame;
import utils.Animator;

public class FireSpin extends Bullet{
    float SPEED_X = 5f;
    public FireSpin(Animator animator, int x, int y, char oriX,int speed) {
        super(animator, x, y, oriX, 'U', 1, speed);
        setSize(20,20);
        type = "enemy";
    }
    public void update(){
        move();
        attack();
        destroy();

    }
    public void move(){
        if(oriX == 'L'){
            setX(getX()-SPEED_X);
        }
        else if(oriX == 'R'){
            setX(getX()+SPEED_X);
        }
        if(oriY == 'D'){
            setY(getY()-SPD);
        }
        else if(oriY == 'U'){
            setY(getY()+SPD);
        }

    }
    public void attack(){
        if(MainGame.cloud.getBoundingRectangle().overlaps(getBoundingRectangle())){
            MainGame.cloud.setDamage(ATK);
            MainGame.bullets.removeForced(this);
            return;
        }

    }
}
