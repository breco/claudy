package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.breco.claudy.Principal;

import allies.Ally;
import screens.MainGame;
import utils.Animator;

public class LittleFire extends Enemy {
    int shootInterval,shotTimer;
    boolean fired = false;
    String replica;
    public LittleFire(int x, int y,String moveType,float appearance){
        super(x,y,1,1,appearance,10);
        replica = moveType;
        shotTimer = 0;
        shootInterval = 50;
        int[] size = {8,8};
        setSize(16,16);
        animator = new Animator(new Texture(Gdx.files.internal("bullets/Fireball.png")),1,2,2,0.5f,size);
    }

    public void attack(){
        for(Ally ally : MainGame.allies.getAllies()){
            if(ally.getBoundingRectangle().overlaps(getBoundingRectangle())){
                ally.setDamage(getATK());
                return;
            }
        }

    }

    @Override
    public void shoot() {
        if(fired) return;
        shotTimer++;
        if(shotTimer >= shootInterval){
            fired = true;
            LittleFire lf;
            if(replica.contains("R") && getX() + getWidth() <= Principal.WIDTH - 16){
                lf = new LittleFire((int) (getX()+getWidth()),0,"R",0);
                if(lf.canLive()){
                    MainGame.enemies.add(lf);
                }
            }
            if(replica.contains("L") && getX()-getWidth() >= 16){
                lf = new LittleFire((int) (getX()-getWidth()),0,"L",0);
                if(lf.canLive()){
                    MainGame.enemies.add(lf);
                }
            }



        }
    }

    @Override
    public void update() {
        shoot();
        attack();
    }

    @Override
    public void move() {

    }

    @Override
    public void draw(SpriteBatch batch) {
        animator.draw(this,batch);
    }

    public boolean canLive(){
        Rectangle rect = getBoundingRectangle();
        for (int i=0; i < MainGame.enemies.getEnemies().size; i++) {
            if(MainGame.enemies.getEnemies().get(i).getBoundingRectangle().overlaps(rect)){
                return false;
            }
        }
        return true;
    }
}
