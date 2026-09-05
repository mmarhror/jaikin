package app;

import algorithm.Chaikin;
import java.util.List;
import model.Point;

public class AnimationController {

  private final Chaikin algorithm;

  private List<Point> originalPoints;
  private List<Point> currentPoints;

  private int currentStep;

  private AppState state;

  public AnimationController(Chaikin algorithm) {

    this.algorithm = algorithm;
    this.state = AppState.DRAWING;
    this.currentStep = 0;
  }
}
