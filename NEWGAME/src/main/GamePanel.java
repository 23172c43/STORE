package main;

import entity.BlueGhost;
import entity.Pacman;
import map.LoadMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    //SCREEN SETTING
    public static final int ORIGINAL_WIDTH = 28;
    public static final int ORIGINAL_HEIGHT = 31;
    public static final int UNIT_SIZE = 20;
    public static final int SCREEN_WIDTH = ORIGINAL_WIDTH * UNIT_SIZE;
    public static final int SCREEN_HEIGHT = ORIGINAL_HEIGHT * UNIT_SIZE;
    static final int DELAY = 120;
    public char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    //KEY


    //MAP
    public LoadMap map = new LoadMap(this, "data/map.txt");

    //Pacman
    public Pacman pacman = new Pacman(this, new MyKeyAdapter());

    //GHOST
    BlueGhost blueGhost = new BlueGhost(this, new MyKeyAdapter());

    public GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setFocusable(true);
        startGame();

    }

    public void startGame(){
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        for(int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++){
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
        }

        for(int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++){
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        map.draw(g);
        pacman.draw(g);
        blueGhost.draw(g);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //
        if(running){
//            move();
//            checkApple();
//            checkCollisions();

        }
        pacman.update();
        blueGhost.update();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    direction = 'L';
                    running = true;
                    break;
                case KeyEvent.VK_D:
                    direction = 'R';
                    break;
                case KeyEvent.VK_W:
                    direction = 'U';
                    break;
                case KeyEvent.VK_S:
                    direction = 'D';
                    break;
            }
        }
    }
}