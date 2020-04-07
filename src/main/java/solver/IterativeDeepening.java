package solver;

import model.Board;
import model.Piece;

import java.util.*;

public class IterativeDeepening implements Solver {

    private List<Piece> dfs(Board board, int limit) {
        // State : piece to move

        List<Piece> actions = new ArrayList<>();
        HashMap<String, Board> visited = new HashMap<>();
        Stack<Board> toVisit = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        Board current;

        toVisit.push(board);
        depths.push(1);

        int i = 0;
        while (!toVisit.isEmpty()) {
            current = toVisit.pop();
            int currentDepth = depths.pop();
            visited.put(current.toString(), current);

            System.out.println("Iteration i : " + ++i + " " + current.getPieces());

            for (Piece nextPiece : current.moveablePieces()) {
                Board nextBoard = current.clone();
                int epX = nextBoard.getEmptyPositionX();
                int epY = nextBoard.getEmptyPositionY();
                nextBoard.move(nextBoard.pieceAt(nextPiece.getX(), nextPiece.getY()));

                if (!visited.containsKey(nextBoard.toString()) && currentDepth + 1 <= limit) {
                    toVisit.push(nextBoard);
                    depths.push(currentDepth + 1);
                    nextBoard.setTriggered(nextBoard.pieceAt(epX, epY));
                }
            }

            if (current.isSolved()) {
                System.out.println("SOLVED");
                System.out.println(current);

                while (current.getTriggered() != null) {
                    actions.add(current.getTriggered());
                    current.move(current.getTriggered());
                    current = visited.get(current.toString());
                }
                Collections.reverse(actions);
                return actions;
            }

        }
        System.out.println("OUT");
        return null;
    }

    @Override
    public List<Piece> solve(Board board) {
        List<Piece> actions;
        for (int i = 1; i <= 100; i++) {
            actions = this.dfs(board, i);
            if (actions != null) {
                return actions;
            }
        }
        System.out.println("Not enough - try harder");
        return null;
    }

}
