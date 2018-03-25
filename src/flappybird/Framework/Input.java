/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.Framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import flappybird.GameObjects.*;

/**
 *
 * @author Joseph
 */
public class Input implements KeyListener{
    //FIELDS
    public Game hostGame;
    public Player player;
    public Input(Game x){
        hostGame = x;
        player = x.player;
    }
    public void tick(){
 
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {       
        switch(e.getKeyCode()){
            case ' ':  //spacebar 
                player.jump();          
                break;
            case 'S': 
                player.velY = player.speed;
                break;
            case 'A':
                player.velX = player.speed*-1;
                break;
            case 'D':
                player .velX = player.speed;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
               switch(e.getKeyCode()){
            case 'W':    
                //nothing 
                break;
            case 'S':
             if(player.velY > 0)   player.velY = 0;
                break;
                   case 'A':
                if(player.velX < 0)player.velX = 0;
                break;
            case 'D':
              if(player.velX > 0) player .velX = 0;
                break;
        }
    }
    
}
