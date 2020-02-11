public class Main{
    public static void main(String args[]){
        GameEngine game = new GameEngine(10, 10);
        GameInterface displayGame = new GameInterface(game);
        /* game loop */
        game.setDir(4);
        displayGame.display();
        while (game.SnakeAlive()){
            game.SnakeMove();
            displayGame.display();
        }
        System.out.println("Game Ended");
    }
}