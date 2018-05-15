package allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.HeavyWaterdrop;
import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

public class Cirrus extends Ally{

    private Animator bulletAnimator;
    private TimeManager timer;
    Sound rain;
    public Cirrus(int x, int y) {
        super(x, y);
        HP = 10000;
        ATK = 0;
        CURRENT_HP = HP;
        int[] size = {16,16};
        setSize(32,32);
        animator = new Animator(new Texture(Gdx.files.internal("allies/Cirrus.png")),1,2,2,0.4f,size);
        int[] size2 = {16,16};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/Heavy Waterdrop.png")),1,2,2,0.5f,size2);
        timer = new TimeManager();
        rain = Gdx.audio.newSound(Gdx.files.internal("sound effects/rain.ogg"));

    }

    @Override
    public void shoot() {

    }

    @Override
    public void update() {
        if(timer.isStarted() && timer.ring()){
            MainGame.allies.remove(this);
            rain.stop();
        }
    }

    @Override
    public void move() {

    }

    @Override
    public void getWater(int WP) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        animator.draw(this,batch);
    }
    public void attack(){
        rain.loop();

        timer.setChronometer(2);
        timer.start();
        for(int i = 1; i< 14;i++){
            MainGame.bullets.add(new HeavyWaterdrop(bulletAnimator, ((int) getX()-8),i*48));
        }
    }
}
