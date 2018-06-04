package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

/**
 * Created by victor on 4/11/18.
 */

public class HighScore {
    private BitmapFont font;
    private int score;

    public HighScore() {

        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);

        Preferences prefs = Gdx.app.getPreferences("Preferences");
        if(prefs.getBoolean("new")){
            score = 0;
            prefs.putBoolean("new",false);
        }
        else{
            score = prefs.getInteger("highscore");
        }


    }
    public void draw(SpriteBatch batch){
        font.draw(batch, "HIGH SCORE", Principal.WIDTH/3, Principal.HEIGHT-15);
        font.draw(batch, score+"", Principal.WIDTH/3+ Principal.WIDTH/16, Principal.HEIGHT-30);
    }
    public void add(int points){
        score+=points;
    }
    public int getScore(){return score;}

}