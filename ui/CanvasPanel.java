package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import model.Point;

public class CanvasPanel extends JPanel {
  private static final int POINT_RADIUS = 6;

  private final List<Point> controlPoints = new ArrayList<>();
  private List<Point> currentCurvePoints = new ArrayList<>();
  private String statusMessage = "Left-Click to place points. Press ENTER to animate. ESC to exit.";
  private int currentStep = 0;

  public CanvasPanel() {
    // Set background to pure black
    setBackground(Color.BLACK);

    addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
              controlPoints.add(new Point(e.getX(), e.getY()));
              currentCurvePoints = new ArrayList<>(controlPoints);
              currentStep = 0;
              repaint();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
              clear();
            }
          }
        });
  }

  public List<Point> getControlPoints() {
    return controlPoints;
  }

  public void setCurvePoints(List<Point> points, int step) {
    this.currentCurvePoints = points;
    this.currentStep = step;
    repaint();
  }

  public void setStatusMessage(String message) {
    this.statusMessage = message;
    repaint();
  }

  public void clear() {
    controlPoints.clear();
    currentCurvePoints.clear();
    currentStep = 0;
    statusMessage = "Cleared. Left-Click to place points. Press ENTER to animate.";
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // 1. Draw Text UI (White/Light gray on black)
    g2.setColor(new Color(200, 200, 200));
    g2.drawString(statusMessage, 20, 30);
    if (!controlPoints.isEmpty()) {
      g2.setColor(new Color(150, 150, 150));
      g2.drawString(
          "Control Points: " + controlPoints.size() + " | Step: " + currentStep + "/7", 20, 50);
    }

    // 2. Draw the Curve (Neon Cyan)
    if (currentCurvePoints.size() >= 2) {
      g2.setColor(new Color(0, 255, 204)); // Neon Cyan
      g2.setStroke(new java.awt.BasicStroke(2.0f)); // Make curve slightly thicker
      for (int i = 0; i < currentCurvePoints.size() - 1; i++) {
        Point p1 = currentCurvePoints.get(i);
        Point p2 = currentCurvePoints.get(i + 1);
        g2.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
      }
    }

    // 3. Draw the Curve Points (Neon Blue)
    g2.setColor(new Color(0, 153, 255));
    for (Point p : currentCurvePoints) {
      int x = (int) p.getX();
      int y = (int) p.getY();
      g2.fillOval(x - 3, y - 3, 6, 6);
    }

    // 4. Draw Original Control Points (Bright Red)
    g2.setColor(new Color(255, 51, 51));
    g2.setStroke(new java.awt.BasicStroke(1.5f));
    for (Point p : controlPoints) {
      int x = (int) p.getX();
      int y = (int) p.getY();
      g2.drawOval(x - POINT_RADIUS, y - POINT_RADIUS, POINT_RADIUS * 2, POINT_RADIUS * 2);
      g2.fillOval(x - 2, y - 2, 4, 4);
    }
  }
}
