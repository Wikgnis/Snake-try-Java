import java.util.Random;

public class GameEngine{
    /* attr */
    private int w;
    private int h;
    // snake
    private class BodyPart{
        public int x;
        public int y;
        public BodyPart topLevel;
        public BodyPart(int x, int y, BodyPart top){
            /* coords */
            this.x = x;
            this.y = y;
            /* top level body part */
            topLevel = top;
        }
    }
    private BodyPart[] body;
    private boolean SnakeAlive;
    private int dirX, dirY;
    // fruit
    private class Fruit{
        /* attr */
        private int x;
        private int y;
        private int[] max = new int[2];
        private boolean eaten;

        /* constructor */
        public Fruit(int maxX, int maxY){
            max[0] = maxX; 
            max[1] = maxY;
            setNewPlace();
        }

        /* method */
        public void setNewPlace(){
            Random r = new Random();
            x = r.nextInt(max[0]);
            y = r.nextInt(max[1]);
            eaten = false;
        }

        public int getX(){
            return x;
        }

        public int getY() {
            return y;
        }
    
        public boolean isEaten() {
            return eaten;
        }
    }
    Fruit gFruit;

    /* constructor */
    public GameEngine(int width, int height){
        w = width;
        h = height;
        // create snake
        body = new BodyPart[h*w];
        for (int i=0; i<3; i++){
            body[i] = new BodyPart(w/2+i, h/2, i>0?body[i-1]:null);
        }
        SnakeAlive = true;
        dirX = -1;
        dirY = 0;
        /* fruit */
        gFruit = new Fruit(width, height);
    }

    /* public method */
    public int getSnakeSize(){
        int size = 0;
        while (body[size] != null) size++;
        return size;
    }

    public int[][] getSnakePos(){
        int size = getSnakeSize();
        int[][] coords = new int[size][2];
        for (int i=0; i<size; i++){
            coords[i][0] = body[i].x;
            coords[i][1] = body[i].y;
        }
        return coords;
    }

    public boolean SnakeAlive(){
        return SnakeAlive;
    }

    public boolean isSnake(int[] coord){
        for (int[] coordS : getSnakePos()){
            if ( coord[0] == coordS[0] && coord[1] == coordS[1]) return true;
        }
        return false;
    }

    private boolean snakeHit(int[] coord){
        int[][] coordS = getSnakePos();
        for (int i=1; i<getSnakeSize(); i++){
            if (coord[0] == coordS[i][0] && coord[1] == coordS[i][1]){
                return true;
            }
        }
        return false;
    }

    public void SnakeMove(){
        /* fruit interaction */
        int[] coord = getFruitPos();
        boolean fruitEaten = false;
        if (body[0].x == coord[0] && body[0].y == coord[1]) {
            gFruit.setNewPlace();
            coord[0] = body[getSnakeSize() - 1].x;
            coord[1] = body[getSnakeSize() - 1].y;
            fruitEaten = true;
        }
        /* main body moving */
        for (int i=getSnakeSize()-1; i!=0; i--){
            body[i].x = body[i].topLevel.x;
            body[i].y = body[i].topLevel.y;
        }
        /* head moving */
        if (body[0].x < 0 || body[0].x >= w || body[0].y < 0 || body[0].y >= h){ // death handling
            SnakeAlive = false;
        }
        else {
            body[0].x += dirX;
            body[0].y += dirY;
            if (snakeHit(new int[] { body[0].x, body[0].y })) SnakeAlive = false;
            /* add part if fruit eaten */
            if (fruitEaten) {
                body[getSnakeSize()] = new BodyPart(coord[0], coord[1], body[getSnakeSize() - 1]);
            }
        }
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }

    public int getDir(){
        int dirReturn = 0;
        /* horizontal */
        if (dirX != 0 && dirY == 0){
            switch (dirX){
                /* left */
                case -1:
                    dirReturn = 1;
                    break;
                /* right */
                case 1:
                    dirReturn = 2;
                    break;
            }
        }
        /* vertical */
        else if (dirY != 0 && dirX == 0){
            switch (dirY) {
                /* top */
                case -1:
                    dirReturn = 3;
                    break;
                /* bottom */
                case 1:
                    dirReturn = 4;
                    break;
            }
        }
        return dirReturn;
    }

    public void setDir(int dir){
        switch (dir){
            /* left */
            case 1:
                if (getDir() != 2){
                    dirX = -1;
                    dirY = 0;
                }
                break;
            /* right */
            case 2:
                if (getDir() != 1){
                    dirX = 1;
                    dirY = 0;
                }
                break;
            /* top */
            case 3:
                if (getDir() != 4){
                    dirX = 0;
                    dirY = -1;
                }
                break;
            /* bottom */
            case 4:
                if (getDir() != 3){
                    dirX = 0;
                    dirY = 1;
                }
                break;
            default:
                break;
        }
    }

    public int[] getFruitPos(){
        return new int[] {gFruit.getX(), gFruit.getY()};
    }

    public boolean FrEaten(){
        return gFruit.isEaten();
    }
}