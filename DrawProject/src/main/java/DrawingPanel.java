import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawingPanel extends JPanel {
    private DialogProcessor dialogProcessor;

    public DrawingPanel(DialogProcessor dialogProcessor) {
        this.dialogProcessor = dialogProcessor;
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dialogProcessor.selectShapeAt(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                dialogProcessor.startDragging(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                dialogProcessor.stopDragging();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                dialogProcessor.dragTo(e.getX(), e.getY());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dialogProcessor.drawShapes(g);
    }
}
