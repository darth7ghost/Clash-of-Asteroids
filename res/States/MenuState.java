package States;

import GUI.Action;
import GUI.Button;
import GameObjects.Constants;
import Graphics.Assets;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Oscar Sierra
 */
public class MenuState extends State {
    private ArrayList<Button> buttons;
    
    public MenuState(){
        buttons = new ArrayList<>();
        //1 Jugador
        buttons.add(new Button(
            Assets.bluebtn, Assets.bluebtnp,
            Constants.WIDTH/2 - Assets.bluebtn.getWidth(),
            Constants.HEIGHT/2 - Assets.bluebtn.getHeight(),
            Constants.PLAY1,
            new Action(){
                @Override
                public void doAction(){
                    State.changeState(new GameState());
                }
            }
        ));
        //2 Jugadores
        buttons.add(new Button(
            Assets.greenbtn, Assets.greenbtnp,
            Constants.WIDTH/2+Assets.greenbtn.getWidth()/9,
            Constants.HEIGHT/2-Assets.greenbtn.getHeight(),
            Constants.PLAY2,
            new Action(){
                @Override
                public void doAction(){
                    State.changeState(new GameState());
                }
            }
        ));
        //Acerca de
        buttons.add(new Button(
            Assets.greybtn, Assets.greybtnp,
            Constants.WIDTH/2+Assets.greybtn.getWidth()/9,
            Constants.HEIGHT/2+Assets.greybtn.getHeight()/2,
            Constants.ABOUT,
            new Action(){
                @Override
                public void doAction(){
                    //State.changeState(new GameState());
                }
            }
        ));
        //Salir
        buttons.add(new Button(
            Assets.redbtn, Assets.redbtnp,
            Constants.WIDTH/2-Assets.redbtn.getWidth(),
            Constants.HEIGHT/2+Assets.redbtn.getHeight()/2,
            Constants.EXIT,
            new Action(){
                @Override
                public void doAction(){
                    System.exit(0);
                }
            }
        ));
    }

    @Override
    public void update() {
        for(Button b: buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(Button b: buttons){
            b.draw(g);
        }
    }
    
}
