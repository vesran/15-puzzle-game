import model.Board;
import solver.BFS;
import solver.DFS;
import solver.IterativeDeepening;
import solver.Solver;

public class Main {

    public static void main(String [] args) {
        Board b = new Board(3, 3);
        Solver s = new IterativeDeepening();
        System.out.println(s.solve(b));
//        System.out.println(b.getPieces());
//        System.out.println(b.moveablePieces());
//        b.moveablePieces();
    }
}
