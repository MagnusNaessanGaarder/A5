package edu.ntnu.idi.idatt.model;

public enum Suit {
  HEARTS {
    @Override
    public boolean isRed() {
      return true;
    }
    @Override
    public char getSuitSymbol() {
        return '♥';
    }
    @Override
    public char getSuitChar() {
        return 'H';
    }
    @Override
    public String getSuitName() {
        return "Hearts";
    }
  },
  DIAMONDS {
      @Override
      public boolean isRed() {
      return true;
      }
      @Override
      public char getSuitSymbol() {
          return '♦';
      }
      @Override
      public char getSuitChar() {
          return 'D';
      }
      @Override
      public String getSuitName() {
          return "Diamonds";
      }
  },
  CLUBS {
      @Override
      public boolean isRed() {
      return false;
      }
      @Override
      public char getSuitSymbol() {
          return '♣';
      }
      @Override
      public char getSuitChar() {
          return 'C';
      }
      @Override
      public String getSuitName() {
          return "Clubs";
      }
  },
  SPADES {
      @Override
      public boolean isRed() {
      return false;
      }
      @Override
      public char getSuitSymbol() {
          return '♠';
      }
      @Override
      public char getSuitChar() {
          return 'S';
      }
      @Override
      public String getSuitName() {
          return "Spades";
      }
  };

  public abstract boolean isRed();
  public abstract char getSuitSymbol();
  public abstract char getSuitChar();
  public abstract String getSuitName();

  @Override
  public String toString() {
      return getSuitName();
  }
}
