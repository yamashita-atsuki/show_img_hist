package ex3b;

public class Move{
    int removal;
    int color;

    public Move(int removal, int color){  //値を設定
        this.removal = removal;
        this.color = color;
    }

    public String toString(){
        char player = State.colorToChar(this.color);
        return String.format("%c takes %d stone(s).", player, this.removal);
    }
}