package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import utils.TimeManager;

/**
 * Created by victor on 4/14/18.
 */

public class GameOver {
    BitmapFont font;
    TimeManager time;
    private boolean enterPressed = false;
    public GameOver() {
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
        time = new TimeManager();
    }
    public void draw(SpriteBatch batch){
        font.draw(batch, "GAME OVER", Principal.WIDTH/3f, Principal.HEIGHT/1.8f);

    }
    public void start(){
        Gdx.app.log("START","!");
        Sound win = Gdx.audio.newSound(Gdx.files.internal("sound effects/lose.ogg"));
        win.play();
        time.setChronometer(10);
        time.start();
    }
    public void update(){


    }
    public void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

            enterPressed = true;
        }
    }
    public boolean canRestart(){
        return enterPressed || time.ring();
    }
}
