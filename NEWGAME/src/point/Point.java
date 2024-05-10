package point;

public class Point {
    //FIELD
    public double x;
    public double y;

    //CONSTRUCTOR
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //GETTER AND SETTER

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //METHOD
        //DISTANCE
    /**
     * Returns the distance between this point and another point.
     * @param another The other point to which the distance is calculated.
     * @return The Euclidean distance between this point and another point.
     */
    public double getDistance(Point another){
        return Math.sqrt(Math.pow(this.x - another.getX(), 2) + Math.pow(this.y - another.getY(), 2));
    }


    public double getDistance(double x, double y){
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

        //TOSTRING

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
