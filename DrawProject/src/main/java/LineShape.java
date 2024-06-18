import java.awt.*;
import java.io.Serializable;

public class LineShape implements Shape, Serializable {
    private int x1, y1, x2, y2;
    private Color strokeColor;
    private String name;

    public LineShape(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(strokeColor);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void setFillColor(Color color) {
        // Линията няма запълване
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
        return false; // Линиите няма да са селектируеми
    }

    @Override
    public int getX() {
        return Math.min(x1, x2);
    }

    @Override
    public int getY() {
        return Math.min(y1, y2);
    }

    @Override
    public int getWidth() {
        return Math.abs(x2 - x1);
    }

    @Override
    public int getHeight() {
        return Math.abs(y2 - y1);
    }
    @Override
    public void resize(int newWidth, int newHeight) {

    }
    @Override
    public void move(int dx, int dy) {

    }
}
