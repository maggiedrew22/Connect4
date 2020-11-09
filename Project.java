public class Project {
    static boolean player;
    static int col;
    static int row;
    static int width = 8;
    static int height = 6;
    static int[][] gameboard = initialize(width, height);
    static int [][] compgameboard = initialize(width, height);
    static Connect4cpu comp = new Connect4cpu(compgameboard);


    public static void main(String[] args) {

        drawaskplayers();
        asknumplayers();

        if (player == true) {
            while (getWinner(gameboard) == 0) {
                StdDraw.clear();
                drawgameboard(gameboard, width);
                StdDraw.setPenColor(StdDraw.YELLOW);
                StdDraw.text(.5, .85, "Player 1, please click on a column to place your token");
                askplayer(gameboard, 1);
                StdDraw.pause(100);
                StdDraw.clear();
                if (getWinner(gameboard) == 0) {
                    drawgameboard(gameboard, width);
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.text(.5, .85, "Player 2, please click on a column to place your token");
                    askplayer(gameboard, 2);
                    StdDraw.pause(100);
                }
            }

            printWinner(getWinner(gameboard), gameboard);

        }

        else {
            while (getWinner(gameboard) == 0) {
                StdDraw.clear();
                drawgameboard(gameboard, width);
                StdDraw.setPenColor(StdDraw.YELLOW);
                StdDraw.text(.5, .85, "Player 1, please click on a column to place your token");
                askplayer(gameboard, 1);
                comp.updateboard(col, row);
                StdDraw.pause(100);
                StdDraw.clear();
                if (getWinner(gameboard) == 0) {
                    drawgameboard(gameboard, width);
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.text(.5,.85,"Computer will now pick a column to place a token");
                    int c = comp.randomguess();
                    for (int r = 0; r < gameboard[0].length; r++) {
                        row = r;
                        if(gameboard[c][r] == 0) {
                            gameboard[c][r] = 2;
                            r = gameboard[0].length;
                        }
                    }
                    comp.updateboard(col, row);
                    StdDraw.pause(2000);
                    StdDraw.clear();
                }
            }

            printWinnercpu(getWinner(gameboard), gameboard);

        }

    }



    public static void askplayer(int[][] gameboard, int plays) {
        boolean t = true;
        while (t == true) {
            int c = -1;
            while (!StdDraw.isMousePressed()) {
                StdDraw.pause(10);
            }
            double x = StdDraw.mouseX();
            while (StdDraw.isMousePressed()) {
                StdDraw.pause(10);
            }
            if (x <= .125) {
                c = 0;
            }
            if (x > .125 && x <= .25) {
                c = 1;
            }
            if (x > .25 && x <= .375) {
                c = 2;
            }
            if (x > .375 && x <= .5) {
                c = 3;
            }
            if (x > .5 && x <= .625) {
                c = 4;
            }
            if (x > .625 && x <= .75) {
                c = 5;
            }
            if (x > .75 && x <= .875) {
                c = 6;
            }
            if (x > .875 && x <= 1) {
                c = 7;
            }
            col = c;
            for (int r = 0; r < gameboard[0].length; r++) {
                row = r;
                if (gameboard[c][r] == 0) {
                    if (plays == 1) {
                        gameboard[c][r] = 1;
                        return;
                    } else if (plays == 2) {
                        gameboard[c][r] = 2;
                        return;
                    }
                }
            }
            for (int r = 0; r < gameboard[0].length; r++) {
                if (gameboard[c][r] == 1 || gameboard[c][r] == 2) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.text(.5, .825, "Error! Please select a new column to place your token");
                    StdDraw.pause(100);
                    continue;
                }
            }
            //t = false;
            //drawgameboard(gameboard, 8);
        }
    }


    public static void drawgameboard(int[][] gameboard, int w) {
        StdDraw.setCanvasSize(750, 750);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledSquare(.5, .5, .5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(.5, .9, "Welcome to Connect 4!");
        for (int c = 0; c < gameboard.length; c++) {
            for (int r = 0; r < gameboard[1].length; r++) {
                if (gameboard[c][r] == 0) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledCircle((c + 1.0) / (w) - .0625, (r + 1.0) / (w) - .0625, .05);
                } else if (gameboard[c][r] == 1) {
                    StdDraw.setPenColor(StdDraw.YELLOW);
                    StdDraw.filledCircle((c + 1.0) / (w) - .0625, (r + 1.0) / (w) - .0625, .05);
                } else if (gameboard[c][r] == 2) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledCircle((c + 1.0) / (w) - .0625, (r + 1.0) / (w) - .0625, .05);
                }
            }
        }
    }


    public static int[][] initialize(int width, int height) {
        int[][] gameboard = new int[width][height];
        for (int c = 0; c < gameboard.length; c++) {
            for (int r = 0; r < gameboard[1].length; r++) {
                gameboard[c][r] = 0;
            }
        }
        return gameboard;
    }

    public static int getWinner(int[][] gameboard) {
        for(int c = 0; c < gameboard.length; c++) {
            for(int r = 0; r < gameboard[0].length; r++) {
                if(getState(gameboard, c, r) == getState(gameboard, c, r - 1) && getState(gameboard, c, r) == getState(gameboard, c, r - 2) && getState(gameboard, c, r) == getState(gameboard, c, r - 3) && gameboard[c][r] != 0) {
                    return gameboard[c][r];
                }
                if(getState(gameboard, c, r) == getState(gameboard, c + 1, r) && getState(gameboard, c, r) == getState(gameboard, c + 2, r) && getState(gameboard, c, r) == getState(gameboard, c + 3, r) && gameboard[c][r] != 0) {
                    return gameboard[c][r];
                }
                if(getState(gameboard, c, r) == getState(gameboard, c + 1, r - 1) && getState(gameboard, c, r) == getState(gameboard, c + 2, r - 2) && getState(gameboard, c, r) == getState(gameboard, c + 3, r - 3) && gameboard[c][r] != 0) {
                    return gameboard[c][r];
                }
                if(getState(gameboard, c, r) == getState(gameboard, c - 1, r - 1) && getState(gameboard, c, r) == getState(gameboard, c - 2, r - 2) && getState(gameboard, c, r) == getState(gameboard, c - 3, r - 3) && gameboard[c][r] != 0) {
                    return gameboard[c][r];
                }
            }
        }
        for(int c = 0; c < gameboard.length; c++) {
            for (int r = 0; r < gameboard[0].length; r++) {
                if(gameboard[c][r] == 0) {
                    return 0;
                }
            }
        }
        return 3;
    }

    public static int getState(int[][]gameboard, int c, int r) {
        if (c < 0) {
            return 0;
        }
        if (r < 0) {
            return 0;
        }
        if (c >= gameboard.length) {
            return 0;
        }
        if (r >= gameboard[0].length) {
            return 0;
        }
        return gameboard[c][r];
    }

    public static void printWinner(int wins, int[][] gameboard) {
        StdDraw.clear();
        drawgameboard(gameboard, 8);
        if (wins == 1) {
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.text(.5, .85, "Player 1 is the winner!");
        }
        if (wins == 2) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(.5, .85, "Player 2 is the winner!");
        }
        if (wins == 3) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.text(.5, .85, "Game ends in a tie!");
        }
    }

    public static void printWinnercpu(int wins, int[][] gameboard) {
        StdDraw.clear();
        drawgameboard(gameboard, 8);
        if (wins == 1) {
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.text(.5, .85, "Player 1 is the winner!");
        }
        if (wins == 2) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(.5, .85, "Computer is the winner!");
        }
        if (wins == 3) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.text(.5, .85, "Game ends in a tie!");
        }
    }

    public static void drawaskplayers() {
        StdDraw.setCanvasSize(750,750);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledSquare(.5,.5,.5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(.5,.7, "Welcome to Connect 4!");
        StdDraw.text(.5,.6, "Please pick the number of players");
        StdDraw.filledRectangle(.25,.4,.2,.1);
        StdDraw.filledRectangle(.75,.4,.2,.1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25,.4, "2 Player");
        StdDraw.text(.75, .4, "1 Player");
    }

    public static void asknumplayers() {

        while (!StdDraw.isMousePressed()) {
            StdDraw.pause(10);
        }
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        while (StdDraw.isMousePressed()) {
            StdDraw.pause(10);
        }
        if (x > .05 && x < .45) {
            if (y > .3 && y < .5) {
                player = true;
            }
        }
        else if (x > .55 && x < .95) {
            if (y > .3 && y < .5) {
                player = false;
            }
        }
    }

}


