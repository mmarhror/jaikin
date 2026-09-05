# Chaikin's Algorithm Animation

A Java Swing application that visualizes Chaikin's corner-cutting algorithm step by step. Users place control points on a canvas, then watch the curve update through seven iterations.

## Requirements

- Java Development Kit 8 or newer
- A graphical desktop environment for the Swing window

## Run the program

From the project directory, compile the source files:

```bash
javac Main.java algorithm/*.java app/*.java model/*.java ui/*.java
```

Then start the application:

```bash
java Main
```

Linux commands are case-sensitive, so use `java Main` with a lowercase `java`.

## Controls

| Action | Result |
| --- | --- |
| Left-click on the canvas | Add a control point |
| Enter | Start the animation when at least three points exist |
| Escape | Close the application |
| Ctrl+C | Clear the canvas and allow new points |

Only left-clicks create points. New points are ignored while an animation is running.

## Behavior

- **No points:** Pressing Enter has no effect. Points can still be added afterward.
- **One point:** The point remains visible and no animation starts.
- **Two points:** A straight line is drawn between the points and no animation starts.
- **Three or more points:** Pressing Enter starts the Chaikin animation.
- **Animation:** The curve advances once every 500 milliseconds through seven steps, then returns to the original control points and repeats.
- **Clear:** Ctrl+C stops the animation, removes all points, and enables point placement again.

Control points are shown as small white circles. The original control polygon is dark gray, and the generated Chaikin curve is green.

## How Chaikin's algorithm works

For each pair of neighboring points, the algorithm creates two new points:

- A point 25% of the way from the first point to the second.
- A point 75% of the way from the first point to the second.

The first and last points are preserved. Repeating this process produces a smoother curve.

## Project structure

```text
.
├── Main.java
├── algorithm/
│   └── Chaikin.java
├── app/
│   ├── AnimationController.java
│   └── AppState.java
├── model/
│   └── Point.java
└── ui/
    └── Canvas.java
```

### Main components

- `Main`: Creates the Swing window and connects input to the application.
- `Canvas`: Stores control points, handles mouse input, and renders the points and curves.
- `AnimationController`: Manages animation timing, seven-step iteration, and input locking.
- `Chaikin`: Calculates the next set of points.
- `Point`: Represents an `(x, y)` coordinate.
