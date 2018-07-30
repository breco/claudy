package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.breco.claudy.Principal;

import allies.CactusFlower;
import allies.Cloud;
import allies.Flower;
import enemies.DarkCloud;
import enemies.Fire;
import enemies.FireDevil;
import enemies.FireEater;
import enemies.ThunderCloud;
import powerups.ExtraAP;
import powerups.ExtraHP;
import powerups.ExtraSPD;
import powerups.ExtraWP;
import screens.MainGame;

public class StageLoader {

    /*public void loadStage(Enemies finalEnemies) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JsonReader reader = new JsonReader();
        Json json = new Json();
        JsonValue base = reader.parse(Gdx.files.internal("stages/test.json"));
        JsonValue enemies = base.get("enemies");
        int i = 0;

        JsonEnemy t;

        while(enemies.get(i) != null){
            t = json.fromJson(JsonEnemy.class, enemies.get(i).toString());
            Class clazz = java.lang.Class.forName(t.clase);
            Constructor ctor = clazz.getConstructor(int.class,int.class,String.class);
            finalEnemies.add((Enemy)ctor.newInstance(t.posx, t.posy,t.moveType));
            i++;
        }

    }*/


    public void loadStage(MainGame game){
        JsonReader reader = new JsonReader();

        String stage = Gdx.app.getPreferences("Preferences").getString("stage");

        JsonValue base = reader.parse(Gdx.files.internal("stages/"+stage+".json"));
        JsonValue enemies = base.get("enemies");
        int i = 0;

        JsonValue cloud = base.get("cloud");
        game.cloud = new Cloud(Principal.WIDTH/2,Principal.HEIGHT-Principal.HEIGHT/4,cloud.get("name").asString(),cloud.get("width").asInt(),cloud.get("height").asInt());

        game.bg = new Texture("backgrounds/"+Gdx.files.internal(base.get("background").asString())+".png");

        game.music = Gdx.audio.newMusic(Gdx.files.internal("music/"+base.get("ost").asString()+".ogg"));

        JsonEnemy t = new JsonEnemy();


        while(enemies.get(i) != null){


            t.clase = enemies.get(i).get("clase").asString();
            t.posx = enemies.get(i).get("posx").asInt();
            t.posy = enemies.get(i).get("posy").asInt();
            t.moveType = enemies.get(i).get("moveType").asString();
            t.appearance = enemies.get(i).get("appearance").asFloat();


            if(t.clase.equals("enemies.FireEater")){
                game.enemies.add(new FireEater(t.posx,t.posy,t.moveType,t.appearance));
            }
            else if(t.clase.equals("enemies.ThunderCloud")){
                game.enemies.add(new ThunderCloud(t.posx,t.posy,t.moveType,t.appearance));
            }
            else if(t.clase.equals("enemies.FireNormal")){
                game.enemies.add(new Fire(t.posx,t.posy,t.moveType,t.appearance));
            }
            else if(t.clase.equals("enemies.DarkCloud")){
                game.enemies.add(new DarkCloud(t.posx,t.posy,t.moveType,t.appearance));
            }
            else if(t.clase.equals("enemies.FireDevil")){
                game.enemies.add(new FireDevil(t.posx,t.posy,t.appearance));
            }
            i++;
        }


        JsonValue allies = base.get("allies");
        i = 0;
        while(allies.get(i) != null){
            t.clase = allies.get(i).get("clase").asString();
            t.posx = allies.get(i).get("posx").asInt();
            t.posy = allies.get(i).get("posy").asInt();
            if(t.clase.equals("allies.Flower")){
                game.allies.add(new Flower(t.posx,t.posy));
            }
            else if(t.clase.equals("allies.CactusFlower")){
                game.allies.add(new CactusFlower(t.posx,t.posy));
            }
            i++;
        }

        JsonValue powerups = base.get("powerups");
        i = 0;
        while(powerups.get(i) != null){
            t.clase = powerups.get(i).get("clase").asString();
            t.posx = powerups.get(i).get("posx").asInt();
            t.posy = powerups.get(i).get("posy").asInt();
            t.appearance = powerups.get(i).get("appearance").asFloat();
            if(t.clase.equals("powerups.ExtraHP")){
                game.powerups.add(new ExtraHP(t.posx,t.posy,t.appearance));
            }
            if(t.clase.equals("powerups.ExtraAP")){
                game.powerups.add(new ExtraAP(t.posx,t.posy,t.appearance));
            }
            if(t.clase.equals("powerups.ExtraSPD")){
                game.powerups.add(new ExtraSPD(t.posx,t.posy,t.appearance));
            }
            if(t.clase.equals("powerups.ExtraWP")){
                game.powerups.add(new ExtraWP(t.posx,t.posy,t.appearance));
            }
            i++;
        }

    }

}

