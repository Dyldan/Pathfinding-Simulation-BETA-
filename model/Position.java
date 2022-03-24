package model;

/**
 * Represents a specific position in a grid.
 */
public class Position {

  private int row;
  private int col;

  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int row() { return row; }
  public int col() { return col; }

  public void setPos(int row, int col) { this.row = row; this.col = col; }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Position) {
      Position otherPos = (Position) other;
      return this.row == otherPos.row() && this.col == otherPos.col();
    }

    return false;
  }
}
