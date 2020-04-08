package Math;

/**
 *
 * @author Oscar Sierra
 */
public class Vector2D {
    private double x, y;
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public Vector2D(){
        x=0;
        y=0;
    }
    
    public Vector2D add(Vector2D v){
        return new Vector2D(x+v.getX(),y+v.getY());
    }
    
    public Vector2D substract(Vector2D v){
        return new Vector2D(x-v.getX(),y-v.getY());
    }
    
    public Vector2D scale(double value){
        return new Vector2D(x*value,y*value);
    }
    
    public Vector2D limit(double value){
        if(getMagnitude()>value){
            return this.normalize().scale(value);
        }
        return this;
    }
    
    public Vector2D normalize(){
        double magnitude = getMagnitude();
        return new Vector2D(x/magnitude, y/magnitude);
    }

    public double getMagnitude(){
        return Math.sqrt(x*x + y*y);
    }
    
    public Vector2D setDirection(double angle){
        double magnitude = getMagnitude();
        return new Vector2D(Math.cos(angle)*magnitude, Math.sin(angle)*magnitude);
    }
    
    public double getAngle(){
        return Math.asin(y/getMagnitude());
    }
    
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
}
