import algorithm.Chaikin;
import app.AnimationController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import ui.CanvasPanel;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Chaikin's Algorithm Animation");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(900, 650);
          frame.setLocationRelativeTo(null);

          CanvasPanel canvas = new CanvasPanel();
          Chaikin chaikin = new Chaikin();
          AnimationController controller = new AnimationController(canvas, chaikin);

          // Key listeners for ENTER and ESC
          frame.addKeyListener(
              new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controller.handleEnterPress();
                  } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0); // Closes the window
                  } else if (e.getKeyCode() == KeyEvent.VK_C) {
                    controller.stop();
                    canvas.clear();
                  }
                }
              });

          frame.add(canvas);
          frame.setFocusable(true);
          frame.setVisible(true);
        });
  }
}
