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

    private Animator animatorTop,animatorMid,animatorBottom;
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

        animatorTop = new Animator(new Texture(Gdx.files.internal("bullets/SpecialW1.png")),1,2,2,0.2f,size);
        animatorMid = new Animator(new Texture(Gdx.files.internal("bullets/SpecialW2.png")),1,2,2,0.2f,size);
        animatorBottom = new Animator(new Texture(Gdx.files.internal("bullets/SpecialW3.png")),1,2,2,0.2f,size);
        timer = new TimeManager();
        rain = Gdx.audio.newSound(Gdx.files.internal("sound effects/rain.ogg"));
        int[] size3= {8,8};
        dyingAnimator = new Animator(new Texture(Gdx.files.internal("allies/CirrusDefeat.png")),1,2,2,0.2f,size3);
        dyingDuration = 0.3f;
    }

    @Override
    public void shoot() {

    }

    @Override
    public void update() {
        if(timer.isStarted() && timer.ring()){
            setDamage(HP);
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
        if(isDead()){
            dyingAnimator.draw(this,batch);
            return;
        }
        animator.draw(this,batch);
    }

    @Override
    public void dispose() {
        Gdx.app.log("DISPOSING","CIRRUS");
        rain.stop();
        rain.dispose();
    }

    public void attack(){
        rain.loop();
        timer.setChronometer(2);
        timer.start();
        for(int i = 0; i< 14;i++){
            if(i == 0){
                MainGame.bullets.add(new HeavyWaterdrop(animatorBottom, ((int) getX()-8),i*48));
            }
            else if(i == 13){
                MainGame.bullets.add(new HeavyWaterdrop(animatorTop, ((int) getX()-8),i*48));
            }
            else{
                MainGame.bullets.add(new HeavyWaterdrop(animatorMid, ((int) getX()-8),i*48));
            }

        }
    }
}
