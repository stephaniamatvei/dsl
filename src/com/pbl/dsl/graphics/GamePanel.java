package com.pbl.dsl.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static char[][] maze = {{'*', '*', '*', '*', '*', ' ', '*', '*', '*', '*'},
                            {'*', ' ', ' ', ' ', ' ', ' ', 'E', '*', '*', '*'},
                            {'P', ' ', '*', ' ', '*', '*', '*', '*', '*', '*'},
                            {'*', ' ', ' ', ' ', ' ', ' ', '*', '*', '*', '*'},
                            {'*', '*', '*', '*', ' ', '*', '*', '*', '*', '*'}};
    static final int UNIT_SIZE = 60;
    static final int SCREEN_WIDTH = UNIT_SIZE * maze[0].length;
    static final int SCREEN_HEIGHT = UNIT_SIZE * maze.length;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    static final int DELAY = 75;
    int nSquares;
    public int posX;
    public int posY;
    int exitX;
    int exitY;
    char direction = 'R';
    boolean running = false;
    boolean endReached = false;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        setInit();
    }

    public void setInit(){
        int index = 0;
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++)
        {
            for (int j = 0; j < SCREEN_WIDTH / UNIT_SIZE; j++){
                if (maze[i][j] == 'P'){
                    posX = j;
                    posY = i;
                }
                else if (maze[i][j] == 'E'){
                    exitX = j;
                    exitY = i;
                }
                else if (maze[i][j] == '*'){
                    nSquares++;
                    x[index] = j;
                    y[index] = i;
                    index++;
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if (running) {
            // Draw grid
            for (int i = 0; i <= SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            }
            for (int i = 0; i <= SCREEN_WIDTH / UNIT_SIZE; i++) {
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            // Draw player
            g.setColor(Color.RED);
            g.fillRect(posX * UNIT_SIZE, posY * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

            // Draw exit
            g.setColor(Color.GREEN);
            g.fillRect(exitX * UNIT_SIZE, exitY * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

            // Draw squares
            g.setColor(Color.BLUE);
            for (int i = 0; i < nSquares; i++){
                g.fillRect(x[i] * UNIT_SIZE, y[i] * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            }
        }
        else{
            gameOver(g);
        }
    }

    public void move(){

    }

    public void checkCollisions(){
        for (int i = 0; i < nSquares; i++){
            if ((posX == x[i]) && (posY == y[i])){
                running = false;
            }
        }
        if ((posX == exitX) && (posY == exitY)){
            endReached = true;
            running = false;
        }
    }
    public void gameOver(Graphics g){
        //Game Over Text
        g.setFont(new Font("Dialog", Font.BOLD, 32));
        FontMetrics metrics = getFontMetrics(g.getFont());
        if (!endReached){
            g.setColor(Color.red);
            g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, (SCREEN_HEIGHT / 2));
        }
        else{
            g.setColor(Color.GREEN);
            g.drawString("You Won", (SCREEN_WIDTH - metrics.stringWidth("You Won"))/2, (SCREEN_HEIGHT / 2));
        }

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (running){
            move();
            checkCollisions();

        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            switch(e.getKeyCode()){
//                case KeyEvent.VK_LEFT:
//                    if (posX > 0){
//                        posX--;
//                    }
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    if (posX < SCREEN_WIDTH / UNIT_SIZE - 1){
//                        posX++;
//                    }
//                    break;
//                case KeyEvent.VK_UP:
//                    if (posY > 0){
//                        posY--;
//                    }
//                    break;
//                case KeyEvent.VK_DOWN:
//                    if (posY < SCREEN_HEIGHT / UNIT_SIZE - 1){
//                        posY++;
//                    }
//            }
        }
    }
}