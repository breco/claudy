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
        rect = getBoundingRectangle();
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().overlaps(rect)){
                enemy.setDamage(ATK);
                MainGame.highscore.add(enemy.points);
                return;
            }
        }
        for (int i=0; i < MainGame.bullets.getBullets().size; i++) {
            if(MainGame.bullets.getBullets().get(i).getBoundingRectangle().overlaps(rect)){
                if(MainGame.bullets.getBullets().get(i) instanceof HeavyWaterdrop || MainGame.bullets.getBullets().get(i) instanceof Waterdrop){
                    continue;
                }
                MainGame.bullets.removeForced(MainGame.bullets.getBullets().get(i));
            }
        }

    }
    public void destroy(){
        if(timer.ring()){
            MainGame.bullets.removeForced(this);
        }
    }

}
