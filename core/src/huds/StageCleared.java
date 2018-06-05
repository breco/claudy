package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

import enemies.Enemies;
import screens.MainGame;
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
    Texture bg;
    int flowerBonus;
    //TEST
    int timeLimit;
    public StageCleared(Enemies enemies, int timeLimit) {
        this.timeLimit = timeLimit;
        this.enemies = enemies;
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
        time = new TimeManager();
        bg = new Texture(Gdx.files.internal("colors/Black Blue.png"));

    }
    public void draw(SpriteBatch batch){
        if(!cleared) return;
        batch.draw(bg,Principal.WIDTH/6,Principal.HEIGHT/5,Principal.WIDTH*2/3,Principal.HEIGHT/2);
        font.draw(batch, "STAGE CLEARED!", Principal.WIDTH/3f, Principal.HEIGHT*2/3f);
        font.draw(batch, "SCORE            "+MainGame.highscore.getScore(), Principal.WIDTH/6+50, Principal.HEIGHT*2/3f-100);
        font.draw(batch, "FLOWER BONUS     "+flowerBonus, Principal.WIDTH/6+50, Principal.HEIGHT*2/3f-150);
        font.draw(batch, "TOTAL            "+(MainGame.highscore.getScore()+flowerBonus), Principal.WIDTH/6+50, Principal.HEIGHT*2/3f-200);

    }
    public void start(){
        time.setChronometer(50);
        time.start();
    }
    public void update(){
        if(this.enemies.length() == 0 && !cleared && MainGame.time.getTime() >= timeLimit){
            Gdx.app.log("time.getTime():",MainGame.time.getTime()+"");
            Gdx.app.log("timeLimit:",timeLimit+"");
            start();
            cleared = true;
            flowerBonus = MainGame.allies.getFlowerbonus();
            Sound win = Gdx.audio.newSound(Gdx.files.internal("sound effects/win-4.mp3"));
            win.play();
            Preferences prefs = Gdx.app.getPreferences("Preferences");
            prefs.putInteger("stage",prefs.getInteger("stage")+1);
            prefs.putInteger("highscore",MainGame.highscore.getScore()+flowerBonus);
            prefs.putInteger("HP",MainGame.cloud.getHP());
            prefs.flush();
        }

    }
    public void input(){
        if(!cleared) return;
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            enterPressed = true;
        }
    }
    public boolean canNextStage(){
        return enterPressed || time.ring();
    }
    public boolean isCleared(){
        return cleared;
    }
}
