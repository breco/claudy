package movers;

import enemies.Enemy;

public class StaticMover extends Mover{
    public StaticMover(Enemy owner, String patron, float final_x, float final_y) {
        super(owner, patron, final_x, final_y);
    }

    @Override
    public void move() {

    }
}
