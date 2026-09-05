package ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import model.Point;

public class Canvas extends JPanel {
    private List<Point> points;
    private List<Point> chaikinPoints;
    private boolean showLines = false;

    public Canvas() {
        setBackground(Color.BLACK);
        points = new ArrayList<>();
        chaikinPoints = new ArrayList<>();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                double x = e.getX();
                double y = e.getY();
                points.add(new Point(x, y));
                repaint();
            }
        });
    }

    public void setShowLines(boolean show) {
        this.showLines = show;
        repaint();
    }

    public void setChaikinPoints(List<Point> newPoints) {
        this.chaikinPoints = newPoints;
        repaint();
    }

    public List<Point> getPoints() {
        return points;
    }

    public void clear() {
        points.clear();
        if (chaikinPoints != null) {
            chaikinPoints.clear();
        }
        showLines = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (showLines && (chaikinPoints == null || chaikinPoints.isEmpty())) {
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                g2d.drawLine(
                    (int) p1.getX(), (int) p1.getY(),
                    (int) p2.getX(), (int) p2.getY()
                );
            }
        }

        if (chaikinPoints != null && !chaikinPoints.isEmpty()) {
            g2d.setColor(Color.GREEN);
            for (int i = 0; i < chaikinPoints.size() - 1; i++) {
                Point p1 = chaikinPoints.get(i);
                Point p2 = chaikinPoints.get(i + 1);
                g2d.drawLine(
                    (int) p1.getX(), (int) p1.getY(),
                    (int) p2.getX(), (int) p2.getY()
                );
            }
        }

        g2d.setColor(Color.WHITE);
        int radius = 4;
        for (Point p : points) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }
    }
}