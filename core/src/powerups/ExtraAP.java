package powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import allies.Cloud;
import screens.MainGame;
import utils.Animator;

public class ExtraAP extends Powerup {

    int EXTRA_AP = 5;
    public ExtraAP(int x, int y,float appearance) {
        super(x, y,appearance);
        setSize(32,32);
        int[] size = {16,16};
        dirX = "R";
        dirY = "U";
        animator = new Animator(new Texture(Gdx.files.internal("powerups/AP_UP.png")),1,2,2,0.4f,size);
    }

    @Override
    public void update() {
        move();
        attack();
    }

    @Override
    public void effect(Cloud cloud) {
        cloud.setAP(EXTRA_AP);
        MainGame.powerups.remove(this);
    }

    @Override
    public void draw(SpriteBatch batch) {
        animator.draw(this,batch);
    }
}
