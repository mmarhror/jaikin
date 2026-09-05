package algorithm;

import java.util.ArrayList;
import java.util.List;
import model.Point;

public class Chaikin {
  public List<Point> nextPoints(List<Point> points) {
    if (points == null) {
      return new ArrayList<>();
    }

    if (points.size() < 3) {
      return new ArrayList<>(points);
    }

    List<Point> newPoints = new ArrayList<>();

    for (int i = 1; i < points.size(); i++) {
      Point p1 = points.get(i - 1);
      Point p2 = points.get(i);

      double dx = p2.getX() - p1.getX();
      double dy = p2.getY() - p1.getY();

      double lx = p1.getX() + dx * 0.25;
      double ly = p1.getY() + dy * 0.25;

      double rx = p1.getX() + dx * 0.75;
      double ry = p1.getY() + dy * 0.75;

      newPoints.add(new Point(lx, ly));
      newPoints.add(new Point(rx, ry));
    }

    return newPoints;
  }
}
