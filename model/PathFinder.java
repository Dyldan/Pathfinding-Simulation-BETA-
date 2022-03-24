package model;

import gfx.GridPanel;
import gfx.StatsPanel;
import model.grids.Grid_Default1;

import java.util.Random;

public class PathFinder extends Thread {

  private final Grid_Default1 grid;
  private final Random random;
  private Position curPos;
  private boolean reachedGoal;
  private int numMoves;
  private GridPanel gridPanel;
  private StatsPanel statsPanel;
  private boolean threadLocked;

  public PathFinder() {
    grid = new Grid_Default1();           // the grid to pathfind in
    random = new Random();
    curPos = new Position(grid.startPos().row(), grid.startPos().col());
    reachedGoal = false;
    numMoves = 0;
    gridPanel = GridPanel.getInstance();
    statsPanel = StatsPanel.getInstance();
    threadLocked = true;
  }

  @Override
  public void run() {
    threadLocked = false;
    startAlgorithm();
  }

  /**
   * Start the algorithm.
   */
  public void startAlgorithm() {
    while (!reachedGoal) {
      if (!threadLocked) {
        moveOneTile();
      } else {
        while (threadLocked) {
          try {
            sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }

    gridPanel.displayFinished(numMoves);
    statsPanel.resetControlButton();
  }

  public void reset() {
    numMoves = 0;
    curPos = new Position(grid.startPos().row(), grid.startPos().col());
    reachedGoal = false;
    gridPanel.reset();
    statsPanel.updateStats(numMoves);
  }

  public void lockThread() { threadLocked = true; }

  public void unlockThread() { threadLocked = false; }

  private void moveOneTile() {
    int curRow = curPos.row();
    int curCol = curPos.col();

    // check if goal is an adjacent Tile
    if (grid.tile(curRow - 1, curCol) instanceof GoalTile) {
      curPos.setPos(curRow - 1, curCol);
      numMoves++;
      reachedGoal = true;
    } else if (grid.tile(curRow + 1, curCol) instanceof GoalTile) {
      curPos.setPos(curRow + 1, curCol);
      numMoves++;
      reachedGoal = true;
    } else if (grid.tile(curRow, curCol - 1) instanceof GoalTile) {
      curPos.setPos(curRow, curCol - 1);
      numMoves++;
      reachedGoal = true;
    } else if (grid.tile(curRow, curCol + 1) instanceof GoalTile) {
      curPos.setPos(curRow, curCol + 1);
      numMoves++;
      reachedGoal = true;
    } else {
      // randomly move to an adjacent tile that is not an obstacle
      boolean canMove = false;

      while (!canMove) {
        RandomMove moveDir = genRandomMove();
        switch (moveDir) {
          case UP:
            if (grid.tile(curRow - 1, curCol) instanceof SpaceTile) {
              canMove = true;
              curPos.setPos(curRow - 1, curCol);
              numMoves++;
            }
            break;
          case DOWN:
            if (grid.tile(curRow + 1, curCol) instanceof SpaceTile) {
              canMove = true;
              curPos.setPos(curRow + 1, curCol);
              numMoves++;
            }
            break;
          case LEFT:
            if (grid.tile(curRow, curCol - 1) instanceof SpaceTile) {
              canMove = true;
              curPos.setPos(curRow, curCol - 1);
              numMoves++;
            }
            break;
          case RIGHT:
            if (grid.tile(curRow, curCol + 1) instanceof SpaceTile) {
              canMove = true;
              curPos.setPos(curRow, curCol + 1);
              numMoves++;
            }
            break;
        }
      }
    }

    // update the GUI every .05 second
    try {
      Thread.sleep(50);
      gridPanel.updateGrid(curPos);
      statsPanel.updateStats(numMoves);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private RandomMove genRandomMove() {
    return RandomMove.getMove(random.nextInt(4));
  }

}
