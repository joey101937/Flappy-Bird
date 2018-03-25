/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.Framework;

import flappybird.GameObjects.GameObject;
import flappybird.GameObjects.Wall;
import java.awt.Graphics;
import static java.lang.Math.E;
import java.util.LinkedList;

/**
 *
 * @author Joseph
 */
public class Handler {

    public LinkedList<GameObject> storage = new LinkedList<>();

    public void render(Graphics g) {
        for (GameObject go : storage) {
            if(go==null)continue;
            go.render(g);
        }
    }

    public void tick() {
        for (GameObject go : storage) {
            if(go==null)continue;
            go.tick();
        }
    }
    
    public Wall addUpperWall(int height){
        Wall wall = new Wall(Game.width,0,height,50);
        storage.add(wall);
        return wall;
    }
    public Wall addLowerWall(int height){
        Wall wall = new Wall(Game.width,Game.height-height,height,50);
        storage.add(wall);
        return wall;
    }
}
