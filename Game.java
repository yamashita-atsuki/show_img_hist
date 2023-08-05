package ex3b;

import static ex3b.State.*;

import java.util.*;

public class Game{
    public static void main(String[] args){
        var p0 = new MinMaxPlayer(new Eval(), 20);  //MinMaxPlayerの呼び出し  //深さ制限20
        var p1 = new RandomPlayer();     //RandomPlayerの呼び出し
        Game g = new Game(p1, p0);   //Gameの呼び出し
        g.play();
        g.printResult();
    }

    State state;
    Map<Integer, Player> players;

    public Game(Player black, Player white){  //値を設定
        this.state = new State(10);  //最初の石の数を設定
        black.color = BLACK;
        white.color = WHITE;
        this.players = Map.of(BLACK, black, WHITE, white, NONE, new Player("draw"));
    }

    void play(){
        while(true){
            System.out.println(this.state);  //石の数を表示
            var player = this.players.get(this.state.color);  //stateのcolorをリストから取り出す
            var move = player.think(this.state.clone());  //Player.javaのthinkで返された値をmoveに入れる
            System.out.println(move);
            this.state = this.state.perform(move);  //performで返された値でstateを更新
            System.out.println("--------------------");
            if(this.state.isGoal()) break;  //ゴール判定を満たすとbreak
        }
    }

    void printResult() {  //石の数と勝者を表示
        System.out.println(this.state);
        System.out.println("winner: " + this.players.get(this.state.winner()));
    }
}