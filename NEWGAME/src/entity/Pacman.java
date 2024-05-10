package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pacman extends Entity{
    public GamePanel gp;
    public GamePanel.MyKeyAdapter keyH;
    public BufferedImage[] images_open;
    public BufferedImage images_close;
    public int[][] coordinates;

    public Pacman(GamePanel gp, GamePanel.MyKeyAdapter keyH){
        this.gp = gp;
        this.keyH = keyH;
        coordinates = new int[8][2];
        setDefault();
        getPlayerImage();
    }

    public void setDefault(){
        this.x = 1;
        this.y = 1;
        System.out.println("pacman: " + direction);
        speed = 1;
        images_open = new BufferedImage[4];
    }

    public void getPlayerImage(){
        try{
            images_open[0] = ImageIO.read(getClass().getResourceAsStream("pacman/pacman_open_down.png"));
            images_open[1] = ImageIO.read(getClass().getResourceAsStream("pacman/pacman_open_left.png"));
            images_open[2] = ImageIO.read(getClass().getResourceAsStream("pacman/pacman_open_right.png"));
            images_open[3] = ImageIO.read(getClass().getResourceAsStream("pacman/pacman_open_up.png"));
            images_close = ImageIO.read(getClass().getResourceAsStream("pacman/pacman_close.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        boolean colission = true;
        if(x >= 27 && gp.direction == 'R'){
            x = -1;
        } else if(x < 1 && gp.direction == 'L'){
            x = 28;
        }

        if(gp.direction == 'D'){
            colission = this.gp.map.objects[(int) y+1][(int) x].collision;
        } else if(gp.direction == 'L'){
            colission = this.gp.map.objects[(int) y][(int) x - 1].collision;
        } else if(gp.direction == 'R'){
            colission = this.gp.map.objects[(int) y][(int) x + 1].collision;
        } else if(gp.direction == 'U'){
            colission = this.gp.map.objects[(int) y - 1][(int) x].collision;
        }

        if (!colission) {
            if (gp.direction == 'D') {
                this.y += speed;
            } else if (gp.direction == 'L') {
                this.x -= speed;
            } else if (gp.direction == 'R') {
                this.x += speed;
            } else if (gp.direction == 'U') {
                this.y -= speed;
            }
        }
        this.count++;
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage image = null;
        System.out.println(gp.direction);
        switch (gp.direction){
            case 'U':
                if(count%2 == 0) {
                    image = images_open[3];
                }
                if(count%2 == 1){
                    image = images_close;
                }
                break;
            case 'D':
                if(count%2 == 0) {
                    image = images_open[0];
                }
                if(count%2 == 1){
                    image = images_close;
                }
                break;
            case 'L':
                if(count%2 == 0) {
                    image = images_open[1];
                }
                if(count%2 == 1){
                    image = images_close;
                }
                break;
            case 'R':
                if(count%2 == 0) {
                    image = images_open[2];
                }
                if(count%2 == 1){
                    image = images_close;
                }
                break;
        }
        g2.drawImage(image, (int) x * this.gp.UNIT_SIZE, (int) y * this.gp.UNIT_SIZE, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
    }
}
