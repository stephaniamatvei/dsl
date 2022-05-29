package com.pbl.dsl;

public class GameState {

    public boolean win;

    public int x;
    public int y;

    public int gridHeight;
    public int gridWidth;

    public GameState() {
        this.win = false;
        this.gridHeight = 10;
        this.gridWidth = 10;
        this.x = 5;
        this.y = 5;
    }

    public void moveRight() {
        if (this.x + 1 < this.gridWidth && !isWall(x + 1, y)) {
            this.x++;
        }
        // bind with graphical interface

        this.win = checkWin();
    }

    public void moveLeft() {
        if (this.x - 1 >= 0 && !isWall(x - 1, y)) {
            this.x--;
        }
        // bind with graphical interface

        this.win = checkWin();
    }

    public void moveUp() {
        if (this.y - 1 >= 0 && !isWall(x, y - 1)) {
            this.y--;
        }
        // bind with graphical interface

        this.win = checkWin();
    }

    public void moveDown() {
        if (this.y + 1 < this.gridHeight && !isWall(x, y + 1)) {
            this.y++;
        }
        // bind with graphical interface

        this.win = checkWin();
    }

    public boolean isWall(int x, int y) {
        // bind with graphical interface
        return false;
    }

    public boolean checkWin() {
        //bind with graphical interface
        return false;
    }
}
