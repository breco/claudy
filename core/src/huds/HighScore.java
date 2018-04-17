package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.claudy.Principal;

/**
 * Created by victor on 4/11/18.
 */

public class HighScore {
    BitmapFont font;
    int score = 0;

    public HighScore() {
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
    }
    public void draw(SpriteBatch batch){
        font.draw(batch, "HIGH SCORE", Principal.WIDTH/3, Principal.HEIGHT-15);
        font.draw(batch, score+"", Principal.WIDTH/3+ Principal.WIDTH/16, Principal.HEIGHT-30);
    }
    public void add(int points){
        score+=points;
    }
}