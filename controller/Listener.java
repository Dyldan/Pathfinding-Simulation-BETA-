package controller;

import gfx.StatsPanel;
import model.PathFinder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Listener implements WindowListener, ActionListener {

  private static Listener listener;

  private PathFinder pathFinder;

  /*******************************************************************
   *                       Interface Methods                         *
   *******************************************************************/

  @Override
  public void windowClosing(WindowEvent e) {
    System.exit(0);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();

    if (button.getText().equals("Reset")) {
      resetButtonEvent();
    } else {
      controlButtonEvent(e);
    }

  }

  /*******************************************************************
   *                       Private Methods                           *
   *******************************************************************/

  private void controlButtonEvent(ActionEvent e) {
    JButton button = (JButton) e.getSource();

    switch (button.getText()) {
      case "Start":
        pathFinder = new PathFinder();
        pathFinder.start();
        StatsPanel.getInstance().setControlButton("Pause");
        break;
      case "Pause":
        pathFinder.lockThread();
        StatsPanel.getInstance().setControlButton("Resume");
        break;
      case "Resume":
        pathFinder.unlockThread();
        StatsPanel.getInstance().setControlButton("Pause");
        break;
      default:
    }
  }

  private void resetButtonEvent() {
    pathFinder.lockThread();
    pathFinder.reset();
    pathFinder = null;
    pathFinder = new PathFinder();
    StatsPanel.getInstance().setControlButton("Start");
  }

  /*******************************************************************
   *                       Singleton Method                          *
   *******************************************************************/

  public static Listener getInstance() {
    if (listener == null) {
      listener = new Listener();
    }

    return listener;
  }

  /*******************************************************************
   *                       Unused Methods                            *
   *******************************************************************/

  @Override
  public void windowClosed(WindowEvent e) { }

  @Override
  public void windowIconified(WindowEvent e) { }

  @Override
  public void windowDeiconified(WindowEvent e) { }

  @Override
  public void windowActivated(WindowEvent e) { }

  @Override
  public void windowDeactivated(WindowEvent e) { }

  @Override
  public void windowOpened(WindowEvent e) { }
}
