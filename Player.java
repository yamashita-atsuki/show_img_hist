package ex3b;

public class Player{
    String name;
    int color;

    public Player(String name){  //値を設定
        this.name = name;
    }

    public String toString(){
        return this.name + (this.color > 0 ? "(black)" : "(white)");
    }

    public Move think(State state){
        Move move = search(state);  //stateによってMinMaxPlayerのsearchとRamdomPlayerのsearchとに分かれる
        move.color = this.color;
        return move;
    }

    Move search(State state) {
        return null;
    }
}