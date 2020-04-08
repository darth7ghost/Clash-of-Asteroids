package GameObjects;

import Graphics.Assets;
import Graphics.Sound;
import Input.KeyBoard;
import Main.Window;
import Math.Vector2D;
import States.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Oscar Sierra
 */
public class Player extends MovingObject {
    
    private Vector2D heading;
    private Vector2D acceleration;
    private final double ACC = 0.2;
    private final double DELTAANGLE = 0.1;
    private boolean accelerating = false;
    private Chronometer fireRate;
    private boolean spawning, visible;
    private Chronometer spawnTime, flickerTime;
    private Sound shoot;

    public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        heading = new Vector2D(0,1);
        acceleration = new Vector2D();
        fireRate = new Chronometer();
        spawnTime = new Chronometer();
        flickerTime = new Chronometer();
        shoot = new Sound(Assets.playerShoot);
    }

    @Override
    public void update() {
        if(!spawnTime.isRunning()){
            spawning = false;
            visible = true;
        }
        if(spawning){
            if(!flickerTime.isRunning()){
                flickerTime.run(Constants.FLICKER_TIME);
                visible = !visible;
            }
        }
        if(KeyBoard.SHOOT && !fireRate.isRunning() && !spawning){
            gameState.getMovingObjects().add(0,new Laser(
                getCenter().add(heading.scale(width)),
                    heading, 10, angle, Assets.blueLaser, gameState
            ));
            fireRate.run(Constants.FIRERATE);
            shoot.play();
        }
        if(KeyBoard.RIGHT){
            angle+=DELTAANGLE;
        }
        if(KeyBoard.LEFT){
            angle-=DELTAANGLE;
        }
        if(KeyBoard.UP){
            acceleration = heading.scale(ACC);
            accelerating = true;
        }else{
            if(velocity.getMagnitude()!=0){
                acceleration = (velocity.scale(-1).normalize()).scale(ACC/2);
                accelerating = false;
            }
        }
        velocity = velocity.add(acceleration);
        velocity = velocity.limit(maxVel);
        heading = heading.setDirection(angle-Math.PI/2);
        position = position.add(velocity);
        
        if(position.getX()>Window.WIDTH){
            position.setX(0);
        }
        if(position.getY()>Window.HEIGHT){
            position.setY(0);
        }
        if(position.getX()<0){
            position.setX(Window.WIDTH);
        }
        if(position.getY()<0){
            position.setY(Window.HEIGHT);
        }
        fireRate.update();
        spawnTime.update();
        flickerTime.update();
        collidesWidth();
    }

    @Override
    public void Destroy(){
        spawning = true;
        spawnTime.run(Constants.SPAWNING_TIME);
        resetValues();
        gameState.substractLife();
    }
    
    private void resetValues(){
        angle=0;
        velocity = new Vector2D();
        position = new Vector2D(Constants.WIDTH/2-Assets.player.getWidth()/2, 350);
    }
    
    @Override
    public void draw(Graphics g) {
        if(!visible){
            return;
        }
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX()+width/2+5
                , position.getY()+height/2+10);
        AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX()+8
                , position.getY()+height/2+10);
        at1.rotate(angle, -5, -10);
        at2.rotate(angle, width/2-8, -10);
        if(accelerating){
            g2d.drawImage(Assets.speed, at1, null);
            g2d.drawImage(Assets.speed, at2, null);
        }
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width/2, height/2);
        g2d.drawImage(texture, at, null);
    }
    
    public boolean isSpawning(){
        return spawning;
    }
}
