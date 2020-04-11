package solver;

import model.Board;
import model.Piece;

import java.util.*;

public class BFS implements Solver {

    private int iterations;

    @Override
    public int getIterations() {
        return this.iterations;
    }

    @Override
    public List<Piece> solve(Board board) {
        // State : piece to move

        List<Piece> actions = new ArrayList<>();
        HashMap<String, Board> visited = new HashMap<>();
        Queue<Board> toVisit = new ArrayDeque<>();
        Board current;

        toVisit.add(board);

        int i = 0;
        while (!toVisit.isEmpty()) {
            current = toVisit.poll();
            visited.put(current.toString(), current);

            System.out.println("Iteration i : " + ++i + " " + current.getPieces() + " " + current.getDepth());

            for (Piece nextPiece : current.moveablePieces()) {
                Board nextBoard = current.clone();
                int epX = nextBoard.getEmptyPositionX();
                int epY = nextBoard.getEmptyPositionY();
                nextBoard.move(nextBoard.pieceAt(nextPiece.getX(), nextPiece.getY()));

                if (!visited.containsKey(nextBoard.toString())) {
                    toVisit.add(nextBoard);
                    nextBoard.setDepth(current.getDepth() + 1);
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
                this.iterations = i;
                return actions;
            }

        }
        System.out.println("OUT");
        return null;
    }

    @Override
    public String toString() {
        return "BFS";
    }
}
