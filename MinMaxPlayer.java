package ex3b;

import static java.lang.Float.*;
import java.util.*;

public class MinMaxPlayer extends Player{
    Eval eval;  //評価値
    int depthLimit;  //深さ制限
    Move move;  

    public MinMaxPlayer(Eval eval, int depthLimit){  //値を設定
        super ("MinMax" + depthLimit);  
        this.eval = eval;
        this.depthLimit = depthLimit;
    }

    Move search(State state){
        maxSearch(state, 0);  //maxSearchを呼び出す
        return this.move;
    }

    float maxSearch(State state, int depth){
        if(isTerminal(state, depth)) return this.eval.value(state); //isTerminalがtrueを返したら、valueに今いるノードの評価値を入れる

        List<Move> moves = state.getMoves(); //State.javaのgetMovesで返された値をmovesにいれる
        float v = NEGATIVE_INFINITY; //v = -∞

        for(Move move : moves){  //movesの中を走査
            State next = state.perform(move);  //State.javaのperformで返された値をnextに入れる
            float v0 = minSearch(next, depth + 1); //一つ下の深さのノードの評価値をv0にいれる
            v = Math.max(v, v0);  //vとv0の大きい方をvに入れる  //同じ親をもつノードのなかで最大の評価値を決定
            if(depth == 0 && v == v0) this.move = move;  //深さが0かつvとv0が等しいときmoveを更新
        }

        return v;
    }

    float minSearch(State state, int depth){
        if(isTerminal(state, depth)) return this.eval.value(state);  //isTerminalがtrueを返したら、v0に今いるノードの評価値を入れる

        List<Move> moves = state.getMoves();  //State.javaのgetMovesで返された値をmovesにいれる
        float v = POSITIVE_INFINITY;  //v = ∞

        for(Move move : moves){  //movesの中を走査
            State next = state.perform(move);  //State.javaのperformで返された値をnextに入れる
            float v0 = maxSearch(next, depth + 1);  //それぞれのノードの評価値をv0にいれる
            v = Math.min(v, v0);  //vとv0の小さい方をvに入れる  //同じ親をもつノードのなかで最小の評価値を決定
            if(depth == 0 && v == v0) this.move = move;  //深さが0かつvとv0が等しいときmoveを更新
        }

        return v;
    }

    boolean isTerminal(State state, int depth){  //ゴール判定がtrue、もしくは深さが深さ制限以上になった場合trueを返す
        return state.isGoal() || depth >= this.depthLimit;
    }
}