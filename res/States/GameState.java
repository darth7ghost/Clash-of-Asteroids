package States;

import GameObjects.Constants;
import GameObjects.Enemy;
import GameObjects.Message;
import GameObjects.Meteor;
import GameObjects.MovingObject;
import GameObjects.Player;
import GameObjects.Size;
import Graphics.Animation;
import Graphics.Assets;
import Graphics.Text;
import Math.Vector2D;
import com.sun.prism.paint.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Oscar Sierra
 */
public class GameState {
    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    private ArrayList<Animation> explotions = new ArrayList<Animation>();
    private ArrayList<Message> messages = new ArrayList<Message>();
    private int score = 0;
    private int lives = 3;
    private int meteors;
    private int waves = 1;
    
    public GameState(){
        player = new Player(new Vector2D(Constants.WIDTH/2-Assets.player.getWidth()/2, 350), new Vector2D(),
                Constants.PLAYER_MAX_VEL, Assets.player, this);
        movingObjects.add(player);
        meteors = 1;
        startWave();
    }
    
    public void addScore(int value, Vector2D position){
        score+=value;
        messages.add(new Message(position, true, "+"+value+" puntos", java.awt.Color.white, false, Assets.fontM, this));
    }
    
    public void divideMeteor(Meteor meteor){
        Size size = meteor.getSize();
        BufferedImage[] textures = size.textures;
        Size newSize = null;
        switch(size){
            case BIG:
                newSize = Size.MED;
                break;
            case MED:
                newSize = Size.SMALL;
                break;
            case SMALL:
                newSize = Size.TINY;
                break;
            default:
                return;
        }
        for (int i = 0; i < size.quantity; i++) {
            movingObjects.add(new Meteor(
                meteor.getPosition(), new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
                Constants.METEOR_VEL*Math.random()+1, textures[(int)(Math.random()*textures.length)],
                this, newSize));
        }
    }
    
    private void startWave(){
        messages.add(new Message(new Vector2D(300, 300), true,
            "OLEADA "+waves, java.awt.Color.white, true,  Assets.fontB, this));
        double x, y;
        for(int i=0; i<meteors;i++){
            x=i%2 == 0 ? Math.random()*Constants.WIDTH : 0;
            y=i%2 == 0 ? 0 : Math.random()*Constants.HEIGHT;
            BufferedImage texture = Assets.bigs[(int)(Math.random()*Assets.bigs.length)];
            movingObjects.add(new Meteor(
                new Vector2D(x, y), new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
                Constants.METEOR_VEL*Math.random()+1, texture, this, Size.BIG));
        }
        meteors++;
        waves++;
        spawnEnemy();
    }
    
    public void playExplosion(Vector2D position){
        explotions.add(new Animation(
            Assets.exp, 50, position.substract(new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))
        ));
    }
    
    private void spawnEnemy(){
        int rand = (int) (Math.random()*2);
        double x = rand == 0 ? (Math.random()*Constants.WIDTH): 0;
        double y = rand == 0 ? 0 : (Math.random()*Constants.HEIGHT);
        ArrayList<Vector2D> path = new ArrayList<Vector2D>();
        double posX, posY;

        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;	
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;	
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;	
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;	
        path.add(new Vector2D(posX, posY));
		
        movingObjects.add(new Enemy(
                        new Vector2D(x, y),
                        new Vector2D(),
                        Constants.ENEMY_MAX_VEL,
                        Assets.enemy,
                        path,
                        this
                        ));
    }
    
    public void update(){
        for(int i=0; i<movingObjects.size(); i++){
            movingObjects.get(i).update();
        }
        for(int i=0; i<explotions.size(); i++){
            Animation anim = explotions .get(i);
            anim.update();
            if(!anim.isRunning()){
                explotions.remove(i);
            }
        }
        for(int i=0; i<movingObjects.size(); i++){
            if(movingObjects.get(i)instanceof Meteor){
                return;
            }
        }
        startWave();
    }
    
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for(int i=0;i<messages.size();i++) {
            messages.get(i).draw(g2d);
        }
        for(int i=0; i<movingObjects.size(); i++){
            movingObjects.get(i).draw(g);
        }
        for(int i=0; i<explotions.size(); i++){
            Animation anim = explotions .get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(), null);
        }
        drawScore(g);
        drawLives(g);
    }
    
    private void drawScore(Graphics g){
        Vector2D pos = new Vector2D(700, 35);
        String scoreToString = Integer.toString(score);
        for (int i = 0; i < scoreToString.length(); i++) {
            g.drawImage(Assets.nums[Integer.parseInt(scoreToString.substring(i, i+1))],
                    (int)pos.getX(), (int)pos.getY(), null);
            pos.setX(pos.getX()+20);
        }
    }
    
    private void drawLives(Graphics g){
        Vector2D livePosition = new Vector2D(25, 35);
        g.drawImage(Assets.life, (int)livePosition.getX(), (int)livePosition.getY(), null);
        g.drawImage(Assets.nums[10], (int)livePosition.getX() + 40,
                        (int)livePosition.getY() + 5, null);
        String livesToString = Integer.toString(lives);
        Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());
        for(int i = 0; i < livesToString.length(); i ++){
            int number = Integer.parseInt(livesToString.substring(i, i+1));
            if(number <= 0)
                    break;
            g.drawImage(Assets.nums[number],
                            (int)pos.getX() + 60, (int)pos.getY() + 5, null);
            pos.setX(pos.getX() + 20);
        }

    }

    /**
     * @return the movingObjects
     */
    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
    
    public ArrayList<Message> getMessage() {
        return messages;
    }

    public Player getPlayer(){
        return player;
    }
    
    public void substractLife(){
        lives--;
    }
}
