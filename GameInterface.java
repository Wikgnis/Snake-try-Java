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
        System.out.println("Dir : " + gameToDisplay.getDir());
        int[][] coords = gameToDisplay.getSnakePos();
        for (int i=0; i<size; i++){
            System.out.print("("+coords[i][0]+","+coords[i][1]+")\n");
        }
        System.out.println("____________________________");
    }
    public void display(){
        switch (currentDisplay){
            case Abstract :
                AbstractDisplay();
                break;
            default:
                break;
        }
    }
}