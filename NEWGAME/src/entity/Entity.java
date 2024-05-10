package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public double x, y;
    public double speed;
    public char direction;
    public int count = 0;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
