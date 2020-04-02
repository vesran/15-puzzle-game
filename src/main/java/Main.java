import model.Board;
import solver.BFS;
import solver.Solver;

public class Main {

    public static void main(String [] args) {
        Board b = new Board(3, 3);
        Solver s = new BFS();
        s.solve(b);
//        System.out.println(b.getPieces());
//        System.out.println(b.moveablePieces());
//        b.moveablePieces();
    }
}
