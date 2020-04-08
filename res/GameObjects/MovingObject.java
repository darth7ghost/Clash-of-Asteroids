package GameObjects;

import Math.Vector2D;
import States.GameState;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Oscar Sierra
 */
public abstract class MovingObject extends GameObject{
    
    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxVel;
    protected int width;
    protected int height;
    protected GameState gameState;

    public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVel = maxVel;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
    }
    
    protected void collidesWidth(){
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();
        for (int i=0;i<movingObjects.size();i++) {
            MovingObject m = movingObjects.get(i);
            if(m.equals(this))
                continue;
                
            double distance = m.getCenter().substract(getCenter()).getMagnitude();
            
            if(distance<m.width/2+width/2 && movingObjects.contains(this)){
                objectCollision(m, this);
            }
        }
    }
    
    private void objectCollision(MovingObject a, MovingObject b){
        if(a instanceof Player && ((Player)a).isSpawning()){
            return;
        }
        if(b instanceof Player && ((Player)b).isSpawning()){
            return;
        }
        if(!(a instanceof Meteor && b instanceof Meteor)){
            gameState.playExplosion(getCenter());
            a.Destroy();
            b.Destroy();
        }
    }
    
    protected void Destroy(){
        gameState.getMovingObjects().remove(this);
    }
    
    protected Vector2D getCenter(){
        return new Vector2D(position.getX()+width/2, position.getY()+height/2);
    }
}
