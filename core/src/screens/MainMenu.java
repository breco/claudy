package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    public MainMenu(Principal game){
        this.game = game;
        cam = new OrthographicCamera(game.WIDTH, game.HEIGHT);
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2, 0);
        bg = new Texture(Gdx.files.internal("backgrounds/bg5.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, 0.5f);
        highscore = new HighScore();
        highscore.add(12550);

    }
    public void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            game.setScreen(new MainGame(1,game));
            dispose();
        }
    }

    public void update(){

    }

    public void draw(){

        game.batch.draw(bg,0,-50);
        font.draw(game.batch, "PUSH START BUTTON", Principal.WIDTH/3.5f, Principal.HEIGHT/1.8f);
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
    }
}
