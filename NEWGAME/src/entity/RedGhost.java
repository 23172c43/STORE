package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class RedGhost extends Entity{
    public GamePanel gp;
    public GamePanel.MyKeyAdapter keyH;
    public BufferedImage[] images_open;
    public BufferedImage images_close;

    public RedGhost(GamePanel gp, GamePanel.MyKeyAdapter keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefault();
        getPlayerImage();
    }



    public void setDefault() {
        this.x = 11;
        this.y = 14;
    }

    public void getPlayerImage() {

    }
}
