import java.awt.*;

public class CircleShape implements Shape {
    private int x, y, diameter;
    private Color fillColor;
    private Color strokeColor;
    private String name;

    public CircleShape(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x, y, diameter, diameter);
        g.setColor(strokeColor);
        g.drawOval(x, y, diameter, diameter);

        if (name != null && !name.trim().isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString(name, x, y + diameter + 15); // Рисуване на името под фигурата
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
        int centerX = this.x + diameter / 2;
        int centerY = this.y + diameter / 2;
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(diameter / 2, 2);
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
        return diameter;
    }

    @Override
    public int getHeight() {
        return diameter;
    }
    @Override
    public void resize(int newWidth, int newHeight) {
        this.diameter = newWidth;
        this.diameter = newHeight;
    }
    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
