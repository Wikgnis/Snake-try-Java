public class Main{
    public static void main(String args[]){
        GameEngine game = new GameEngine(50, 50);
        GameInterface displayGame = new GameInterface(game, "graphical_interface");
        /* game loop */
        displayGame.display();
        while (game.SnakeAlive()){
            game.SnakeMove();
            displayGame.display();
        }
        System.out.println("Game Ended");
    }
}