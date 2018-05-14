package huds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import allies.Cloud;

/**
 * Created by victor on 4/14/18.
 */

public class CloudBar {
    private Cloud cloud;
    public CloudBar(Cloud cloud){
        this.cloud = cloud;
    }
    public void draw(SpriteBatch batch){
        for(int i = 0; i < cloud.LIFES; i++){
            batch.draw(cloud.getTextureRegion(),
                    Principal.WIDTH- cloud.getWidth()*(i+1),
                    Principal.HEIGHT-cloud.getHeight(),
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
