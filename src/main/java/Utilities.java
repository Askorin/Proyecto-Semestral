public class Utilities {
    public static Point getNormalizedVector(Point vector) {
        return getNormalizedVector(vector, 1);
    }
    public static Point getNormalizedVector(Point vector, int norm) {
        double vectorXnotNorm = (double)(vector.x);
        double vectorYnotNorm = (double)(vector.y);
        double vectorNorm = Math.sqrt(vectorXnotNorm * vectorXnotNorm
                +  vectorYnotNorm * vectorYnotNorm);
        int vectorX = (int)(norm * (vectorXnotNorm) / vectorNorm);
        int vectorY = (int)(norm * (vectorYnotNorm) / vectorNorm);
        return  new Point(vectorX, vectorY);
    }
}
