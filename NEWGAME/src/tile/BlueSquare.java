package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Rectangle;

public class BlueSquare extends Wall{
    public BlueSquare(String path, GamePanel gp){
        this.collision = true;
        this.gp = gp;
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setRectangle(int x, int y) {
        this.rectangle = new Rectangle(x, y, gp.UNIT_SIZE, gp.UNIT_SIZE);
    }
}
