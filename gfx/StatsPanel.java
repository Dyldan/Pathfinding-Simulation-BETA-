package gfx;

import controller.Listener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Panel for the statistics of the algorithm.
 */
public class StatsPanel extends JPanel {

  private static StatsPanel statsPanel;

  private JLabel titleLabel;
  private JLabel numMovesLabel;
  private JButton controlButton;
  private JButton resetButton;
  private JPanel labelPanel;
  private JPanel buttonPanel;

  private StatsPanel() {
    // initialize instance variables
    titleLabel = new JLabel("Logistics");
    numMovesLabel = new JLabel("Moves: 0");
    controlButton = new JButton("Start");
    resetButton = new JButton("Reset");
    labelPanel = new JPanel();
    buttonPanel = new JPanel();

    // setup
    setComponents();
    setListener();
  }

  /**
   * Update the display with how many moves have occurred.
   */
  public void updateStats(int numMoves) {
    numMovesLabel.setText("Moves: " + numMoves);
  }

  /**
   * Changes the text of the control button.
   *
   * @param text The text to change to
   */
  public void setControlButton(String text) {
    controlButton.setText(text);
  }

  public void resetControlButton() {
    controlButton.setText("Start");
  }

  /**
   * Set the components.
   */
  private void setComponents() {
    setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
    setLayout(new BorderLayout());

    labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
    labelPanel.add(titleLabel);
    labelPanel.add(numMovesLabel);

    buttonPanel.add(controlButton);
    buttonPanel.add(resetButton);

    add(labelPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Set listeners.
   */
  private void setListener() {
    Listener listener = Listener.getInstance();

    controlButton.addActionListener(listener);
    resetButton.addActionListener(listener);
  }

  /**
   * Singleton.
   */
  public static StatsPanel getInstance() {
    if (statsPanel == null) {
      statsPanel = new StatsPanel();
    }

    return statsPanel;
  }
}
