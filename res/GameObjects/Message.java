package GameObjects;

import Graphics.Text;
import Math.Vector2D;
import States.GameState;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Oscar Sierra
 */
public class Message {
    private GameState gameState;
    private float alpha;
    private String text;
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean fade;
    private Font font;
    private final float deltaAlpha = 0.01f;
    
    public Message(Vector2D position, boolean fade, String text, Color color, boolean center,
            Font font, GameState gameState){
        this.center = center;
        this.color = color;
        this.fade = fade;
        this.position = position;
        this.text = text;
        this.font = font;
        this.gameState = gameState;
        if(fade){
            alpha = 1;
        }else{
            alpha = 0;
        }
    }
    
    public void draw(Graphics2D g2d){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        Text.drawText(g2d, text, position, center, color, font);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        position.setY(position.getY()-1);
        if(fade){
            alpha-=deltaAlpha;
        }else{
            alpha+=deltaAlpha;
        }
        if(fade && alpha<0 || !fade && alpha>1){
            gameState.getMessage().remove(this);
        }
    }
}
