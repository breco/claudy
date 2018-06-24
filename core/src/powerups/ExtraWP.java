package powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import allies.Cloud;
import screens.MainGame;
import utils.Animator;

public class ExtraWP extends Powerup{
    int EXTRA_MAX_SHOTS = 5;
    float EXTRA_BULLET_SPD = 12;
    Cloud cloud;

    public ExtraWP(int x, int y,float appearance) {
        super(x, y,appearance);
        setSize(32,32);
        int[] size = {16,16};
        dirX = "R";
        dirY = "U";
        animator = new Animator(new Texture(Gdx.files.internal("powerups/WP_UP.png")),1,2,2,0.4f,size);
    }

    @Override
    public void update() {
        move();
        attack();
        if(used && timer.ring()){
            cloud.resetMaxBulletShots();
            cloud.resetBulletSPD();
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
        cloud.setMaxBulletShots(EXTRA_MAX_SHOTS);
        cloud.setBulletSPD(EXTRA_BULLET_SPD);

    }

    @Override
    public void draw(SpriteBatch batch) {
        if(used) return;
        animator.draw(this,batch);
    }
}
