package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import allies.Cloud;
import utils.Counter;

public class AttackPowerBar {
    private Cloud cloud;
    private BitmapFont font;
    private Texture white, lightBlue;
    private boolean blink = false;
    private boolean blinkSwitch = false;
    private Counter counter;
    public AttackPowerBar(Cloud cloud){
        this.cloud = cloud;
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
        white = new Texture(Gdx.files.internal("colors/White.png"));
        lightBlue = new Texture(Gdx.files.internal("colors/Light Blue.png"));
        counter = new Counter();
    }
    public void update(){
        counter.update();

        if(cloud.getAP() == cloud.getMAX_AP()){
            if(!blink){
                blink = true;
                counter.setLimit(10);
            }
            if(counter.ring()){

                blinkSwitch = !blinkSwitch;
                counter.reset();
                counter.setLimit(10);
            }
        }
        else{
            blink = false;
        }




    }
    public void draw(SpriteBatch batch){
        font.draw(batch, "AP", 10, Principal.HEIGHT-15);
        //font.draw(batch, score+"", Principal.WIDTH/3+ Principal.WIDTH/16, Principal.HEIGHT-30);
        batch.draw(white,10,Principal.HEIGHT-45,175,10);
        if(!blink){
            batch.draw(lightBlue,10,Principal.HEIGHT-45,cloud.getAP()*175f/cloud.getMAX_AP(),10);
        }
        else{
            if(blinkSwitch){
                batch.draw(lightBlue,10,Principal.HEIGHT-45,175,10);
            }

        }
    }
}
