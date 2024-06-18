import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private DrawingPanel drawingPanel;
    private DialogProcessor dialogProcessor;

    private JMenu shapeMenu;

    public MainFrame() {
        setTitle("Drawing Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        dialogProcessor = new DialogProcessor();
        drawingPanel = new DrawingPanel(dialogProcessor);

        add(drawingPanel, BorderLayout.CENTER);
        add(createToolbar(), BorderLayout.NORTH);
        setJMenuBar(createMenuBar());
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();

        JButton addRectangleButton = new JButton("Add Rectangle");
        addRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.addRandomRectangle();
                updateShapeMenu();
                drawingPanel.repaint();
            }
        });
        toolbar.add(addRectangleButton);

        JButton addOvalButton = new JButton("Add Oval");
        addOvalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.addRandomOval();
                updateShapeMenu();
                drawingPanel.repaint();
            }
        });
        toolbar.add(addOvalButton);

        JButton addTriangleButton = new JButton("Add Triangle");
        addTriangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.addRandomTriangle();
                updateShapeMenu();
                drawingPanel.repaint();
            }
        });
        toolbar.add(addTriangleButton);

        JButton addCircleButton = new JButton("Add Circle");
        addCircleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.addRandomCircle();
                updateShapeMenu();
                drawingPanel.repaint();
            }
        });
        toolbar.add(addCircleButton);

        JButton addTrapezoidButton = new JButton("Add Trapezoid");
        addTrapezoidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.addRandomTrapezoid();
                updateShapeMenu();
                drawingPanel.repaint();
            }
        });
        toolbar.add(addTrapezoidButton);


        JButton addCircleWithLinesButton = new JButton("Add Circle with Lines");
        addCircleWithLinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.addRandomCircleWithLines();
                drawingPanel.repaint();
            }
        });

        toolbar.add(addCircleWithLinesButton);

        JButton addLineButton = new JButton("Add Line");
        addLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.addRandomLine();
                drawingPanel.repaint();
            }
        });
        toolbar.add(addLineButton);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.setSelectionMode(true);
            }
        });
        toolbar.add(selectButton);

        JButton resizeButton = new JButton("Resize");
        resizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newWidthStr = JOptionPane.showInputDialog("Enter new width:");
                String newHeightStr = JOptionPane.showInputDialog("Enter new height:");
                if (newWidthStr != null && newHeightStr != null) {
                    try {
                        int newWidth = Integer.parseInt(newWidthStr);
                        int newHeight = Integer.parseInt(newHeightStr);
                        dialogProcessor.resizeSelectedShape(newWidth, newHeight);
                        drawingPanel.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values.");
                    }
                }
            }
        });
        toolbar.add(resizeButton);


        JButton fillColorButton = new JButton("Fill Color");
        fillColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.RED);
                if (fillColor != null) {
                    dialogProcessor.changeSelectedFillColor(fillColor);
                    drawingPanel.repaint();
                }
            }
        });
        toolbar.add(fillColorButton);


        JButton changeFillColorByNameButton = new JButton("Change Fill Color by Name");
        changeFillColorByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter shape name:");
                if (name != null) {
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.RED);
                    if (fillColor != null) {
                        dialogProcessor.changeFillColorByName(name, fillColor);
                        drawingPanel.repaint();
                    }
                }
            }
        });
        toolbar.add(changeFillColorByNameButton);

        JButton changeStrokeColorByNameButton = new JButton("Change Stroke Color by Name");
        changeStrokeColorByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter shape name:");
                if (name != null) {
                    Color strokeColor = JColorChooser.showDialog(null, "Choose Stroke Color", Color.BLACK);
                    if (strokeColor != null) {
                        dialogProcessor.changeStrokeColorByName(name, strokeColor);
                        drawingPanel.repaint();
                    }
                }
            }
        });

        toolbar.add(changeStrokeColorByNameButton);

        JButton strokeColorButton = new JButton("Stroke Color");
        strokeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color strokeColor = JColorChooser.showDialog(null, "Choose Stroke Color", Color.BLACK);
                if (strokeColor != null) {
                    dialogProcessor.changeSelectedStrokeColor(strokeColor);
                    drawingPanel.repaint();
                }
            }
        });
        toolbar.add(strokeColorButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.clearShapes();
                drawingPanel.repaint();
            }
        });
        toolbar.add(clearButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.deleteSelectedShape();
                drawingPanel.repaint();
            }
        });
        toolbar.add(deleteButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getPath();
                    dialogProcessor.saveToFile(filename);
                }
            }
        });
        toolbar.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getPath();
                    dialogProcessor.loadFromFile(filename);
                    updateShapeMenu();
                    drawingPanel.repaint();
                }
            }
        });
        toolbar.add(loadButton);

        JButton newWindowButton = new JButton("New Window");
        newWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame newFrame = new MainFrame();
                newFrame.setVisible(true);
            }
        });
        toolbar.add(newWindowButton);

        return toolbar;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getPath();
                    dialogProcessor.saveToFile(filename);
                }
            }
        });
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getPath();
                    dialogProcessor.loadFromFile(filename);
                    updateShapeMenu();
                    drawingPanel.repaint();
                }
            }
        });
        fileMenu.add(loadItem);

        menuBar.add(fileMenu);

        shapeMenu = new JMenu("Shapes");
        menuBar.add(shapeMenu);

        JMenu operationsMenu = new JMenu("Operations");
        JMenuItem selectItem = new JMenuItem("Select");
        selectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogProcessor.setSelectionMode(true);
            }
        });
        operationsMenu.add(selectItem);

        JMenuItem fillColorItem = new JMenuItem("Fill Color");
        fillColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.RED);
                if (fillColor != null) {
                    dialogProcessor.changeSelectedFillColor(fillColor);
                    drawingPanel.repaint();
                }
            }
        });
        operationsMenu.add(fillColorItem);

        JMenuItem strokeColorItem = new JMenuItem("Stroke Color");
        strokeColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color strokeColor = JColorChooser.showDialog(null, "Choose Stroke Color", Color.BLACK);
                if (strokeColor != null) {
                    dialogProcessor.changeSelectedStrokeColor(strokeColor);
                    drawingPanel.repaint();
                }
            }
        });
        operationsMenu.add(strokeColorItem);

        JMenuItem changeFillColorByNameItem = new JMenuItem("Stroke Color");
        changeFillColorByNameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter shape name:");
                if (name != null) {
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.RED);
                    if (fillColor != null) {
                        dialogProcessor.changeFillColorByName(name, fillColor);
                        drawingPanel.repaint();
                    }
                }
            }
        });
        operationsMenu.add(changeFillColorByNameItem);

        JMenuItem changeStrokeColorByNameItem = new JMenuItem("Change Stroke Color by Name");
        changeStrokeColorByNameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter shape name:");
                if (name != null) {
                    Color strokeColor = JColorChooser.showDialog(null, "Choose Stroke Color", Color.BLACK);
                    if (strokeColor != null) {
                        dialogProcessor.changeStrokeColorByName(name, strokeColor);
                        drawingPanel.repaint();
                    }
                }
            }
        });
        operationsMenu.add(changeStrokeColorByNameItem);


        JMenuItem resizeItem = new JMenuItem("Resize");
        resizeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newWidthStr = JOptionPane.showInputDialog("Enter new width:");
                String newHeightStr = JOptionPane.showInputDialog("Enter new height:");
                if (newWidthStr != null && newHeightStr != null) {
                    try {
                        int newWidth = Integer.parseInt(newWidthStr);
                        int newHeight = Integer.parseInt(newHeightStr);
                        dialogProcessor.resizeSelectedShape(newWidth, newHeight);
                        drawingPanel.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values.");
                    }
                }
            }
        });
        operationsMenu.add(resizeItem);

        menuBar.add(operationsMenu);

        return menuBar;
    }

    private void updateShapeMenu() {
        shapeMenu.removeAll();
        for (Shape shape : dialogProcessor.getShapes()) {
            JMenuItem shapeItem = new JMenuItem(shape.getName());
            shapeItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogProcessor.selectShapeAt(shape.getX(), shape.getY());
                    drawingPanel.repaint();
                }
            });
            shapeMenu.add(shapeItem);
        }
        shapeMenu.revalidate();
        shapeMenu.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
