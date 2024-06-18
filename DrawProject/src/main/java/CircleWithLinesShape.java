import java.awt.*;
import java.io.Serializable;

public class CircleWithLinesShape implements Shape, Serializable {
    private int x, y, diameter;
    private Color fillColor;
    private Color strokeColor;
    private String name;

    public CircleWithLinesShape(int x, int y, int diameter) {
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


        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;
        int radius = diameter / 2;


        g.drawLine(centerX, centerY - radius, centerX, centerY + radius);
        g.drawLine(centerX - radius, centerY, centerX + radius, centerY);


        g.drawLine(centerX - radius, centerY - radius / 2, centerX + radius, centerY - radius / 2); // Top horizontal line
        g.drawLine(centerX - radius, centerY + radius / 2, centerX + radius, centerY + radius / 2); // Bottom horizontal line
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
    public boolean contains(int x, int y) {
        int centerX = this.x + diameter / 2;
        int centerY = this.y + diameter / 2;
        return (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= Math.pow(diameter / 2, 2);
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
    public String getName() {
        return name;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Override
    public void resize(int newWidth, int newHeight) {
        this.diameter = Math.min(newWidth, newHeight);
    }
}
