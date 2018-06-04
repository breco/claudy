package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import allies.Cloud;
import utils.Animator;

/**
 * Created by victor on 4/14/18.
 */

public class CloudBar {
    private Cloud cloud;
    Texture texture;
    Animator animator;
    public CloudBar(Cloud cloud){
        this.cloud = cloud;

        int[] size = {16,16};
        animator = new Animator(new Texture(Gdx.files.internal("allies/cloud/Cloud.png")),1,2,2,0.5f,size);
    }
    public void draw(SpriteBatch batch){
        for(int i = 0; i < cloud.LIFES; i++) {
            batch.draw(animator.getTextureRegion(),
                    Principal.WIDTH - 48 * (i + 1),
                    Principal.HEIGHT - 48,
                    0,
                    0,
                    16,
                    16,
                    2,
                    2,
                    0
            );
        }
        //draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation)

    }
    public void update(){

    }

}
