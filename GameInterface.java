import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameInterface extends JFrame implements KeyListener{
    private static final long serialVersionUID = 1L;
    /* attr */
    private GameEngine gameToDisplay;
    private enum typeDisplay{ Abstract, CMD_line, Graphical_interface }
    private typeDisplay currentDisplay;
    private GraphicDisplaySnake displayPannel;
    private int waitTime;

    /* constructor */
    public GameInterface(GameEngine game){
        gameToDisplay = game;
        currentDisplay = typeDisplay.Abstract;
    }

    public GameInterface(GameEngine game, String typeDis) {
        super();
        gameToDisplay = game;
        int caseSize = 10;
        switch (typeDis) {
            case "graphical_interface":
                currentDisplay = typeDisplay.Graphical_interface;
                /* window in itself */
                this.setTitle("Snake in Java");
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setResizable(false);
                /* jpannel */
                displayPannel = new GraphicDisplaySnake(game, this, caseSize);
                this.setContentPane(displayPannel);
                this.getContentPane().setPreferredSize(new Dimension(caseSize * (game.getW()), caseSize * (game.getH())));
                /* keylistener */
                this.addKeyListener(this);
                /* center window */
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
                /* show window */
                this.pack();
                this.setLocationRelativeTo(null);
                this.setVisible(true);
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

    public GameInterface(GameEngine game, String typeDis, int caseSize) {
        super();
        gameToDisplay = game;
        switch (typeDis) {
        case "graphical_interface":
            currentDisplay = typeDisplay.Graphical_interface;
            /* window in itself */
            this.setTitle("Snake in Java");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            /* jpannel */
            displayPannel = new GraphicDisplaySnake(game, this, caseSize);
            this.setContentPane(displayPannel);
            this.getContentPane().setPreferredSize(new Dimension(caseSize * (game.getW()), caseSize * (game.getH())));
            this.setResizable(false);
            /* keylistener */
            this.addKeyListener(this);
            /* center window */
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            /* show window */
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
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
        System.out.println("Direction : " + gameToDisplay.getDir());
        System.out.println("Dir : " + gameToDisplay.getDir());
        int[] fruitCoords = gameToDisplay.getFruitPos();
        System.out.println("Fruit : (" + fruitCoords[0] +";"+ fruitCoords[1] + ")");
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
                else{
                    int[] coords = gameToDisplay.getFruitPos();
                    if (e == coords[0] && i == coords[1]) {
                        System.out.print("1 ");
                    }
                    else{
                        System.out.print("  ");
                    }
                }
            }
            System.out.print("|\n");
        }
        CMD_lineDisplayTopBottomPart();
    }

    private void speedSetup(){
        if (waitTime == 0){
            waitTime = 60;
        }
        else if (gameToDisplay.getSnakeSize()%4 == 0 && waitTime > 4 && gameToDisplay.FrEaten()){
            waitTime--;
        }
    }

    private void Graphical_interfaceDisplay(){
        // main part
        displayPannel.update();
        displayPannel.repaint();
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        speedSetup();
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