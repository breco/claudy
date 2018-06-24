package powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import allies.Cloud;
import screens.MainGame;
import utils.Animator;

public class ExtraSPD extends Powerup{

    float EXTRA_SPD = 5;
    Cloud cloud;

    public ExtraSPD(int x, int y,float appearance) {
        super(x, y,appearance);
        setSize(32,32);
        int[] size = {16,16};
        dirX = "R";
        dirY = "U";
        animator = new Animator(new Texture(Gdx.files.internal("powerups/SPD_UP.png")),1,2,2,0.4f,size);
    }

    @Override
    public void update() {
        move();
        attack();
        if(used && timer.ring()){
            cloud.resetSPEED();
            MainGame.powerups.remove(this);
        }
    }

    @Override
    public void effect(Cloud cloud) {
        if(used) return;
        this.cloud = cloud;
        used = true;
        timer.start();
        timer.setChronometer(10);
        cloud.setSPEED(EXTRA_SPD);

    }

    @Override
    public void draw(SpriteBatch batch) {
        if(used) return;
        animator.draw(this,batch);
    }
}
