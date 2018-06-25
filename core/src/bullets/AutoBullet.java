package bullets;

import com.badlogic.gdx.Gdx;

import screens.MainGame;
import utils.Animator;

public class AutoBullet extends Bullet{
    private float slope,intercept;
    private float t;
    private float speed;
    int base_x,base_y;
    private float l,m;
    public AutoBullet(Animator animator, int x, int y) {
        super(animator, x, y, ' ', ' ', 1, 3);
        base_x = x;
        base_y = y;
        setSize(16,16);
        type = "enemy";
        slope = (MainGame.cloud.getY() - y)/(MainGame.cloud.getX() - x);
        l = MainGame.cloud.getX() - x;
        m = MainGame.cloud.getY() - y;
        intercept = y - slope * x;

        t = 0;
        speed = 0.01f;
        Gdx.app.log("CLOUD",MainGame.cloud.getX()+","+MainGame.cloud.getY());
        Gdx.app.log("Dark",x+","+y);
        Gdx.app.log("l,m",l+","+m);
    }
    public void update(){
        move();
        attack();
        destroy();
        //
    }
    public void move(){
        setX(base_x+l*t);
        setY(base_y+m*t);
        t+=speed;
    }
    public void attack(){
        if(!MainGame.cloud.inmunity() && MainGame.cloud.getBoundingRectangle().overlaps(getBoundingRectangle())){
            MainGame.cloud.setDamage(ATK);
            MainGame.bullets.removeForced(this);
            return;
        }

    }
}
