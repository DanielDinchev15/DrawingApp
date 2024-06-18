import java.awt.*;
import java.io.Serializable;

public class OvalShape implements Shape, Serializable {
    private int x, y, width, height;
    private Color fillColor;
    private Color strokeColor;
    private String name;

    public OvalShape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x, y, width, height);
        g.setColor(strokeColor);
        g.drawOval(x, y, width, height);

        if (name != null && !name.trim().isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString(name, x, y + height + 15); // Рисуване на името под фигурата
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
        int centerX = this.x + width / 2;
        int centerY = this.y + height / 2;
        return (Math.pow(x - centerX, 2) / Math.pow(width / 2, 2) + Math.pow(y - centerY, 2) / Math.pow(height / 2, 2)) <= 1;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public void resize(int newWidth, int newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
