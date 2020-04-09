package GUI;

import Graphics.Assets;
import Graphics.Text;
import Input.MouseInput;
import Math.Vector2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Oscar Sierra
 */
public class Button {
    private BufferedImage mouseOutImg;
    private BufferedImage mouseInImg;
    private boolean mouseIn;
    private Rectangle boundingBox;
    private String text;
    private Action action;
    
    public Button(BufferedImage mouseOutImg, BufferedImage mouseInImg,
            int x, int y, String text, Action action){
        this.mouseInImg = mouseInImg;
        this.mouseOutImg = mouseOutImg;
        this.text = text;
        boundingBox = new Rectangle(x, y, mouseInImg.getWidth(), mouseInImg.getHeight());
        this.action = action;
    }
    
    public void update(){
        if(boundingBox.contains(MouseInput.X, MouseInput.Y)){
            mouseIn = true;
        }else{
            mouseIn = false;
        }
        if(mouseIn && MouseInput.MLB){
            action.doAction();
        }
    }
    
    public void draw(Graphics g){
        if(mouseIn){
            g.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
        }else{
            g.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
        }
        Text.drawText(g, text, new Vector2D(boundingBox.getX()+boundingBox.getWidth()/3,
                                            boundingBox.getY()+boundingBox.getHeight()/2),
                      true, java.awt.Color.black, Assets.fontM);
    }
}
