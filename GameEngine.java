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
    public void SnakeMove(){
        for (int i=getSnakeSize()-1; i!=0; i--){
            body[i].x = body[i].topLevel.x;
            body[i].y = body[i].topLevel.y;
        }
        body[0].x += dirX;
        body[0].y += dirY;
        if (body[0].x < 0 || body[0].x > w || body[0].y < 0 || body[0].y > h){
            SnakeAlive = false;
        }
    }
    public int getDir(){
        int dirReturn = 0;
        /* horizontal */
        if (dirX != 0){
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
        else if (dirY !=0){
            switch (dirX) {
                /* bottom */
                case -1:
                    dirReturn = 3;
                    break;
                /* top */
                case 1:
                    dirReturn = 4;
                    break;
            }
        }
        return dirReturn;
    }
    /* private method */
}