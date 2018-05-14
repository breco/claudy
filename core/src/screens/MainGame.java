package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.breco.claudy.Principal;

import java.lang.reflect.InvocationTargetException;

import allies.Allies;
import allies.Cloud;
import allies.Flower;
import bullets.Bullets;
import enemies.Enemies;
import huds.CloudBar;
import huds.GameOver;
import huds.HighScore;
import huds.StageCleared;
import utils.StageLoader;

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
    public static Cloud cloud;

    //HUD
    public static HighScore highscore;
    public Texture bg;
    public static GameOver gameOver;
    public CloudBar cloudBar;
    public StageCleared stageCleared;


    //MUSIC variables

    Music music = Gdx.audio.newMusic(Gdx.files.internal("music/bgm1.ogg"));

    //SPECIAL EFFECTS variables

    //Sound winSound = Gdx.audio.newMusic(Gdx.files.internal("sound effects/win.wav"));
    //Music loseSound = Gdx.audio.newMusic(Gdx.files.internal("sound effects/lose.wav"));

    public MainGame(Principal game){
        this.game = game;
        cam = new OrthographicCamera(game.WIDTH, game.HEIGHT);
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2, 0);

        //GAME LOGIC OBJECTS

        bullets = new Bullets();
        enemies = new Enemies();
        allies = new Allies();

        //HUD
        highscore = new HighScore();
        bg = new Texture(Gdx.files.internal("backgrounds/bg2.png"));
        gameOver = new GameOver();
        stageCleared = new StageCleared(enemies);

        //TEST

        music.setLooping(true);
        music.play();
        cloud = new Cloud(game.WIDTH/2,game.HEIGHT-game.HEIGHT/4);
        cloudBar = new CloudBar(cloud);


        /*enemies.add(new Fire(100,300,"polar"));
        enemies.add(new Fire (300,200,"polar"));
        enemies.add(new Fire(500,300,"polar"));

        enemies.add(new FireEater(250,250,"static"));*/

        StageLoader loader = new StageLoader();
        try {
            loader.loadStage(enemies);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        /*enemies.add(new Fire(100,200,""));
        enemies.add(new Fire (200,200,""));
        enemies.add(new Fire (300,200,""));
        enemies.add(new Fire (400,200,""));
        enemies.add(new Fire (500,200,""));

        enemies.add(new Fire (100,240,""));
        enemies.add(new Fire (200,240,""));
        enemies.add(new Fire (300,240,""));
        enemies.add(new Fire (400,240,""));
        enemies.add(new Fire (500,240,""));

        enemies.add(new ThunderCloud(100,280,""));
        enemies.add(new Fire (200,280,""));
        enemies.add(new ThunderCloud(300,280,""));
        enemies.add(new Fire (400,280,""));
        enemies.add(new ThunderCloud (500,280,""));

        enemies.add(new Fire (100,320,""));
        enemies.add(new Fire (200,320,""));
        enemies.add(new Fire (300,320,""));
        enemies.add(new Fire (400,320,""));
        enemies.add(new Fire (500,320,""));

        enemies.add(new Fire (100,360,""));
        enemies.add(new Fire (200,360,""));
        enemies.add(new Fire (300,360,""));
        enemies.add(new Fire (400,360,""));
        enemies.add(new Fire (500,360,""));

        enemies.add(new DarkCloud(100,430,""));
        enemies.add(new DarkCloud(300,430,""));
        enemies.add(new DarkCloud(500,430,""));

        enemies.add(new DarkCloud(100,470,""));
        enemies.add(new DarkCloud(300,470,""));
        enemies.add(new DarkCloud(500,470,""));*/



        allies.add(new Flower(100-20,0));
        allies.add(new Flower(250-20,0));
        allies.add(new Flower(400-20,0));
        allies.add(new Flower(550-20,0));

    }
    public void input(){
        cloud.input();
    }

    public void update(){
        if(cloud.isDead()){
            gameOver.input();
            if(gameOver.canRestart()){
                //loseSound.setVolume(0.5f);
                //loseSound.play();
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
                //winSound.setVolume(0.5f);
                //winSound.play();
                game.setScreen(new MainMenu(game));
                dispose();
            }
        }
        cloud.update();
        bullets.update();
        enemies.update();
        allies.update();

        //HUD

    }

    public void draw(){
        game.batch.draw(bg,0,-50);
        cloud.draw(game.batch);
        bullets.draw(game.batch);
        enemies.draw(game.batch);
        allies.draw(game.batch);
        highscore.draw(game.batch);
        cloudBar.draw(game.batch);
        if(cloud.isDead()) gameOver.draw(game.batch);
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
