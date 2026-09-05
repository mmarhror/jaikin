package app;

import algorithm.Chaikin;
import ui.Canvas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import model.Point;

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

        if (points == null || points.size() < 3) {
            return;
        }

        originalPoints = new ArrayList<>(points);
        currentPoints = new ArrayList<>(points);

        currentStep = 0;
        state = AppState.ANIMATING;

        timer = new Timer(1000, e -> nextStep());

        timer.start();
    }

    private void nextStep() {

        currentPoints = algorithm.nextPoints(currentPoints);

        currentStep++;

        canvas.setChaikinPoints(currentPoints);

        if (currentStep == 7) {
            timer.stop();

            currentPoints = new ArrayList<>(originalPoints);
            currentStep = 0;
            state = AppState.DRAWING;
        }
    }
}