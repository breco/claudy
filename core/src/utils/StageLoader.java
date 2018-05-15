package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import enemies.Enemies;
import enemies.Enemy;

public class StageLoader {
    public static class JsonEnemy {
        public String clase,moveType;
        public int posx,posy;
    }
    public void loadStage(Enemies finalEnemies) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JsonReader reader = new JsonReader();
        Json json = new Json();
        JsonValue base = reader.parse(Gdx.files.internal("stages/1-1.json"));
        JsonValue enemies = base.get("enemies");
        int i = 0;

        JsonEnemy t;

        while(enemies.get(i) != null){
            t = json.fromJson(JsonEnemy.class, enemies.get(i).toString());
            Class<?> clazz = Class.forName(t.clase);
            Constructor<?> ctor = clazz.getConstructor(int.class,int.class,String.class);
            finalEnemies.add((Enemy)ctor.newInstance(t.posx, t.posy,t.moveType));
            i++;
        }

    }



}





/*



public void loadLevel() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JsonReader reader = new JsonReader();
        Json json = new Json();
        JsonValue base = reader.parse(Gdx.files.internal("levels/"+game.prefs.getString("loadLevel")+".json"));

        JsonValue enemigos = base.get("enemies");
        JsonEnemy t;
        int i = 0;
        while(enemigos.get(i) != null){
            t = json.fromJson(JsonEnemy.class, enemigos.get(i).toString());
            Class<?> clazz = Class.forName(t.clase);
            Constructor<?> ctor = clazz.getConstructor(int.class,int.class,int.class);
            enemies.add((Enemy) ctor.newInstance(t.posx,MainGame.HEIGHT/t.posy,t.time));
            i++;
        }



        bg = new Background(new Texture(Gdx.files.internal("backgrounds/"+base.getString("background"))));
    }
 */