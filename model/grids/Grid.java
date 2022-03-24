//public class Grid {
//
//  private int width;
//  private int height;
//  private Tile[][] grid;
//  private int maxNumObstacles;
//  private Position curPos;
//  private GoalTile goalTile;
//
//  public Grid(int width, int height, Position startPos) {
//    this.width  = width;
//    this.height = height;
//    curPos = startPos;
//    grid = new Tile[width][height];
//    maxNumObstacles = (int) ((width * height) / 1.3);
//    goalTile = new GoalTile(new Position(height - 1, width / 2));
//
//    buildGrid();
//  }
//
//  public int width()     { return width;  }
//  public int height()    { return height; }
//  public Tile[][] grid() { return grid;   }
//
//  /**
//   * Randomly populate the grid.
//   */
//  public void buildGrid() {
//    int numObstacles = 0;
//
//    grid[goalTile.position().row()][goalTile.position().col()] = goalTile;
//
//    for (int row = 0; row < width; row++) {
//      for (int col = 0; col < height; col++) {
//        if (numObstacles < maxNumObstacles && row !=) {
//
//        } else if () {
//
//        } else {
//          // add a space tile
//          grid[row][col] = new SpaceTile(new Position(row, col));
//        }
//      }
//    }
//
//    System.out.println("num obstacles: " + numObstacles);
//  }
//}
