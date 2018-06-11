package movers;

import enemies.Enemy;

public class CircularMover extends Mover{
    double r = 50;
    double angle = 1.6;

    public CircularMover(Enemy owner, String patron, float final_x, float final_y) {
        super(owner, patron, final_x, final_y);
        this.final_y-=r;
    }

    @Override
    public void move() {
        owner.setX(final_x + (float) (r*Math.cos(angle)));
        owner.setY(final_y + (float) (r*Math.sin(angle)));
        angle+= 0.05;
    }
}
