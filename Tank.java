import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tank extends Actor
{
    /**
     * Act - do whatever the Tank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private Earth earth;
    private Ground ground;
    private Nozzle nozzle;
    
    private boolean move_left;
    private boolean move_right;
    
    private int xPos;
    private int yPos;
    
    private double vel;
    private double speedFactor = 4.0;
    
    private int maxSpeed = 5;
    private double friction;
    
    private int angle;
    
    private int shotCounter;
    private int shotGap = 30;
    
    private boolean is_right = false;
    
    String left = "left";
    String right = "right";
    String up = "up";
    String down = "down";
    String fire = "/";
    
    public Tank(Ground ground,Nozzle nozzle, boolean is_right) {
        this.ground = ground;
        this.nozzle = nozzle;
        this.is_right = is_right;
        friction = ground.getFriction();
        if (is_right) {
            getImage().mirrorHorizontally();
            left = "a";
            right = "d";
            up = "w";
            down = "s";
            fire = "e";
            
        }
        
    }
    
    public void act() 
    {
        if(is_right) angleNozzle2();
        else angleNozzle();
        changeShotCounter();
        if (Greenfoot.isKeyDown(fire)) shoot();
        accelerate();
        movePath();
        applyFriction();
        System.out.println(shotCounter);
    }    
    
    public void accelerate() {
       move_left = Greenfoot.isKeyDown(left);
       move_right = Greenfoot.isKeyDown(right); 
       if (move_left || move_right && Math.abs(vel) < maxSpeed) {
           if (move_right) vel++;
           if (move_left) vel--;
       }
    }
    
    public void movePath() {
        if (!((xPos + vel / speedFactor) < 0 || (xPos + vel / speedFactor) > 599)) {
            xPos += (int)(vel / speedFactor);
            yPos = ground.getShiftRelY(xPos,15);
            setLocations(xPos,yPos);
        } else {
            vel *= -1;
        }
    }
    
    public void applyFriction() {
        if (vel > friction) vel -= friction;
        else if (vel < -friction) vel += friction; 
        else vel = 0;
    }
    
    public void angleNozzle() {
        if (angle >= 0 && angle <= 70) {
            if (Greenfoot.isKeyDown(up) && angle < 70) {
                angle++;
                nozzle.turn(1);
            } else if(Greenfoot.isKeyDown(down) && angle > 0) {
                angle--;
                nozzle.turn(-1);
            }
        }
    }
    
    public void angleNozzle2() {
        if (angle >= 0 && angle <= 70) {
            if (Greenfoot.isKeyDown(up) && angle < 70) {
                angle++;
                nozzle.turn(-1);
            } else if(Greenfoot.isKeyDown(down) && angle > 0) {
                angle--;
                nozzle.turn(1);
            }
        }
    }
    
    public void changeShotCounter() {
        if (shotCounter > 0) {
            if (shotCounter < shotGap) shotCounter++; 
            else if (shotCounter ==  shotGap) shotCounter = 0;
        }
    }
    
    public void shoot() {
        
        if (shotCounter == 0) {
            if (angle < 47) earth.addObject(new Shell(ground,this,angle,is_right),getX(),getY() - 6);
            if (angle > 47) earth.addObject(new Shell(ground,this,angle,is_right),getX(),getY() - 14);
            shotCounter++;
        }
    }
    
    public void resetShot() {shotCounter = 0;}
    
    public void addedToWorld(World world) {
        earth = (Earth) world;
        xPos = getX();
        yPos = getY();
    }
    
    public void setLocations(int x, int y) {
        setLocation(x,y);
        nozzle.setLocation(x,y);
    }
    
    public Nozzle getNozzle() {return nozzle;}
}
