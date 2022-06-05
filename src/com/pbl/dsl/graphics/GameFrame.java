package com.pbl.dsl.graphics;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

    public GamePanel gamePanel;
    public GameFrame(){
        this.gamePanel = new GamePanel();
        this.add(this.gamePanel);
        this.setTitle("Maze Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}