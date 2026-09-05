package app;

import algorithm.ChaikinAlgorithm;
import model.Point;

import java.util.List;

public class AnimationController {

    private final ChaikinAlgorithm algorithm;

    private List<Point> originalPoints;
    private List<Point> currentPoints;

    private int currentStep;

    private AppState state;

    public AnimationController(ChaikinAlgorithm algorithm) {

        this.algorithm = algorithm;
        this.state = AppState.DRAWING;
        this.currentStep = 0;
    }
}