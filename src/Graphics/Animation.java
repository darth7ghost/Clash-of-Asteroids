package Graphics;

import Math.Vector2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Oscar Sierra
 */
public class Animation {
    private BufferedImage[] frames;
    private int velocity, index;
    private boolean running;
    private Vector2D position;
    private long time, lastTime;
    
    public Animation(BufferedImage[] frames, int velocity, Vector2D position){
        this.frames = frames;
        this.velocity = velocity;
        this.position = position;
        index = 0;
        running = true;
        time = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public void update(){
        time+=System.currentTimeMillis()-lastTime;
        lastTime = System.currentTimeMillis();
        if(time>velocity){
            time=0;
            index++;
            if(index>=frames.length){
                running = false;
            }
        }
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @return the position
     */
    public Vector2D getPosition() {
        return position;
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
