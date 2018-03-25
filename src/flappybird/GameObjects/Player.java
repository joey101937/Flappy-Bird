/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.GameObjects;

import flappybird.Framework.Game;
import flappybird.GameLogic.GameManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import flappybird.Main;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class Player extends GameObject{
/*  FIELDS  */
private int jumpTimer = 0; //while nonzero, we are jumping
private int terminalVelocity = 10;//max fall speed   
private int lastRenderedFrame = 0;
private int frameDuration = 0; //how to render the frame
    public Player(int x, int y) {
        super(x, y);
        height = 100;
        width = 100;
        this.speed = 4;
        name = "Player";
        try {
            //initialize sprite arrays then populate them with a single sprite for testing purposes
            SpritesR = new BufferedImage[9];
            SpritesL = new BufferedImage[1];
            //SpritesR[0] = ImageIO.read(new File(Main.assets + "RightSprite.png"));
            for(int i =0; i< 9;i++){
                SpritesR[i] = ImageIO.read(new File(Main.assets + "birdies"+i+".png"));
            }
            SpritesL[0] = ImageIO.read(new File(Main.assets + "LeftSprite.png"));
            height = SpritesR[0].getHeight();
            width = SpritesR[0].getWidth();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public void GOtick() {
        checkCollision();
        if(jumpTimer>0){
            //jumping
            jumpTimer--;
            if(jumpTimer > 5){
                velY-=4;
            }
        }
        if(jumpTimer==0)applyGravity();
    }

    @Override
    public void render(Graphics g) {
        //display either the left of the right sprite
       // g.fillRect(x-width/2, y-height/2, width, height);
        BufferedImage toDraw = SpritesR[lastRenderedFrame];
        if(lastRenderedFrame == SpritesR.length-1) {
            lastRenderedFrame =0;
        }
        else {
            if(frameDuration == 0){
                lastRenderedFrame++;
                frameDuration=25;
            }else{
                 frameDuration--;
            }
        }
        if(velX >= 0){
            g.drawImage(toDraw, x-width/2, y-height/2, null); //if going right, display right sprite
        }else if(velX < 0){
             g.drawImage(toDraw, x-width/2, y-height/2, null); //if going left, display left
        }else{
           g.drawImage(SpritesR[0], x-width/2, y-height/2, null);  //if stopped, default to right
        }
        
    }

    @Override
    public void collide(GameObject go) {
       if(go == null){ //hitting the floor
           if(this.velY > 16){
               System.out.println(velY + " vely");
               this.velY = -5; //if we hit hard, bounce a bit
           }else{
               System.out.println("hit something");
               this.velY = 0;
           }          
       }
    }
    
    public void jump(){
        velY=0;
        jumpTimer = 8;
    }

    private void applyGravity(){
        if(this.y < Game.height){
            if(!fixed && velY<terminalVelocity)velY++;    //falling
        }
    }
/**
 * checks if we hit a wall and does things accordingly
 */
   private void checkCollision(){
       for(GameObject go: Game.handler.storage){
           Wall w;
           if(go.name.equals("Wall")){
               w = (Wall)go;
           }else{
               continue;
           }
           if(w.x < x && w.x+w.width > x){ //we are in bounds of the wall on the x axis
               if(w.y<y && w.y+w.height > y){//in bounds on y axis
                   gameOver();
                   return;
               }
           }
       }
    }
   
   public void gameOver(){
       JOptionPane.showMessageDialog(null, "Game Over!\nScore: " + GameManager.score);
       System.exit(0);
   }
}
