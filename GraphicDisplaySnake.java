import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.FontMetrics;

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
        PartDisplay.size = size;
    }
    public boolean isActive(){
        return active;
    }
    public void setActive(){
        active = true;
    }
    public void setInactive() {
        active = false;
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
    private static final long serialVersionUID = 1L;
    /* attr */
    private GameEngine gameToDisplay;
    private PartDisplay[][] PannelPart;
    private GameInterface master;

    /* constructor */
    public GraphicDisplaySnake(GameEngine game , GameInterface master, int size){
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
        PannelPart[0][0].setSize(size);
        for (int[] coord : gameToDisplay.getSnakePos()) {
            PannelPart[coord[1]][coord[0]].setActive();
        }
    }

    /* method */
    public void update(){
        for (int i = 0; i < gameToDisplay.getH(); i++) {
            for (int e = 0; e < gameToDisplay.getW(); e++) {
                PannelPart[i][e].setInactive();
            }
        }
        for (int[] coord : gameToDisplay.getSnakePos()){
            if (
                !(coord[0] < 0 || coord[0] >= gameToDisplay.getW() || coord[1] < 0 || coord[1] >= gameToDisplay.getH())
                ) {
                PannelPart[coord[1]][coord[0]].setActive();
            }
        }
    }

    public void paintComponent(Graphics g) {
        /* background */
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        /* snake  & fruit */
        int[] fruitCoords = gameToDisplay.getFruitPos();
        for (int i = 0; i < gameToDisplay.getH(); i++) {
            for (int e = 0; e < gameToDisplay.getW(); e++) {
                if (PannelPart[i][e].isActive()){
                    g.setColor(Color.green);
                    g.fillRect(PannelPart[i][e].getX()*PannelPart[i][e].getSize(), PannelPart[i][e].getY()*PannelPart[i][e].getSize(), PannelPart[i][e].getSize(), PannelPart[i][e].getSize());
                }
                else if (e == fruitCoords[0] && i == fruitCoords[1]) {
                    g.setColor(Color.red);
                    g.fillRect(PannelPart[i][e].getX() * PannelPart[i][e].getSize(),PannelPart[i][e].getY() * PannelPart[i][e].getSize(), PannelPart[i][e].getSize(), PannelPart[i][e].getSize());
                }
                else {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(PannelPart[i][e].getX() * PannelPart[i][e].getSize(), PannelPart[i][e].getY() * PannelPart[i][e].getSize(), PannelPart[i][e].getSize(), PannelPart[i][e].getSize());
                }
            }
        }
        if (!gameToDisplay.SnakeAlive()){
            g.setColor(Color.green);
            Font font = new Font("Arial", Font.BOLD, PannelPart[0][0].getSize()*4);
            Rectangle rect = new Rectangle(0, 0, gameToDisplay.getW()*PannelPart[0][0].getSize(), gameToDisplay.getH() * PannelPart[0][0].getSize());
            drawCenteredString(g, "Game over!", rect, font);
        }
    }
    
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java
        // 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}