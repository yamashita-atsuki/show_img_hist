package ex3b;

public class RandomPlayer extends Player{
    public RandomPlayer() {
        super("Random");
    }

    Move search(State state) {
        var moves = state.getMoves();
        int index = new java.util.Random().nextInt(moves.size());  //ランダムに数を決める
        return moves.get(index);
    }
}