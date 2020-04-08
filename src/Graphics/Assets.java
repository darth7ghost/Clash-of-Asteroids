package Graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Oscar Sierra
 */
public class Assets {
    //Naves
    public static BufferedImage player;
    public static BufferedImage enemy;
    //Efectos
    public static BufferedImage speed;
    public static BufferedImage blueLaser, redLaser, greenLaser;
    //Meteoros
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    //Explosion
    public static BufferedImage[] exp = new BufferedImage[9];
    //GUI
    public static BufferedImage life;
    public static BufferedImage cursor;
    public static BufferedImage[] nums = new BufferedImage[11];
    public static Font fontB;
    public static Font fontM;
    //Sounds
    public static Clip bgMusic, explosion, playerLoose, enemyShoot, playerShoot, powerUp;
    
    public static void init() throws LineUnavailableException{
        player = Loader.ImageLoader("/ships/Ship2_blue.png");
        enemy = Loader.ImageLoader("/ships/enemyBlack1.png");
        life = Loader.ImageLoader("/GUI/playerLife3_blue.png");
        cursor = Loader.ImageLoader("/GUI/cursor.png");
        speed = Loader.ImageLoader("/Effects/fire08.png");
        blueLaser = Loader.ImageLoader("/Lasers/laserBlue13.png");
        redLaser = Loader.ImageLoader("/Lasers/laserRed13.png");
        greenLaser = Loader.ImageLoader("/Lasers/laserGreen03.png");
        fontB = Loader.loadFont("/Fonts/Borda Bold.ttf", 42);
        fontM = Loader.loadFont("/Fonts/Borda Bold.ttf", 20);
        bgMusic = Loader.loadSound("/Sounds/backgroundMusic.wav");
        explosion = Loader.loadSound("/Sounds/explosion.ogg");
        playerLoose = Loader.loadSound("/Sounds/playerLoose.ogg");
        playerShoot = Loader.loadSound("/Sounds/playerShoot.ogg");
        powerUp = Loader.loadSound("/Sounds/powerUp.ogg");
        for(int i = 0; i < bigs.length; i++)
            bigs[i] = Loader.ImageLoader("/Meteors/meteorBrown_big"+(i+1)+".png");

        for(int i = 0; i < meds.length; i++)
            meds[i] = Loader.ImageLoader("/Meteors/meteorBrown_med"+(i+1)+".png");

        for(int i = 0; i < smalls.length; i++)
            smalls[i] = Loader.ImageLoader("/Meteors/meteorBrown_small"+(i+1)+".png");

        for(int i = 0; i < tinies.length; i++)
            tinies[i] = Loader.ImageLoader("/Meteors/meteorBrown_tiny"+(i+1)+".png");
        
        for(int i = 0; i < exp.length; i++)
            exp[i] = Loader.ImageLoader("/Explosion/"+i+".png");
        
        for(int i = 0; i < nums.length; i++)
            nums[i] = Loader.ImageLoader("/GUI/"+i+".png");
    }
}
