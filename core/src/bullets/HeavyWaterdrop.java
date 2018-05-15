package bullets;

import enemies.Enemy;
import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

public class HeavyWaterdrop extends Bullet {
    private TimeManager timer;
    public HeavyWaterdrop(Animator animator, int x, int y) {
        super(animator, x, y, ' ', ' ', 100, 0);
        timer = new TimeManager();
        timer.setChronometer(2);
        timer.start();
        setSize(48,48);

    }
    public void update(){
        attack();
        destroy();
    }
    public void attack(){
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().overlaps(getBoundingRectangle())){
                enemy.getDamage(ATK);
                MainGame.highscore.add(enemy.points);
                return;
            }
        }
    }
    public void destroy(){
        if(timer.ring()){
            MainGame.bullets.remove(this);
        }
    }

}
