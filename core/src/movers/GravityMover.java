package movers;

import enemies.Enemy;

public class GravityMover extends Mover{
    public GravityMover(Enemy owner, String patron, float final_x, float final_y) {
        super(owner, patron, final_x, final_y);
        start_x = final_x;
        start_y = final_y;
        owner.setPosition(start_x,start_y);
    }

    @Override
    public void move() {
        owner.setY(owner.getY() - SPEED_Y);
    }
}
