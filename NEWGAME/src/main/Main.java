package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("BOMBIT");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
