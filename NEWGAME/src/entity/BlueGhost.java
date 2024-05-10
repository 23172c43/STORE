package entity;

import main.GamePanel;
import point.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class BlueGhost extends Entity{
    public GamePanel gp;
    public GamePanel.MyKeyAdapter keyH;
    public BufferedImage[] images_open;
    public BufferedImage images_close;
    public char[] directionArr = {'R', 'L', 'U', 'D'};

    public BlueGhost(GamePanel gp, GamePanel.MyKeyAdapter keyH){
        this.gp = gp;
        this.keyH = keyH;
        this.direction = 'L';
        setDefault();
        getPlayerImage();
    }

    private void setDefault() {
        this.x = 14;
        this.y = 14;
        System.out.println("BlueGhost: " + direction);
        speed = 0.25;
        images_open = new BufferedImage[4];
    }

    public void getPlayerImage(){
        try{
            images_open[0] = ImageIO.read(getClass().getResourceAsStream("blueghost/pacman_open_down.png"));
            images_open[1] = ImageIO.read(getClass().getResourceAsStream("blueghost/pacman_open_left.png"));
            images_open[2] = ImageIO.read(getClass().getResourceAsStream("blueghost/pacman_open_right.png"));
            images_open[3] = ImageIO.read(getClass().getResourceAsStream("blueghost/pacman_open_up.png"));
            images_close = ImageIO.read(getClass().getResourceAsStream("blueghost/pacman_close.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        moveTowardsTarget();
    }

    private void moveTowardsTarget() {
        int targetX = (int) gp.pacman.x;
        int targetY = (int) gp.pacman.y;
        Point target = new Point(targetX, targetY);
        Point current = new Point((int) this.x, (int) this.y);

        List<Point> possibleMoves = getPossibleMoves(current);
        Point bestMove = chooseBestMove(possibleMoves, target);

        if (bestMove != null) {
            this.x = bestMove.x;
            this.y = bestMove.y;
        } else {
            handleStuckSituation(current);
        }
    }

    private void handleStuckSituation(Point current) {
        // Thử di chuyển ngẫu nhiên để thoát khỏi tình trạng kẹt
        Point randomMove = moveRandomly(current);
        if (randomMove != null) {
            this.x = randomMove.x;
            this.y = randomMove.y;
        }
    }

    private Point moveRandomly(Point current) {
        List<Point> moves = getPossibleMoves(current);
        if (!moves.isEmpty()) {
            return moves.get(new Random().nextInt(moves.size()));
        }
        return null;
    }

    private List<Point> getPossibleMoves(Point current) {
        List<Point> moves = new ArrayList<>();
        // Kiểm tra mỗi hướng có thể và thêm vào danh sách nếu không có va chạm
        if (current.x > 0 && !gp.map.objects[(int) current.y][(int) current.x - 1].collision) {
            moves.add(new Point(current.x - 1, current.y)); // Trái
        }
        if (current.x < gp.ORIGINAL_WIDTH - 1 && !gp.map.objects[(int) current.y][(int) current.x + 1].collision) {
            moves.add(new Point(current.x + 1, current.y)); // Phải
        }
        if (current.y > 0 && !gp.map.objects[(int) current.y - 1][(int) current.x].collision) {
            moves.add(new Point(current.x, current.y - 1)); // Lên
        }
        if (current.y < gp.ORIGINAL_HEIGHT - 1 && !gp.map.objects[(int) current.y + 1][(int) current.x].collision) {
            moves.add(new Point(current.x, current.y + 1)); // Xuống
        }
        return moves;
    }

    private Point chooseBestMove(List<Point> moves, Point target) {
        Point bestMove = null;
        double minDistance = Double.MAX_VALUE;
        for (Point move : moves) {
            double distance = move.getDistance(target);
            if (distance < minDistance) {
                minDistance = distance;
                bestMove = move;
            }
        }
        return bestMove;
    }




    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage image = this.count % 2 == 0 ? images_open[directionToIndex(this.direction)] : images_open[directionToIndex(this.direction)];
        g2.drawImage(image, (int) this.x * gp.UNIT_SIZE, (int) this.y * gp.UNIT_SIZE, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
    }

    private int directionToIndex(char dir) {
        switch (dir) {
            case 'U': return 3;
            case 'D': return 0;
            case 'L': return 1;
            case 'R': return 2;
        }
        return 0; // Default to down if direction is unknown
    }
}
