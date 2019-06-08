import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Nozzle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nozzle extends Actor
{
    /**
     * Act - do whatever the Nozzle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Nozzle(boolean right) {
        if(right) {
            getImage().mirrorHorizontally();
        }
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
