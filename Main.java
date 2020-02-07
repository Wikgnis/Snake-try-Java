public class Main{
    public static void main(String args[]){
        GameEngine game = new GameEngine(10, 10);
        GameInterface displayGame = new GameInterface(game);
        /* game loop */
        while (game.SnakeAlive()){
            game.SnakeMove();
            displayGame.display();
        }
        System.out.println("Game Ended");
    }
}