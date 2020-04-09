package GameObjects;

/**
 *
 * @author Oscar Sierra
 */
public class Constants {
    // frame dimensions
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // player properties
    public static final int FIRERATE = 300;
    public static final double DELTAANGLE = 0.1;
    public static final double ACC = 0.2;
    public static final double PLAYER_MAX_VEL = 7.0;
    public static final long FLICKER_TIME = 200;
    public static final long SPAWNING_TIME = 3000;

    // Laser properties
    public static final double LASER_VEL = 15.0;
    
    //Meteors
    public static double METEOR_VEL = 2.0;
    public static final int METEOR_SCORE = 20;
    
    //Enemies
    public static final int NODE_RADIUS = 160;
    public static final double ENEMY_MASS = 60;
    public static final int ENEMY_MAX_VEL = 3;
    public static long ENEMY_FIRE_RATE = 1000;
    public static double ENEMY_ANGLE_RANGE = Math.PI/2;
    public static final int ENEMY_SCORE = 50;
    
    //GUI
    public static final String PLAY1 = "1 JUGADOR";
    public static final String PLAY2 = "2 JUGADORES";
    public static final String ABOUT = "ACERCA DE";
    public static final String EXIT = "SALIR";
}
