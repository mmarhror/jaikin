package app;

import algorithm.Chaikin;
import java.util.List;
import javax.swing.Timer;
import model.Point;
import ui.CanvasPanel;

public class AnimationController {
  private final CanvasPanel canvas;
  private final Chaikin chaikin;
  private final Timer timer;

  private int currentStep = 0;
  private List<Point> animatedPoints;
  private boolean isRunning = false;

  public AnimationController(CanvasPanel canvas, Chaikin chaikin) {
    this.canvas = canvas;
    this.chaikin = chaikin;

    // Timer fires every 500ms (0.5s per step)
    this.timer = new Timer(500, e -> stepAnimation());
  }

  public void handleEnterPress() {
    List<Point> controlPoints = canvas.getControlPoints();

    // Edge case: 0 points
    if (controlPoints.isEmpty()) {
      canvas.setStatusMessage("Please click on the canvas to draw points first!");
      return;
    }

    // Edge case: 1 point
    if (controlPoints.size() == 1) {
      canvas.setStatusMessage("Single point placed. Add more points to create a curve.");
      return;
    }

    // Edge case: 2 points (straight line, no smoothing needed)
    if (controlPoints.size() == 2) {
      canvas.setStatusMessage("2 points form a straight line.");
      return;
    }

    // Start / restart animation loop
    currentStep = 0;
    animatedPoints = controlPoints;
    isRunning = true;
    canvas.setStatusMessage("Animating Chaikin's Algorithm...");
    timer.restart();
  }

  private void stepAnimation() {
    if (!isRunning) return;

    if (currentStep < 7) {
      currentStep++;
      // Med's algorithm called here:
      animatedPoints = chaikin.nextPoints(animatedPoints);
      canvas.setCurvePoints(animatedPoints, currentStep);
    } else {
      // Reached 7th step -> Restart from original points
      currentStep = 0;
      animatedPoints = canvas.getControlPoints();
      canvas.setCurvePoints(animatedPoints, currentStep);
    }
  }

  public void stop() {
    isRunning = false;
    timer.stop();
  }
}
