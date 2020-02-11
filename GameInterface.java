import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameInterface extends JFrame implements KeyListener{
    private static final long serialVersionUID = 1L;
    /* attr */
    private GameEngine gameToDisplay;
    private enum typeDisplay{ Abstract, CMD_line, Graphical_interface }
    private typeDisplay currentDisplay;
    private GraphicDisplaySnake displayPannel;

    /* constructor */
    public GameInterface(GameEngine game){
        gameToDisplay = game;
        currentDisplay = typeDisplay.Abstract;
    }

    public GameInterface(GameEngine game, String typeDis) {
        super();
        gameToDisplay = game;
        switch (typeDis) {
            case "graphical_interface":
                currentDisplay = typeDisplay.Graphical_interface;
                this.setTitle("Snake in Java");
                this.setSize(10*game.getW(), 10*game.getH());
                this.setLocationRelativeTo(null);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setVisible(true);
                displayPannel = new GraphicDisplaySnake(game, this);
                this.setContentPane(displayPannel);
                this.addKeyListener(this);
                break;
            case "cmd_line":
                currentDisplay = typeDisplay.CMD_line;
                break;
            case "abstract":
            default:
                currentDisplay = typeDisplay.Abstract;
                break;
        }
    }

    /* method */
    private boolean isSnake(int x, int y){
        for (int[] coords : gameToDisplay.getSnakePos()){
            if (x == coords[0] && y == coords[1]) return true;
        }
        return false;
    }

    private void AbstractDisplay(){
        System.out.println("____________________________");
        System.out.println("Snake Pos :");
        int size = gameToDisplay.getSnakeSize();
        System.out.println("Size : " + size);
        System.out.println("Direction : " + (gameToDisplay.getDir() == 1 ? "Left" : gameToDisplay.getDir() == 2 ? "Right" : gameToDisplay.getDir() == 3? "Bottom" : gameToDisplay.getDir() == 4 ? "Top" : "No direction"));
        System.out.println("Dir : " + gameToDisplay.getDir());
        int[][] coords = gameToDisplay.getSnakePos();
        for (int i=0; i<size; i++){
            System.out.print("("+coords[i][0]+","+coords[i][1]+")\n");
        }
        System.out.println("____________________________");
    }

    private void CMD_lineDisplayTopBottomPart(){
        System.out.print("+ ");
        for (int i=0; i<gameToDisplay.getW(); i++){
            System.out.print("- ");
        }
        System.out.print("+\n");
    }

    private void CMD_lineDisplay(){
        CMD_lineDisplayTopBottomPart();
        for (int i=0; i<gameToDisplay.getH(); i++){
            System.out.print("| ");
            for (int e = 0; e < gameToDisplay.getW(); e++) {
                if (isSnake(e, i)) {
                    System.out.print("0 ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.print("|\n");
        }
        CMD_lineDisplayTopBottomPart();
    }

    private void Graphical_interfaceDisplay(){
        // for debug
        //CMD_lineDisplay();
        AbstractDisplay();
        // main part
        displayPannel.update();
        displayPannel.repaint();
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void display(){
        switch (currentDisplay){
            case Abstract :
                AbstractDisplay();
                break;
            case CMD_line :
                CMD_lineDisplay();
                break;
            case Graphical_interface :
                Graphical_interfaceDisplay();
                break;
            default:
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            /* left */
            case 37:
                gameToDisplay.setDir(1);
                break;
            /* top */
            case 38:
                gameToDisplay.setDir(3);
                break;
            /* right */
            case 39:
                gameToDisplay.setDir(2);
                break;
            /* bottom */
            case 40:
                gameToDisplay.setDir(4);
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}