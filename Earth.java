import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Earth extends World
{
    /**  */
    /** Gravity on planet */
    private double gravity = 1.6;
    
    /** Color of sky */
    private Color skyColor = Color.BLUE;
    
    /** Ground */
    private Ground ground;
    
    /** tank1 */
    private Tank tank1;
    private Nozzle nozzle1;
    
    /** tank2 */
    private Tank tank2;
    private Nozzle nozzle2;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Earth()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        setPaintOrder(Boom.class,Tank.class,Nozzle.class,Shell.class);
        
        ground = new Ground();
        addObject(ground,300,500);
        
        nozzle1 = new Nozzle(false);
        addObject(nozzle1,400,ground.getShiftRelY(400,15));
        tank1 = new Tank(ground,nozzle1,false);
        addObject(tank1, 400,ground.getShiftRelY(400,15));
        
        nozzle2 = new Nozzle(true);
        addObject(nozzle2,200,ground.getShiftRelY(200,15));
        tank2 = new Tank(ground,nozzle2,true);
        addObject(tank2,200,ground.getShiftRelY(200,15));
        
        
        
    }
    
    public Color getSkyColor() {
        return skyColor;
    }
}
