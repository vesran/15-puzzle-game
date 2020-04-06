package solver;

import model.Board;
import model.Piece;

import java.util.*;

public class DFS implements Solver {

    @Override
    public List<Piece> solve(Board board) {
        // State : piece to move

        List<Piece> actions = new ArrayList<>();
        HashMap<String, Board> visited = new HashMap<>();
        Stack<Board> toVisit = new Stack<>();
        Board current;

        toVisit.add(board);

        int i = 0;
        while (!toVisit.isEmpty()) {
            current = toVisit.pop();
            visited.put(current.toString(), current);

            System.out.println("Iteration i : " + ++i + " " + current.getPieces());

            for (Piece nextPiece : current.moveablePieces()) {
                Board nextBoard = current.clone();
                int epX = nextBoard.getEmptyPositionX();
                int epY = nextBoard.getEmptyPositionY();
                nextBoard.move(nextBoard.pieceAt(nextPiece.getX(), nextPiece.getY()));

                if (!visited.containsKey(nextBoard.toString())) {
                    toVisit.push(nextBoard);
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
}
