import java.awt.*;

public interface Shape {
    void draw(Graphics g);
    void setFillColor(Color color);
    void setStrokeColor(Color color);
    void setName(String name);
    String getName();
    boolean contains(int x, int y);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void resize(int newWidth, int newHeight); // нов метод за преоразмеряване
    void move(int dx, int dy); // Нов метод за преместване
}
