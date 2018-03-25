/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.GameLogic;

import flappybird.Framework.Game;
import flappybird.GameObjects.Wall;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class that maintains game logic as far as levels and obsticals
 * @author Joseph
 */
public class GameManager implements Runnable{
    /*   FIELDS   */
    public static Game game = null;
    public static int score = 0;
    
    
    public static void beginGame(Game game){
        GameManager.game = game;
        Thread t = new Thread(new GameManager());
        t.start();
    }

    @Override
    public void run() {
        while(true){
            this.wait(1000);
           GameManager.score +=10;
           Wall lower = Game.handler.addLowerWall((int)(Math.random()*300));
           Wall upper = Game.handler.addUpperWall((int)(Math.random()*300));
        }
    }
    
    /**
     * thread sleep with built in try/catch
     * @param duration 
     */
    private void wait(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
