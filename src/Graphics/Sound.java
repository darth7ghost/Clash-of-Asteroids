package Graphics;

import javax.sound.sampled.Clip;

/**
 *
 * @author Oscar Sierra
 */
public class Sound {
    private Clip clip;
    
    public Sound(Clip clip){
        this.clip = clip;
    }
    
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void stop(){
        clip.stop();
    }
}
