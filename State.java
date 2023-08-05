package ex3b;

import java.util.*;

public class State implements Cloneable{
    public final static int BLACK = 1;
    public final static int WHITE = -1;
    public final static int NONE = 0;

    int stones;
    int color = BLACK;
    Move lastMove;

    public State(int stones) { //石の数
        this.stones = stones;
    }

    public State clone(){  //thisの状態をotherに置き換える
        State other = new State(this.stones);
        other.color = this.color;
        other.lastMove = this.lastMove;
        return other;
    }

    public String toString(){
        return String.format("%d stone(s)", this.stones);
    }
    
    public static char colorToChar(int color){ 
        return Map.of(1, 'o', -1, 'x').getOrDefault(color, ' ');  //Map値の値がnullだったら空白
    }

    public boolean isGoal(){  //ゴール判定
        return this.stones == 0;
    }

    public List<Move> getMoves(){
        var moves = new ArrayList<Move>();
        for(int i = 1; i <= Math.min(3, this.stones); i++){  //movesにMoveの値を入れる  //colorにthis.color,removalにi
            moves.add(new Move(i, this.color));
        }
        return moves;
    }

    public State perform(Move move){
        var next = this.clone();
        next.stones -= move.removal;  //とった石の数を引く
        next.color = -move.color;  //相手のターンにする
        next.lastMove = move;
        return next;
    }

    public int winner(){  //勝利判定
        return isGoal() ? this.color : NONE;  //isGoalがtrueのときthis.colorを返し、falseのときNONEを返す
    }
}