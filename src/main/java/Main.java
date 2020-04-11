import model.Board;
import model.Piece;
import solver.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void benchmark() {
        int n = 10;
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < n; i++) boards.add(new Board(3, 3));

        List<Solver> solvers = new ArrayList<>();
        solvers.add(new BFS());
        solvers.add(new DFS());
        solvers.add(new IterativeDeepening());
        solvers.add(new AStar());

        List<Double> meanIters = new ArrayList<>();
        for (Solver solver : solvers) {
            int iter = 0;
            for (Board b : boards) {
                solver.solve(b);
                iter += solver.getIterations();
            }
            meanIters.add((double)iter / n);
        }

        for (int i = 0; i < solvers.size(); i++) {
            System.out.println(String.format("%20s", solvers.get(i)) + " | average number of visited nodes : " + meanIters.get(i));
        }

    }

    public static void main(String [] args) {
//        Board b = new Board(3, 3);
//        Solver s1 = new AStar();
//        Solver s2 = new BFS();
//
//        List<Piece> list2 = s2.solve(b);
//        List<Piece> list = s1.solve(b);
//        System.out.println(list);
//        System.out.println(list.size());
//        System.out.println(list2);
//        System.out.println(list2.size());

        benchmark();

    }
}
