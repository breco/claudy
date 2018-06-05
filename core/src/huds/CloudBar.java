package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import allies.Cloud;
import utils.Animator;

/**
 * Created by victor on 4/14/18.
 */

public class CloudBar {
    private Cloud cloud;
    Animator animator;
    private BitmapFont font;
    private Texture white, lightBlue;

    public CloudBar(Cloud cloud){
        this.cloud = cloud;
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
        white = new Texture(Gdx.files.internal("colors/White.png"));
        lightBlue = new Texture(Gdx.files.internal("colors/Light Blue.png"));
        int[] size = {16,16};
        animator = new Animator(new Texture(Gdx.files.internal("allies/cloud/Cloud.png")),1,2,2,0.5f,size);
    }
    public void draw(SpriteBatch batch){
        font.draw(batch, "HP "+cloud.getHP()+"/"+cloud.getMAX_HP(), Principal.WIDTH*0.67f, Principal.HEIGHT-15);
        batch.draw(white,Principal.WIDTH*0.67f,Principal.HEIGHT-45,175,10);
        batch.draw(lightBlue,Principal.WIDTH*0.67f,Principal.HEIGHT-45,cloud.getHP()*175f/cloud.getMAX_HP(),10);


        /*for(int i = 0; i < cloud.getHP(); i++) {
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
        }*/

        //draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation)

    }
    public void update(){

    }

}
