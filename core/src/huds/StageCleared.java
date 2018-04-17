package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import enemies.Enemies;
import utils.TimeManager;

/**
 * Created by victor on 4/14/18.
 */

public class StageCleared {
    BitmapFont font;
    TimeManager time;
    private boolean enterPressed = false;
    Enemies enemies;
    private boolean cleared = false;
    public StageCleared(Enemies enemies){
        this.enemies = enemies;
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
        time = new TimeManager();
    }
    public void draw(SpriteBatch batch){
        if(this.enemies.length() != 0) return;
        font.draw(batch, "STAGE CLEARED!", Principal.WIDTH/3f, Principal.HEIGHT/1.8f);

    }
    public void start(){
        time.setChronometer(5);
        time.start();
    Gdx.app.log("TIME","STARTO!");
    }
    public void update(){
        if(this.enemies.length() == 0 && !cleared){
            start();
            cleared = true;
        }

    }
    public void input(){
        if(!cleared) return;
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            enterPressed = true;
        }
    }
    public boolean canNextStage(){
        Gdx.app.log("timering",time.ring()+"");
        return enterPressed || time.ring();
    }
    public boolean isCleared(){
        return cleared;
    }
}
