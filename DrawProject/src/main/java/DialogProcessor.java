import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DialogProcessor implements Serializable {
    private List<Shape> shapeList;
    private Shape selectedShape;
    private boolean selectionMode;
    private Color fillColor = Color.RED;
    private Color strokeColor = Color.BLACK;
    private static int numberOfPrimitives = 0;

    public List<Shape> getShapes() {
        return shapeList;
    }

    public DialogProcessor() {
        shapeList = new ArrayList<>();
        selectionMode = false;
    }

    public void addRandomRectangle() {
        Random rnd = new Random();
        int x = rnd.nextInt(700);
        int y = rnd.nextInt(500);

        RectangleShape rect = new RectangleShape(x, y, 100, 50);
        rect.setFillColor(fillColor);
        rect.setStrokeColor(strokeColor);
        rect.setName("Rectangle" + ++numberOfPrimitives);
        shapeList.add(rect);

        nameShape(rect);
    }
    public void addRandomOval() {
        Random rnd = new Random();
        int x = rnd.nextInt(700);
        int y = rnd.nextInt(500);

        OvalShape oval = new OvalShape(x, y, 100, 50);
        oval.setFillColor(fillColor);
        oval.setStrokeColor(strokeColor);
        oval.setName("Oval" + ++numberOfPrimitives);
        shapeList.add(oval);

        nameShape(oval);
    }
    public void addRandomLine() {
        Random rnd = new Random();
        int x1 = rnd.nextInt(700);
        int y1 = rnd.nextInt(500);
        int x2 = rnd.nextInt(700);
        int y2 = rnd.nextInt(500);

        LineShape line = new LineShape(x1, y1, x2, y2);
        line.setStrokeColor(strokeColor);
        line.setName("Line" + ++numberOfPrimitives);
        shapeList.add(line);

        nameShape(line);
    }

    public void addRandomCircleWithLines() {
        Random rnd = new Random();
        int x = rnd.nextInt(700);
        int y = rnd.nextInt(500);
        int diameter = 100; // Примерен размер на кръга

        CircleWithLinesShape circle = new CircleWithLinesShape(x, y, diameter);
        circle.setFillColor(fillColor);
        circle.setStrokeColor(strokeColor);
        circle.setName("CircleWithLines" + ++numberOfPrimitives);
        shapeList.add(circle);

        nameShape(circle);
    }

    public void addRandomTriangle() {
        Random rnd = new Random();
        int x = rnd.nextInt(700);
        int y = rnd.nextInt(500);
        int size = 100; // Примерен размер на триъгълника

        int[] xPoints = {x, x + size / 2, x + size};
        int[] yPoints = {y + size, y, y + size};

        TriangleShape triangle = new TriangleShape(xPoints, yPoints);
        triangle.setFillColor(fillColor);
        triangle.setStrokeColor(strokeColor);
        triangle.setName("Triangle" + ++numberOfPrimitives);
        shapeList.add(triangle);

        nameShape(triangle);
    }

    public void addRandomCircle() {
        Random rnd = new Random();
        int x = rnd.nextInt(700);
        int y = rnd.nextInt(500);
        int diameter = rnd.nextInt(100) + 50; // случайен диаметър между 50 и 150 пиксела

        CircleShape circle = new CircleShape(x, y, diameter);
        circle.setFillColor(fillColor);
        circle.setStrokeColor(strokeColor);
        circle.setName("Circle" + ++numberOfPrimitives);
        shapeList.add(circle);

        nameShape(circle);
    }

    public void addRandomTrapezoid() {
        Random rnd = new Random();
        int x = rnd.nextInt(700);
        int y = rnd.nextInt(500);
        int width = rnd.nextInt(100) + 50;
        int height = rnd.nextInt(100) + 50;

        TrapezoidShape trapezoid = new TrapezoidShape(x, y, width, height);
        trapezoid.setFillColor(fillColor);
        trapezoid.setStrokeColor(strokeColor);
        shapeList.add(trapezoid);

        nameShape(trapezoid);
    }

    public void nameShape(Shape shape) {
        String name = JOptionPane.showInputDialog("Enter a name for the shape:");
        if (name != null && !name.trim().isEmpty()) {
            shape.setName(name);
        }
    }





    // Метод за задаване на режим на селектиране
    public void setSelectionMode(boolean selectionMode) {
        this.selectionMode = selectionMode;
    }

    // Метод за селектиране на форма по координатите на мишката
    public void selectShapeAt(int x, int y) {
        if (selectionMode) {
            selectedShape = null;
            for (Shape shape : shapeList) {
                if (shape.contains(x, y)) {
                    selectedShape = shape;
                    break;
                }
            }
        }
    }

    // Метод за промяна на цвета на запълване на избраната форма
    public void changeSelectedFillColor(Color color) {
        if (selectedShape != null) {
            selectedShape.setFillColor(color);
        }
    }

    // Метод за промяна на цвета на контура на избраната форма
    public void changeSelectedStrokeColor(Color color) {
        if (selectedShape != null) {
            selectedShape.setStrokeColor(color);
        }
    }

    // Метод за промяна на цвета на запълване на форма по име
    public void changeFillColorByName(String name, Color color) {
        for (Shape shape : shapeList) {
            if (shape.getName().equals(name)) {
                shape.setFillColor(color);
                break;
            }
        }
    }

    // Метод за промяна на цвета на контура на форма по име
    public void changeStrokeColorByName(String name, Color color) {
        for (Shape shape : shapeList) {
            if (shape.getName().equals(name)) {
                shape.setStrokeColor(color);
                break;
            }
        }
    }
    // Метод за изчистване на всички форми
    public void clearShapes() {
        shapeList.clear();
        selectedShape = null;
    }

    // Метод за триене на избраната форма
    public void deleteSelectedShape() {
        if (selectedShape != null) {
            shapeList.remove(selectedShape);
            selectedShape = null;
        }
    }
    public void drawShapes(Graphics g) {
        for (Shape shape : shapeList) {
            shape.draw(g);
            if (shape == selectedShape) {
                g.setColor(Color.BLUE);
                g.drawRect(shape.getX() - 2, shape.getY() - 2, shape.getWidth() + 4, shape.getHeight() + 4);
            }
        }
    }



    public void resizeSelectedShape(int newWidth, int newHeight) {
        if (selectedShape != null) {
            selectedShape.resize(newWidth, newHeight);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shapeList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            shapeList = (List<Shape>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

// преместване
private boolean isDragging = false;
    private int dragStartX, dragStartY;

    public void startDragging(int x, int y) {
        if (selectionMode && selectedShape != null) {
            isDragging = true;
            dragStartX = x;
            dragStartY = y;
        }
    }

    public void dragTo(int x, int y) {
        if (isDragging && selectedShape != null) {
            int dx = x - dragStartX;
            int dy = y - dragStartY;
            selectedShape.move(dx, dy);
            dragStartX = x;
            dragStartY = y;
        }
    }

    public void stopDragging() {
        isDragging = false;
    }
}
