package model;

public abstract class Tile {

  private final Position pos;

  public Tile(Position pos) {
    this.pos = pos;
  }

  public Position position() {
    return pos;
  }
}
