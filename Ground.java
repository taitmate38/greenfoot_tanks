import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.util.stream.*;
/**
 * Write a description of class ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ground extends Actor
{
    /**
     * Act - do whatever the ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage myImage;
    
    /** the size of the bounding box  of the image*/
    private int xSize = 600;
    private int ySize = 200;
    
    /** generated list of x and y positions for reference by tanks */
    private int[] xPos;
    private int[] yPos;
    
    /** */
    private double friction = 0.2;
    
    
    public Ground() {
        myImage = new GreenfootImage(xSize,ySize);
        // myImage.drawLine(0,0,599,0);
        myImage.setColor(Color.GREEN);
        myImage.fillPolygon(createLineX(),createLineY(),602);
        //myImage.fill();
        setImage(myImage);
        
        xPos = createXPos().stream().mapToInt(i -> i).toArray();
        yPos = createYPos().stream().mapToInt(i -> i).toArray();
        
    }
    
    public int MathFcn1(int x) {
        int A = 12; //+ 4*Math.random();
        return 35 + (int)((int)A*Math.sin(0.05*x));
    } 
    
    public int MathFcn2(int x) {
        return 43 + (int)(0.001*Math.pow(x-300,2));
    } 
    
    public List<Integer> createXPos() {
        List<Integer> xPos = new ArrayList<Integer>();
        for (int i = 0; i < 600; i++) xPos.add(i);
        return xPos;
    }
    
    public int[] createLineX() {
        //adds the x vals of the corner points at front and end so that 
        //full polygon is drawn
        List<Integer> xPoints = createXPos();
        xPoints.add(0,0);
        xPoints.add(599);   
        return xPoints.stream().mapToInt(i -> i).toArray();
    }
    
    public List<Integer> createYPos() {
        List<Integer> yPos = new ArrayList<Integer>();
        for (int i = 0; i < 600; i++) {
            if (i < 300) yPos.add(MathFcn1(i));
            else yPos.add(MathFcn2(i));
        }
        return yPos;
    }
    
    public int[] createLineY() {
        //adds the y vals of the corner points at front and end so that 
        //full polygon is drawn
        List<Integer> yPoints = createYPos();
        yPoints.add(0,200);
        yPoints.add(200);   
        return yPoints.stream().mapToInt(i -> i).toArray();
    }
    
    public int getRelativeY(int x) {
        int rawY = yPos[x];
        //System.out.println(rawY);
        return getY() - ySize/2 + rawY;
    }
    
    public int getShiftRelY(int x,int shift) {
        return getRelativeY(x) - shift;
    }
    
    public void act() 
    {
        // Add your action code here.
    }
    
    public double getFriction() {return friction;}
}
