/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.Framework;

import flappybird.GameLogic.GameManager;
import flappybird.Main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import flappybird.GameObjects.*;
import java.awt.Font;
import java.util.ConcurrentModificationException;

/**
 * this is the part of the screen that you look at while playing and that
 * contains all gameObjects
 *
 * @author Joseph
 */
public class Game extends Canvas implements Runnable {
    /*  FIELDS   */

    private Thread thread = null;
    private boolean running = false;
    public BufferedImage backgroundImage;
    public static Handler handler = new Handler();
    public static int width, height;
    public Window window;
    public Input input;
    public Player player;
    
    public Game() {
        this.width = 700;
        this.height = 700;
        window = new Window(this);
        Setup();
        input = new Input(this);
        this.addKeyListener(input);             
    }

    /**
     * use this method to set starting objects etc
     */
    public void Setup() {
        handler.storage.add(new Wall(50, 500, 20, 200));
        handler.addLowerWall(200);
        handler.addUpperWall(300);
        player = new Player(100,100);
        handler.storage.add(player);             ///creates the main character
        GameManager.beginGame(this);
    }

    //core tick, tells all game Objects to tick
    private void tick() {
        try{
        handler.tick();
        if(input!=null)input.tick();
        }catch(ConcurrentModificationException cme){
            
        }
    }

    //core render method, tells all game Objects to render
    private void render() {
        try{
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) { ///run once at the start
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g = (Graphics2D) g;
        g.setColor(Color.GREEN);

        this.renderBackGround(g);
        handler.render(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif",Font.BOLD,25));
        g.drawString("SCORE: " + GameManager.score, 0, 25);
        g.setFont(new Font("Serif",Font.BOLD,15));
        g.drawString("Space: Jump", 0, width/2);
        g.drawString("A: Move Left", 0, width/2 + 15);
        g.drawString("D: Move Right", 0, width/2 + 30);
        g.dispose();

        bs.show();
        }catch(ConcurrentModificationException cme){
            
        }
    }

    /**
     * renders the background onto the game
     * @param g 
     */
    public void renderBackGround(Graphics g) {
        try {
            if (backgroundImage == null) {
                backgroundImage = ImageIO.read(new File(Main.getDir() + Main.assets + "Platformbg.png"));
            }
            g.drawImage(backgroundImage, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Core game loop 
    @Override
    public void run() {
        this.requestFocus(); ///automatically selects window so you dont have to click on it
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                this.render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
                ///this triggers once a second

            }
        }
        stop();
    }

    //starts the main game
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    ///stops the main game
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
