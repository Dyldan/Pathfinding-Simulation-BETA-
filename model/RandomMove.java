package model;

public enum RandomMove {
  UP, DOWN, LEFT, RIGHT;

  public static RandomMove getMove(int num) {
    switch (num) {
      case 0:
        return UP;
      case 1:
        return DOWN;
      case 2:
        return LEFT;
      case 3:
        return RIGHT;
      default:
        throw new IllegalStateException();
    }
  }
}
