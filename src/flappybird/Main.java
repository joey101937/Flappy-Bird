/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import flappybird.Framework.Game;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * Main class that runs the program and contains utility methods
 * @author Joseph
 */
public class Main {
/* FIELDS */
   public static String assets = "Assets" + File.separator;
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /****              UTILITY METHODS                ****/
    
    /**
     * returns the string directory of where the program was launched with a directory
     * separator on the end
     * @return the directory
     */
    public static String getDir() {
        String output = System.getProperty("user.dir") + File.separator;
        return output;
    }
    
        /**
     * displays a dialogue box with a text input field. And a question for the user.
     * returns the text area's contents.
     *
     * @param question
     * @return
     */
    public static String prompt(String question) {
        return JOptionPane.showInputDialog(question);
    }

    /**
     * displays a dialog box give the user a message, String s. pauses the
     * thread until the user hits OK
     *
     * @param s
     */
    public static void display(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
    
            /**
         * clamps the input to a given set of constraints and returns the max or min rather than the inputed value 
         * if the input is outside the given range
         * @param input the number to test
         * @param max maximum value
         * @param min minimum value
         * @return if the number is between max and min then return input, else return the min or max respectively
         */
    public static int clamp(int input, int max, int min) {
        if (input > max) {
            return input = max;
        }
        if (input < min) {
            return input = min;
        }
        return input;
    }
    
    
            /**
         * returns a random integer between the given parameters
         * @return the number
         */
        public static int generateRandom(int min, int max){
            if(min == max) return min; //if they are the same return that number
            if(max < min){
                //if the numbers are entered backwards, rerun the method with the correct order
                return generateRandom(max, min);
            }else{
                //here is the body of our method
                int diff = max - min;
                int output = (int)(Math.random()*diff); //generates a random number between 0 and the difference between the numbers
                return (min + output);                //returns that random number plus the min
            }
        }
    
}
