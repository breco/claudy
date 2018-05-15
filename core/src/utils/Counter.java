package utils;

/**
 * Created by victor on 4/25/18.
 */

public class Counter {
    private int count;
    private int limit;
    private boolean finish;
    private boolean started;
    public Counter(){
        count = 0;
        limit = 1000000;
        finish = false;
        started = false;
    }
    public void setLimit(int limit){
        this.limit = limit;
        started = true;
    }
    public void update(){
        if(!started) return;
        count++;
        if(count > limit){
            finish = true;
        }
        if (check()) {
            reset();
        }
    }

    public void reset(){
        finish = false;
        count = 0;
        started = false;
        limit = 1000000;
    }
    public boolean check(){
        return finish;
    }
    public boolean started(){
        return started;
    }
    public int getCount(){
        return count;
    }
    public boolean ring(){
        if(count == limit){
            return true;
        }
        return false;
    }

}
