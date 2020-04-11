import model.Board;
import model.Piece;
import solver.*;

import java.util.List;

public class Main {

    public static void main(String [] args) {
        Board b = new Board(3, 3);
        Solver s1 = new AStar();
        Solver s2 = new BFS();

//        System.out.println(b.getPieces().get(1));
//        System.out.println(s.manhattan(b, b.getPieces().get(1)));

        List<Piece> list2 = s2.solve(b);
        List<Piece> list = s1.solve(b);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list2);
        System.out.println(list2.size());

    }
}
