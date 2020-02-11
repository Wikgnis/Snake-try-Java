public class GameInterface{
    /* attr */
    private GameEngine gameToDisplay;
    private enum typeDisplay{ Abstract, CMD_line, Graphical_interface }
    private typeDisplay currentDisplay;

    /* constructor */
    public GameInterface(GameEngine game){
        gameToDisplay = game;
        currentDisplay = typeDisplay.Abstract;
    }

    /* method */
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
                if (i == )
            }
            System.out.print("|\n");
        }
        CMD_lineDisplayTopBottomPart();
    }
    public void display(){
        switch (currentDisplay){
            case Abstract :
                AbstractDisplay();
                CMD_lineDisplay();
                break;
            case CMD_line :
                CMD_lineDisplay();
                break;
            default:
                break;
        }
    }
}