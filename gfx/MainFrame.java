package gfx;

import controller.Listener;

import javax.swing.*;
import java.awt.*;

/**
 * The main window that everything will be rendered to.
 */
public class MainFrame extends JFrame {

  private static MainFrame mainFrame;

  private JPanel gridPanel;
  private JPanel statsPanel;

  private MainFrame() {
    // initialize instance variables
    gridPanel = GridPanel.getInstance();
    statsPanel = StatsPanel.getInstance();

    // setup
    setListener();
    setComponents();          // TODO change later

    // set window logistics
    setSize(600, 450);
    setTitle("Random PathFinder");
    setVisible(true);
    centerForm();
  }

  /**
   * Set listeners.
   */
  private void setListener() {
    Listener listener = Listener.getInstance();

    this.addWindowListener(listener);
  }

  /**
   * Set the panel to display.
   */
  private void setComponents() {
    add(gridPanel, BorderLayout.CENTER);
    gridPanel.setVisible(true);

    add(statsPanel, BorderLayout.EAST);
    statsPanel.setVisible(true);
  }

  /**
   * Center form on screen.
   */
  private void centerForm() {

    Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimFrameSize = getSize();

    if ( dimFrameSize.height > dimScreenSize.height ) {
      dimFrameSize.height = dimScreenSize.height;
    }
    if ( dimFrameSize.width > dimScreenSize.width ) {
      dimFrameSize.width = dimScreenSize.width;
    }

    setLocation( ( dimScreenSize.width - dimFrameSize.width ) / 2,
      ( dimScreenSize.height - dimFrameSize.height ) / 2 );

  }

  /**
   * Singleton.
   */
  public static MainFrame getInstance() {
    if (mainFrame == null) {
      mainFrame = new MainFrame();
    }

    return mainFrame;
  }
}
