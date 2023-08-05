package ex3b;

public class Eval {
    float value(State state){  //評価値を返す
        return state.isGoal() ? Integer.MAX_VALUE * state.winner() : state.stones;  //ゴール判定がtrueの時はwinnerの値を返し、falseの時は残りの石の数を返す
    }
}