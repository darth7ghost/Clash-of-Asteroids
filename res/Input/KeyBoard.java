package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Oscar Sierra
 */
public class KeyBoard implements KeyListener {
    private boolean[] keys = new boolean[256];
    public static boolean UP, LEFT, RIGHT, SHOOT;
    public KeyBoard(){
        UP = false;
        LEFT = false;
        RIGHT = false;
        SHOOT = false;
    }
    
    public void update(){
        UP = keys[KeyEvent.VK_UP];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        SHOOT = keys[KeyEvent.VK_Q];
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        //System.out.println(ke.getKeyCode());
        keys[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //System.out.println(ke.getKeyCode());
        keys[ke.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}
}
