package model.grids;

import model.*;

/**
 * Default Grid #1.
 */
public class Grid_Default1 {

  private final int ROWS = 9;
  private final int COLS = 9;
  private final Tile[][] grid;
  private final GoalTile goalTile;
  private Position startPos;

  public Grid_Default1() {
    grid = new Tile[ROWS][COLS];
    goalTile = new GoalTile(new Position(0, 4));
    startPos = new Position(8, 4);
    buildGrid();
  }

  /**
   * Grid looks like:
   * [ ] [ ] [ ] [x] [F] [ ] [ ] [ ] [ ]
   * [ ] [ ] [ ] [x] [x] [x] [x] [ ] [ ]
   * [ ] [ ] [ ] [ ] [ ] [ ] [x] [ ] [ ]
   * [ ] [ ] [x] [x] [x] [x] [x] [ ] [ ]
   * [ ] [ ] [x] [ ] [ ] [ ] [x] [ ] [ ]
   * [ ] [ ] [ ] [ ] [ ] [ ] [x] [ ] [ ]
   * [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
   * [ ] [ ] [ ] [x] [x] [x] [x] [ ] [ ]
   * [ ] [ ] [ ] [ ] [S] [ ] [ ] [ ] [ ]
   */
  public void buildGrid() {
    // space tiles
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        grid[row][col] = new SpaceTile(new Position(row, col));
      }
    }

    // obstacles tiles
    grid[0][3] = new ObstacleTile(new Position(0, 3));
    grid[1][3] = new ObstacleTile(new Position(1, 3));
    grid[1][4] = new ObstacleTile(new Position(1, 4));
    grid[1][5] = new ObstacleTile(new Position(1, 5));
    grid[1][6] = new ObstacleTile(new Position(1, 6));
    grid[2][6] = new ObstacleTile(new Position(2, 6));
    grid[3][2] = new ObstacleTile(new Position(3, 2));
    grid[3][3] = new ObstacleTile(new Position(3, 3));
    grid[3][4] = new ObstacleTile(new Position(3, 4));
    grid[3][5] = new ObstacleTile(new Position(3, 5));
    grid[3][6] = new ObstacleTile(new Position(3, 6));
    grid[4][2] = new ObstacleTile(new Position(4, 2));
    grid[4][6] = new ObstacleTile(new Position(4, 6));
    grid[5][6] = new ObstacleTile(new Position(5, 6));
    grid[7][3] = new ObstacleTile(new Position(7, 3));
    grid[7][4] = new ObstacleTile(new Position(7, 4));
    grid[7][5] = new ObstacleTile(new Position(7, 5));
    grid[7][6] = new ObstacleTile(new Position(7, 6));

    // goal tile
    grid[0][4] = goalTile;
  }

  /**
   * Get the Tile at the specified row, col coordinate. Explicit coordinates were used
   * instead of a Position for simplicity.
   */
  public Tile tile(int row, int col) {
    if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
      return null;
    }
    return grid[row][col];
  }

  /**
   * Return current Position of the pathfinder.
   */
  public Position startPos() {
    return startPos;
  }

  /**
   * Return a reference to the Tile grid itself.
   */
  public Tile[][] grid() {
    return grid;
  }
}
