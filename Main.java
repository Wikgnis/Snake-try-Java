public class Main{
    public static void main(String args[]){
        GameEngine game = new GameEngine(75, 75);
        GameInterface displayGame = new GameInterface(game, "graphical_interface", 12);
        /* game loop */
        displayGame.display();
        while (game.SnakeAlive()){
            if (!displayGame.pause()) game.SnakeMove();
            displayGame.display();
        }
        System.out.println("Game Over");
    }
}