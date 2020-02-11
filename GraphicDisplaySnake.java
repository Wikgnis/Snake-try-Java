import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

class PartDisplay {
    private final int x;
    private final int y;
    private static int size;
    private boolean active;
    PartDisplay (int x, int y){
        this.x = x;
        this.y = y;
        active = false;
    }
    public void setSize(int size){
        this.size = size;
    }
    public boolean isActive(){
        return active;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getSize(){
        return size;
    }
}

public class GraphicDisplaySnake extends JPanel{
    /* attr */
    private GameEngine gameToDisplay;
    private PartDisplay[][] PannelPart;
    private GameInterface master;

    /* constructor */
    public GraphicDisplaySnake(GameEngine game , GameInterface master){
        super();
        gameToDisplay = game;
        this.master = master;
        // adapt the display to the Jframe
        PannelPart = new PartDisplay[game.getH()][game.getW()];
        for (int i=0; i<game.getH(); i++){
            for (int e=0; e<game.getW(); e++){
                PannelPart[i][e] = new PartDisplay(e, i);
            }
        }
        PannelPart[0][0].setSize(master.getWidth()/game.getW());
    }

    /* method */
    public void paintComponent(Graphics g) {
        for (int i = 0; i < gameToDisplay.getH(); i++) {
            for (int e = 0; e < gameToDisplay.getW(); e++) {
                if (PannelPart[i][e].isActive()){
                    g.setColor(Color.black);
                    g.fillRect(40, 40, PannelPart[i][e].getSize(), PannelPart[i][e].getSize());
                }
            }
        }
    }
}