package solver;

import model.Board;
import model.Piece;

import java.util.List;

public interface Solver {
    List<Piece> solve(Board b);
    int getIterations();
}
