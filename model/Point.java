package model;

public class Point {
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;

    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Point other = (Point) obj;
    return Double.compare(other.x, x) == 0 && Double.compare(other.y, y) == 0;
  }
}
