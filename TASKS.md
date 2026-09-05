# Chaikin's Algorithm Project — Team Split (Java)

## 👤 Med — Chaikin Algorithm + Unit Tests

Responsible for the mathematical and algorithmic part of the project.

### Tasks

- Create the `Point` class.
- Implement one iteration of Chaikin's algorithm.
- Generate the new points according to the algorithm.
- Handle edge cases:
  - `0` points
  - `1` point
  - `2` points
- Return a new list of points without modifying the original.
- Implement unit tests.

### Suggested files

```text
src/
├── model/
│   └── Point.java
│
├── algorithm/
│   └── ChaikinAlgorithm.java
```

### Main interface

```java
List<Point> nextStep(List<Point> points);
```

### Unit tests

```text
test/
└── ChaikinAlgorithmTest.java
```

Tests should verify:

- Correct coordinates after one iteration.
- Correct number of generated points.
- Empty list.
- One point.
- Two points.

---

# 👤 Hamada — Canvas + Drawing + Mouse Input

Responsible for the graphical interface and user interaction.

### Tasks

- Create the application window.
- Create the canvas/panel.
- Detect left mouse clicks.
- Add control points.
- Draw points as small circles.
- Draw lines between the points.
- Draw the current Chaikin curve.
- Handle the Escape key to close the application.
- Optionally implement:
  - Clearing the screen.
  - Dragging control points.

### Suggested files

```text
src/
├── ui/
│   ├── Canvas.java
│   ├── Renderer.java
│   └── InputHandler.java
```

### Responsibility flow

```text
Mouse Input
    ↓
List<Point>
    ↓
Canvas Rendering
```

Hamada should not implement the Chaikin algorithm.

---

# 👤 Yassin — Animation + Application State

Responsible for controlling the animation and connecting the algorithm with the GUI.

### Tasks

- Detect the Enter key.
- Start the animation only when points exist.
- Control animation speed.
- Track the current iteration.
- Call the Chaikin algorithm for each step.
- Update the canvas after every step.
- Stop after the 7th iteration.
- Restart the animation after completion.
- Handle special cases:
  - `0` points → Enter does nothing.
  - `1` point → Display the point without animation.
  - `2` points → Display a straight line.

### Suggested files

```text
src/
├── app/
│   ├── AnimationController.java
│   ├── AppState.java
│   └── ChaikinApp.java
```

### Possible application state

```java
enum AppState {
    DRAWING,
    ANIMATING
}
```

### Animation flow

```text
Original Points
      ↓
    Step 1
      ↓
    Step 2
      ↓
    Step 3
      ↓
      ...
      ↓
    Step 7
      ↓
    Restart
```

---

# 📁 Recommended Project Structure

```text
src/
├── Main.java
│
├── model/
│   └── Point.java
│
├── algorithm/
│   └── ChaikinAlgorithm.java
│
├── ui/
│   ├── Canvas.java
│   ├── Renderer.java
│   └── InputHandler.java
│
└── app/
    ├── AnimationController.java
    └── AppState.java
```

---

# 🔗 How the Three Parts Connect

```text
              HAMADA
          Canvas / Mouse Input
                  │
                  ▼
           List<Point>
                  │
                  ▼
              YASSIN
        Animation Controller
                  │
                  ▼
                MED
         Chaikin Algorithm
                  │
                  ▼
          New List<Point>
                  │
                  ▼
              HAMADA
             Rendering
```

---

# ⚠️ Important Before Starting

Before splitting the work, the whole team should agree on:

## The `Point` class

```text
Point
├── x
└── y
```

For example, decide:

- Should `Point` be mutable or immutable?
- Should coordinates use `int`, `float`, or `double`?
- Which package will contain `Point`?

## The algorithm interface

Everyone should agree that the algorithm exposes something similar to:

```java
List<Point> nextStep(List<Point> points);
```

This allows the three parts to work independently.

---

# ✅ Recommended Final Split

| Team Member | Responsibility |
|---|---|
| **Med** | Chaikin algorithm + `Point` model + unit tests |
| **Hamada** | Canvas + rendering + mouse input |
| **Yassin** | Animation + application state + connecting everything |

This is the cleanest split because each person has a mostly isolated task and there should be fewer merge conflicts.
