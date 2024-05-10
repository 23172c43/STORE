package map;

import main.GamePanel;
import tile.BlackSquare;
import tile.BlueSquare;
import tile.Wall;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoadMap {
    public GamePanel gp;
    public int[][] map;
    public Wall[][] objects;

    public LoadMap(GamePanel gp, String fileName){
        this.gp = gp;
        map = new int[gp.SCREEN_HEIGHT / gp.UNIT_SIZE][gp.SCREEN_WIDTH / gp.UNIT_SIZE];
        objects = new Wall[gp.SCREEN_HEIGHT / gp.UNIT_SIZE][gp.SCREEN_WIDTH / gp.UNIT_SIZE];
        loadmap(fileName);
    }

    public void loadmap(String fileName){
        try{
            File file = new File(fileName);
            Scanner in = new Scanner(file);
            for(int i = 0; i < map.length; i++){
                String[] numMap = in.nextLine().trim().split(" ");
                for(int j = 0; j < map[0].length; j++){
                    map[i][j] = Integer.parseInt(numMap[j]);
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            setObjects();
            in.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setObjects(){
        for(int i = 0; i < objects.length; i++){
            for(int j = 0; j < objects[0].length; j++){
                if(map[i][j] == 1){
                    objects[i][j] = new BlueSquare("wall.png", gp);
                }

                if(map[i][j] == 0){
                    objects[i][j] = new BlackSquare("road.png", gp);
                }
            }
        }
    }

    public void draw(Graphics g2){
        for(int i = 0; i < objects.length; i++){
            for(int j = 0; j < objects[0].length; j++){
                if(objects[i][j] != null && objects[i][j].image != null) {
                    objects[i][j].setRectangle(gp.UNIT_SIZE * i, gp.UNIT_SIZE * j);
                    g2.drawImage(objects[i][j].image, gp.UNIT_SIZE * j, gp.UNIT_SIZE * i, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
                }

            }
        }
    }
}
