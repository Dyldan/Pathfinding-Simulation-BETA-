package gfx;

import model.GoalTile;
import model.Position;
import model.SpaceTile;
import model.Tile;
import model.grids.Grid_Default1;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Panel for the grid of the GUI.
 */
public class GridPanel extends JPanel {

  private static GridPanel gridPanel;

  private Grid_Default1 gridDefault;    // TODO change to allow any grid
  private Panel[][] guiGrid;            // grid of Panel objects
  private Position prevPos;             // previous position of the pathfinder
  private Position goalPos;             // position of the goal (used for resetting)

  private GridPanel() {
    // initialize instance variables
    gridDefault = new Grid_Default1();
    guiGrid = new Panel[9][9];              // TODO change to take dimensions of spec grid
    prevPos = gridDefault.startPos();

    // setup
    setComponents();
    createGrid();
  }

  /**
   * Update the grid with the new current Position.
   */
  public void updateGrid(Position curPos) {
//    this.setBackground(Color.WHITE);

    // clear previous position on the GUI
//    guiGrid[prevPos.row()][prevPos.col()].setBackground(Color.WHITE);

    // draw the new position on the GUI
    guiGrid[curPos.row()][curPos.col()].setBackground(Color.GREEN);

    // set the previous position as the current position
    prevPos = new Position(curPos.row(), curPos.col());
  }

  public void reset() {
    updateGrid(gridDefault.startPos());
    guiGrid[goalPos.row()][goalPos.col()].setBackground(Color.BLUE);
  }

  public void displayFinished(int numMoves) {
    JOptionPane.showMessageDialog(null, "Finished! Total moves: " + numMoves);

  }

  /**
   * Create the GUI grid as a grid of Panel objects.
   */
  private void createGrid() {
    Tile[][] grid = gridDefault.grid();

    for (int row = 0; row < guiGrid.length; row++) {
      for (int col = 0; col < guiGrid[0].length; col++) {
        Panel pan = new Panel();
        if (grid[row][col] instanceof GoalTile) {
          // add the GoalTile to the GUI
          pan.setBackground(Color.BLUE);
          goalPos = new Position(row, col);
        } else if (grid[row][col] instanceof SpaceTile) {
          // add a SpaceTile to the GUI
          pan.setBackground(Color.WHITE);
        } else {
          // add an ObstacleTile to the GUI
          pan.setBackground(Color.GRAY);
        }
        guiGrid[row][col] = pan;
      }
    }

    for (int row = 0; row < guiGrid.length; row++) {
      for (int col = 0; col < guiGrid[0].length; col++) {
        add(guiGrid[row][col]);
      }
    }

    updateGrid(gridDefault.startPos()); // draw the current position
  }

  /**
   * Set the components.
   */
  private void setComponents() {
    setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
    setLayout(new GridLayout(9, 9));
  }

  /**
   * Singleton.
   */
  public static GridPanel getInstance() {
    if (gridPanel == null) {
      gridPanel = new GridPanel();
    }

    return gridPanel;
  }
}
