/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import flappybird.Framework.Game;
import flappybird.Main;
import java.util.ConcurrentModificationException;

/**
 * A game object is everything that will go in the game. it ticks (does somthing every frame)
 * it renders (displays itself) and has coordinates for its X and Y position.
 * also has an x velocity and a Y velocity variable as well has two arrays for sprites.
 * those arnt yet implemented however.
 * @author Joseph
 */
public abstract class GameObject {
    /*  FIELDS  */
    public BufferedImage[] SpritesR;     //where we store our sprites for right direction
    public BufferedImage[] SpritesL;     //where we store the sprites for left direction
    public int numFrames;               //number of frames in the animation            
    public int x , y, velX, velY, speed;       //X/Y coordinates and X/Y velocity
    public int width, height;                  //length and width
    public boolean fixed = false;              //if the object is effected by gravity or can be moved
    public String name;
    /**
     * constructor that takes two ints for x and y coordinates
     * @param x
     * @param y 
     */
    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void tick(){
        GOtick();
        for(int i=0; i < Math.abs(velX);i++){
            if(velX<0)x--;
            else if (velX>0)x++;
        }
        for(int i=0; i < Math.abs(velY);i++){
            if(velY<0)y--;
            else if (velY>0)y++;
        }
        
        if(this.y >= Game.height-this.height/2 || this.y <= 0){
           collide(null);  //collide with the floor/ceiling
        }
        x = Main.clamp(x, Game.width-width/2, 0);
        y = Main.clamp(y, Game.height-height/2, 0);
    }
    
    /**
     * this is the equivilent of the update method in unity. run once every frame.
     */
    public abstract void GOtick();
    
    /**
     * this is run once a frame and contains code that puts the object on screen.
     * @param g 
     */
    public abstract void render(Graphics g);
    
    /**
     * what happens when we collide with another game object, or with the floor(null)
     */
    public abstract void collide(GameObject go);

    public void destroy() {
        while(Game.handler.storage.contains(this)){
            try{
           // Game.handler.storage.set(Game.handler.storage.indexOf(this), null);
                Game.handler.storage.remove(this);
            }catch(ConcurrentModificationException cme){
                System.out.println("cme");
            }
        }
       
    }
}
