import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shell extends Actor
{
    /**
     * Act - do whatever the Shell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Earth earth;
    private Ground ground;
    
    /** Physical quantities that will change over time*/
    private int angle;
    private int xPos;
    private int yPos;
    
    /** X-direction Projectile motion quantities that are constant*/
    private double v_x;
    private double x_i;
    
    /** Y-direction Projectile motion quantities that are constant*/
    private double a_y = 9.81; // need to flip sign because y is inverted
    private double v_iy1;
    private double v_iy2;
    private double y_i;
    
    /** Projectile motion quantities that are changing over time*/
    private double v_y;
    private int direction = 1;
    
    /** Flight time*/
    private int t = 0;
    
    private boolean is_right;
    
    public Shell(Ground ground,int angle,boolean is_right) {
        this.ground = ground;
        this.angle = angle;
        this.is_right = is_right;
        v_iy1 = -65*Math.sin(Math.toRadians(angle));
        v_iy2 = -75*Math.sin(Math.toRadians(angle));
        v_x = -65*Math.cos(Math.toRadians(angle));
        //System.out.println("V_iy:" + ((angle > 50) ? v_iy2 : v_iy1));
        if (is_right) {
            v_x *= -1;
            getImage().mirrorHorizontally();
        }
        turn(angle);
        
    }
    
    public void act() 
    {
        if (t == 0) System.out.println("Angle:" + angle + " Slope:" + getSlope());
        
        if (!checkCollision()) {
            move();
            t++;
        }
        
    }
    
    public boolean checkCollision() {
        //System.out.println("Y:" + getY() + " Rel Y:" + theGround.getRelativeY(getX()));
        if (getY() >= ground.getRelativeY(getX()) || getX() == 0  || getX() == 599) {
            earth.addObject(new Boom(), getX(),getY());
            earth.removeObject(this);
            return true;
        }
        else return false;
    }
    
    public void move() {
        System.out.println(getX());
        setRotation((int)getSlope());
        setLocation((int)calcXPos(),(int)calcYPos());
    }
    
    public double calcYPos()  {
        return 0.5*a_y*Math.pow(t/30.0,2) + v_iy1*(t/30.0) + y_i;
    }
    
    public double calcXPos() {
        return v_x*(t/30.0) + x_i;
    }
    
    public double getSlope() {
        v_y = a_y*(t/30.0) + v_iy1; //need to flip b/c y is flipped
        if(!is_right) v_y *= -1;
        return v_y;
    } 
    
    public void addedToWorld(World world) 
    {       
        xPos = getX();
        x_i = getX();
        yPos = getY();
        y_i = getY();
        earth = (Earth) world;
    }
    
}
