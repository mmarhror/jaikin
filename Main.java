import algorithm.Chaikin;
import app.AnimationController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import ui.Canvas;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Chaikin's Algorithm");
          Canvas canvas = new Canvas();
          Chaikin algorithm = new Chaikin();
          AnimationController controller = new AnimationController(algorithm, canvas);

          frame.add(canvas);
          frame.setSize(800, 600);
          frame.setResizable(false);
          frame.addKeyListener(
              new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                  if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                  } else if (e.getKeyCode() == KeyEvent.VK_C && e.isControlDown()) {
                    canvas.clear();
                    controller.clear();
                  } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controller.startAnimation(canvas.getPoints());
                  }
                }
              });

          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
        });
  }
}
