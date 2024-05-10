package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BlackSquare extends Wall{
    public BlackSquare(String path, GamePanel gp){
        this.collision = false;
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
