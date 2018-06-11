package movers;

import com.breco.claudy.Principal;

import enemies.Enemy;
import utils.MovementManager;

public class ZigzagMover extends Mover{
    private boolean zigzagVertical = true, zigzagHorizontal = true;
    private int zigzagX = 2,zigzagY = 3;
    public ZigzagMover(Enemy owner, String patron, float final_x, float final_y) {
        super(owner,patron,final_x,final_y);
        if(patron.contains("R")){
            start_x = ((int) (0 - owner.getWidth())); // 0 || Principal.WIDTH
            zigzagHorizontal = true;
        }
        else if(patron.contains("L")){
            zigzagHorizontal = false;
            start_x = Principal.WIDTH;
        }
        if(patron.contains("M")){
            start_y = Principal.HEIGHT/4;
        }
        else if(patron.contains("D")){
            start_y = Principal.HEIGHT/8;
        }
        owner.setPosition(start_x,start_y);
    }

    @Override
    public void move() {
        if((zigzagHorizontal && owner.getX() >= final_x) || (!zigzagHorizontal && owner.getX() <= final_x)){
            owner.setY(owner.getY()+ zigzagY);
            if(owner.getY() >= final_y){
                owner.setMover(MovementManager.getMover(owner,finalPatron, final_x, final_y));

            }
            return;
        }


        if(zigzagHorizontal){
            owner.setX(owner.getX()+zigzagX);
        }
        else{
            owner.setX(owner.getX()-zigzagX);
        }


        if(zigzagVertical){
            owner.setY(owner.getY()+zigzagY);
            if(owner.getY() >= final_y){
                zigzagVertical = !zigzagVertical;
            }
        }
        else{
            owner.setY(owner.getY()-zigzagY);
            if(owner.getY() <= start_y){
                zigzagVertical = !zigzagVertical;
            }
        }
    }
}
