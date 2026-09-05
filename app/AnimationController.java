package app;

import algorithm.Chaikin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import model.Point;
import ui.Canvas;

public class AnimationController {

  private final Chaikin algorithm;
  private final Canvas canvas;

  private List<Point> originalPoints;
  private List<Point> currentPoints;

  private int currentStep;
  private AppState state;

  private Timer timer;

  public AnimationController(Chaikin algorithm, Canvas canvas) {
    this.algorithm = algorithm;
    this.canvas = canvas;

    this.state = AppState.DRAWING;
    this.currentStep = 0;
  }

  public void startAnimation(List<Point> points) {

    if (state == AppState.ANIMATING || points == null || points.size() < 3) {
      return;
    }

    originalPoints = new ArrayList<>(points);
    currentPoints = new ArrayList<>(points);

    currentStep = 0;
    state = AppState.ANIMATING;

    canvas.setAcceptingPoints(false);
    canvas.setChaikinPoints(currentPoints);

    timer = new Timer(500, e -> nextStep());
    timer.start();
  }

  private void nextStep() {

    if (currentStep == 7) {
      currentPoints = new ArrayList<>(originalPoints);
      currentStep = 0;

      canvas.setChaikinPoints(currentPoints);
      return;
    }

    currentPoints = algorithm.nextPoints(currentPoints);
    currentStep++;

    canvas.setChaikinPoints(currentPoints);
  }

  public void clear() {
    if (timer != null) {
      timer.stop();
    }

    originalPoints = new ArrayList<>();
    currentPoints = new ArrayList<>();

    currentStep = 0;
    state = AppState.DRAWING;

    canvas.clear();
  }
}
