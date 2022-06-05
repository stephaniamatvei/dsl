package com.pbl.dsl;

import com.pbl.dsl.graphics.GameFrame;

public class GameState {

    public boolean win;

    public int x;
    public int y;

    public int gridHeight;
    public int gridWidth;

    private GameFrame gameFrame;

    public GameState() {
        this.win = false;
        this.gridHeight = 5;
        this.gridWidth = 10;
        this.x = 0;
        this.y = 2;
        this.gameFrame = new GameFrame();
    }

    public void moveRight() {
        if (this.x + 1 < this.gridWidth && !isWall(x + 1, y)) {
            this.x++;
            gameFrame.gamePanel.posX++;
        }
        // bind with graphical interface

        this.win = checkWin();
    }

    public void moveLeft() {
        if (this.x - 1 >= 0 && !isWall(x - 1, y)) {
            this.x--;
            gameFrame.gamePanel.posX--;
        }
        // bind with graphical interface

        this.win = checkWin();
    }

    public void moveUp() {
        if (this.y - 1 >= 0 && !isWall(x, y - 1)) {
            this.y--;
            gameFrame.gamePanel.posY--;
        }
        // bind with graphical interface

        this.win = checkWin();
    }

    public void moveDown() {
        if (this.y + 1 < this.gridHeight && !isWall(x, y + 1)) {
            this.y++;
            gameFrame.gamePanel.posY++;
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
