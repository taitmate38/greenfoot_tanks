import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Boom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boom extends Actor
{
    /**
     * Act - do whatever the Boom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Earth earth;
    private GifImage gif;
    private SimpleTimer timer;
    
    public Boom() {
        gif = new GifImage("boom.gif");
        timer = new SimpleTimer();
        timer.mark();
    }
    
    public void act() 
    {
        setImage(gif.getCurrentImage());
        if (timer.millisElapsed() > 790) {
            earth.removeObject(this);
        }
    }   
    
    
    public void addedToWorld(World world) 
    { 
       earth = (Earth) world; 
    }
}
