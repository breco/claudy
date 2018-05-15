package utils;

/**
 * Created by victor on 4/14/18.
 */

public class TimeManager {
    private long startTime;
    private long totalPauseTime;
    private long pauseInit;
    private float chronometer;
    private boolean started;
    public TimeManager(){
        pauseInit = 0;
        totalPauseTime = 0;
        startTime = 0;
        started = false;
    }
    public void start(){
        //
        startTime = System.currentTimeMillis();
        started = true;
    }

    public float getTime(){
        //
        return (System.currentTimeMillis() - startTime - totalPauseTime)/1000f;
    }

    public void pause(){
        pauseInit = System.currentTimeMillis();
    }
    public void unpause(){
        totalPauseTime+= System.currentTimeMillis() - pauseInit;
    }
    public void reset(){
        startTime = 0;
        pauseInit = 0;
        totalPauseTime = 0;
    }
    public void setChronometer(float seconds){
        this.chronometer = seconds;
    }
    public boolean ring(){
        if(getTime()>= chronometer) return true;
        return false;
    }
    public boolean isStarted(){
        return started;
    }
}

