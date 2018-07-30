package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.breco.claudy.Principal;

import huds.HighScore;

/**
 * Created by victor on 5/9/17.
 */
public class MainMenu implements Screen {
    OrthographicCamera cam;
    Principal game;


    //HUD
    public HighScore highscore;
    BitmapFont font;
    Texture bg;
    Sprite logo;
    public Music music;

    public MainMenu(Principal game){
        this.game = game;
        cam = new OrthographicCamera(game.WIDTH, game.HEIGHT);
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2, 0);
        bg = new Texture(Gdx.files.internal("backgrounds/bg5.png"));
        logo = new Sprite(new Texture(Gdx.files.internal("hud/CloudyDayLogo.png")));
        logo.setScale(2);
        logo.setPosition(225,Principal.HEIGHT - 250);
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
        highscore = new HighScore();

        music = Gdx.audio.newMusic(Gdx.files.internal("music/intro.ogg"));
        music.play();

    }
    public void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            Preferences prefs = Gdx.app.getPreferences("Preferences");
            prefs.putBoolean("new",true);
            game.setScreen(new MainGame(game));

            dispose();
        }
    }

    public void update(){

    }

    public void draw(){

        game.batch.draw(bg,0,-50);
        logo.draw(game.batch);
        font.draw(game.batch, "PRESS START BUTTON", Principal.WIDTH/3.5f, Principal.HEIGHT/1.8f);

        font.draw(game.batch, "MOVE             ARROW KEYS", Principal.WIDTH/5.5f, Principal.HEIGHT/3f);
        font.draw(game.batch, "NORMAL SHOT           SPACE", Principal.WIDTH/5.5f, Principal.HEIGHT/3.3f);
        font.draw(game.batch, "SPECIAL SHOT              Z", Principal.WIDTH/5.5f, Principal.HEIGHT/3.6f);
        highscore.draw(game.batch);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("FPS",""+Gdx.graphics.getFramesPerSecond());

        // 1)Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 2)Input handling
        input();

        // 3)Update system
        // 3.1)---> Update Cam

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        // 3.2)---> Update Game
        update();


        // 4)Draw
        game.batch.begin();
        draw();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        font.dispose();
        bg.dispose();
        music.dispose();
    }
}
