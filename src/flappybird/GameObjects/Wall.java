/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.GameObjects;

import flappybird.Framework.Game;
import java.awt.Color;
import java.awt.Graphics;

/**
 * this is an example gameObject. the tick does nothing and its render is to simply create a rectangle where it is.
 * the rectangle's upper left corner corresponds to the x and y coordinates of the wall object.
 * @author Joseph
 */
public class Wall extends GameObject{
    /*   FIELDS   */
    public boolean growing = false;
    //CONSTRUCTOR
    public Wall(int x, int y, int height, int length){
      super(x,y); //this calls the main game object constructor with using x and y as parameters.
      this.width = length;
      this.height = height;
      this.fixed = true;
      name = "Wall";
    }
    
    @Override
    public void GOtick() { 
        if(growing) height++;
        velX = -3;
        if(x==0){
            this.destroy();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(x, y, width, height); //tells graphics to fill a rectangle starting at (x,y) and using our width and height on the canvas
    }

    @Override
    public void collide(GameObject go) {
        //TODO, WALL COLLISION
    }
    
}
