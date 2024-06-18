import java.awt.*;

public class TrapezoidShape implements Shape {
    private int x, y, width, height;
    private Color fillColor;
    private Color strokeColor;
    private String name;

    public TrapezoidShape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        int[] xPoints = {x, x + width, x + (int)(width * 0.75), x + (int)(width * 0.25)};
        int[] yPoints = {y + height, y + height, y, y};
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, 4);
        g.setColor(strokeColor);
        g.drawPolygon(xPoints, yPoints, 4);

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
        Polygon trapezoid = new Polygon(
                new int[]{this.x, this.x + width, this.x + (int)(width * 0.75), this.x + (int)(width * 0.25)},
                new int[]{this.y + height, this.y + height, this.y, this.y},
                4
        );
        return trapezoid.contains(x, y);
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
