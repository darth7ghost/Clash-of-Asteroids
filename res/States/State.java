package States;

import java.awt.Graphics;

/**
 *
 * @author Oscar Sierra
 */
public abstract class State {
    private static State currentState = null;
    /**
     * @return the currentState
     */
    public static State getCurrentState() {
        return currentState;
    }
    
    public static void changeState(State newState){
        currentState = newState;
    }
    
    public abstract void update();
    public abstract void draw(Graphics g);

    
}
