import java.awt.*;
import java.io.Serializable;

public class TriangleShape implements Shape, Serializable {
    private int[] xPoints;
    private int[] yPoints;
    private Color fillColor;
    private Color strokeColor;
    private String name;

    public TriangleShape(int[] xPoints, int[] yPoints) {
        if (xPoints.length != 3 || yPoints.length != 3) {
            throw new IllegalArgumentException("Triangle must have exactly 3 points");
        }
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, 3);
        g.setColor(strokeColor);
        g.drawPolygon(xPoints, yPoints, 3);

        if (name != null && !name.trim().isEmpty()) {
            g.setColor(Color.BLACK);
            int minY = Math.min(yPoints[0], Math.min(yPoints[1], yPoints[2]));
            int maxY = Math.max(yPoints[0], Math.max(yPoints[1], yPoints[2]));
            g.drawString(name, getX() + getWidth() / 2, maxY + 15); // Рисуване на името под триъгълника
        }
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean contains(int x, int y) {
        Polygon polygon = new Polygon(xPoints, yPoints, 3);
        return polygon.contains(x, y);
    }

    @Override
    public int getX() {
        return xPoints[0];
    }

    @Override
    public int getY() {
        return yPoints[0];
    }

    @Override
    public int getWidth() {
        int minX = Math.min(xPoints[0], Math.min(xPoints[1], xPoints[2]));
        int maxX = Math.max(xPoints[0], Math.max(xPoints[1], xPoints[2]));
        return maxX - minX;
    }

    @Override
    public int getHeight() {
        int minY = Math.min(yPoints[0], Math.min(yPoints[1], yPoints[2]));
        int maxY = Math.max(yPoints[0], Math.max(yPoints[1], yPoints[2]));
        return maxY - minY;
    }

    @Override
    public void resize(int newWidth, int newHeight) {

    }


    @Override
    public void move(int dx, int dy) {
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] += dx;
            yPoints[i] += dy;
        }
    }

}
