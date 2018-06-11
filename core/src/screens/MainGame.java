package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.breco.claudy.Principal;

import allies.Allies;
import allies.Cloud;
import bullets.Bullets;
import enemies.Enemies;
import huds.AttackPowerBar;
import huds.CloudBar;
import huds.GameOver;
import huds.HighScore;
import huds.StageCleared;
import powerups.Powerups;
import utils.StageLoader;
import utils.TimeManager;

/**
 * Created by victor on 5/9/17.
 */
public class MainGame implements Screen {
    OrthographicCamera cam;
    Principal game;

    //GAME LOGIC OBJECTS
    public static Bullets bullets;
    public static Enemies enemies;
    public static Allies allies;
    public static Powerups powerups;
    public static Cloud cloud;

    //HUD
    public static HighScore highscore;
    public Texture bg;
    public static GameOver gameOver;
    public CloudBar cloudBar;
    public StageCleared stageCleared;
    public AttackPowerBar attackPowerBar;

    //MUSIC variables

    public Music music;


    //UTIL variables

    public static TimeManager time;


    public MainGame(Principal game){
        this.game = game;
        cam = new OrthographicCamera(game.WIDTH, game.HEIGHT);
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2, 0);

        //GAME LOGIC OBJECTS

        bullets = new Bullets();
        enemies = new Enemies();
        allies = new Allies();
        powerups = new Powerups();

        //HUD
        highscore = new HighScore();
        gameOver = new GameOver();
        stageCleared = new StageCleared(enemies, 5);


        //TEST


        StageLoader loader = new StageLoader();
        loader.loadStage(this);
        cloudBar = new CloudBar(cloud);
        attackPowerBar = new AttackPowerBar(cloud);
        time = new TimeManager();
        time.start();
        music.setLooping(true);
        music.play();




    }
    public void input(){
        if(stageCleared.isCleared()){
            cloud.changeDirX("");
            cloud.changeDirY("");
            return;
        }
        cloud.input();
    }

    public void update(){
        if(cloud.isDead()){
            music.stop();
            gameOver.input();
            if(gameOver.canRestart()){
                game.setScreen(new MainMenu(game));
                dispose();
            }
            return;
        }
        stageCleared.update();
        if(stageCleared.isCleared()){
            music.stop();
            stageCleared.input();
            if(stageCleared.canNextStage()){
                game.setScreen(new MainGame(game));
                dispose();

            }
            return;
        }
        cloud.update();
        bullets.update();
        enemies.update();
        allies.update();
        powerups.update();
        //HUD

        attackPowerBar.update();
    }

    public void draw(){
        game.batch.draw(bg,0,-50);
        attackPowerBar.draw(game.batch);
        cloudBar.draw(game.batch);
        cloud.draw(game.batch);
        enemies.draw(game.batch);
        allies.draw(game.batch);
        powerups.draw(game.batch);
        highscore.draw(game.batch);
        if(cloud.isDead()){
            gameOver.draw(game.batch);
            return;
        }

        bullets.draw(game.batch);
        stageCleared.draw(game.batch);

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
        music.stop();
        music.dispose();
    }
}
