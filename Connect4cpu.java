public class Connect4cpu {
    static int [][] gameboard;

    public Connect4cpu(int[][] gb){
        gameboard = gb;
    }

    public static int randomguess() {
        boolean full = true;
        int c = 0;

        while (full) {
            c = (int) (Math.random() * 7);
            for (int r = 0; r < 6; r++) {
                if (gameboard[c][r] == 2) {}
                else {
                    full = false;
                }
            }
        }
        return c;
    }


    public static void updateboard(int x, int y) {
        gameboard[x][y] = 2;
    }

}

