package Graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;

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
    public static BufferedImage bluebtn, greenbtn, greybtn, yellowbtn, redbtn;
    public static BufferedImage bluebtnp, greenbtnp, greybtnp, yellowbtnp, redbtnp;
    //Sounds
    public static Clip bgMusic, explosion, playerLoose, enemyShoot, playerShoot, powerUp;
    
    public static void init() {
        //GUI
        player = Loader.ImageLoader("/ships/Ship2_blue.png");
        enemy = Loader.ImageLoader("/ships/enemyBlack1.png");
        life = Loader.ImageLoader("/GUI/playerLife3_blue.png");
        cursor = Loader.ImageLoader("/GUI/cursor.png");
        //Normal buttons
        bluebtn = Loader.ImageLoader("/GUI/bluebtn.png");
        greenbtn = Loader.ImageLoader("/GUI/greenbtn.png");
        greybtn = Loader.ImageLoader("/GUI/greybtn.png");
        yellowbtn = Loader.ImageLoader("/GUI/yellowbtn.png");
        redbtn = Loader.ImageLoader("/GUI/redbtn.png");
        //Pushed buttons
        bluebtnp = Loader.ImageLoader("/GUI/bluebtnp.png");
        greenbtnp = Loader.ImageLoader("/GUI/greenbtnp.png");
        greybtnp = Loader.ImageLoader("/GUI/greybtnp.png");
        yellowbtnp = Loader.ImageLoader("/GUI/yellowbtnp.png");
        redbtnp = Loader.ImageLoader("/GUI/redbtnp.png");
        //Effects
        speed = Loader.ImageLoader("/Effects/fire08.png");
        blueLaser = Loader.ImageLoader("/Lasers/laserBlue13.png");
        redLaser = Loader.ImageLoader("/Lasers/laserRed13.png");
        greenLaser = Loader.ImageLoader("/Lasers/laserGreen03.png");
        fontB = Loader.loadFont("/Fonts/Borda Bold.ttf", 42);
        fontM = Loader.loadFont("/Fonts/Borda Bold.ttf", 20);
        //Sounds
        bgMusic = Loader.loadSound("/Sounds/backgroundMusic.wav");
//        explosion = Loader.loadSound("/Sounds/explosion.mp3");
//        playerLoose = Loader.loadSound("/Sounds/playerLoose.mp3");
//        enemyShoot = Loader.loadSound("/Sounds/playerShoot.mp3");
//        playerShoot = Loader.loadSound("/Sounds/playerShoot.mp3");
//        powerUp = Loader.loadSound("/Sounds/powerUp.mp3");
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
