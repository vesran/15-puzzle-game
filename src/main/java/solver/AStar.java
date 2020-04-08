package solver;

import model.Board;
import model.Piece;

import java.util.*;
import java.util.function.Function;

public class AStar implements Solver {

    private int g(Board board) {
        return board.getDepth();
    }

    private int h(Board board) {
        int hValue = 0;
        for (Piece p : board.getPieces()) {
            hValue += this.manhattan(board, p);
        }
        return hValue;
    }

    public int manhattan(Board board, Piece piece) {
        int currentX = piece.getX();
        int currentY = piece.getY();
        int targetX = ((piece.getId() - 1) % (board.getWidth()));
        int targetY = (piece.getId() - 1) / board.getHeight();

        return Math.abs(currentX - targetX) + Math.abs(currentY - targetY);
    }

    @Override
    public List<Piece> solve(Board board) {
        List<Piece> actions = new ArrayList<>();
        HashMap<String, Board> visited = new HashMap<>();
        Queue<Board> toVisit = new PriorityQueue<>(new Comparator<Board>(){

            final Function<Board, Integer> f = (b) -> g(b) + h(b);

            @Override
            public int compare(Board b1, Board b2) {
                int diff = f.apply(b1) - f.apply(b2);
                return Integer.compare(diff, 0);
            }

        });
        Board current;

        toVisit.add(board);

        int i = 0;
        while (!toVisit.isEmpty()) {
            current = toVisit.poll();
            visited.put(current.toString(), current);

            System.out.println("Iteration i : " + ++i + " " + current.getPieces());

            for (Piece nextPiece : current.moveablePieces()) {
                Board nextBoard = current.clone();
                int epX = nextBoard.getEmptyPositionX();
                int epY = nextBoard.getEmptyPositionY();
                nextBoard.move(nextBoard.pieceAt(nextPiece.getX(), nextPiece.getY()));

                if (!visited.containsKey(nextBoard.toString())) {
                    toVisit.add(nextBoard);
                    nextBoard.setTriggered(nextBoard.pieceAt(epX, epY));
                    nextBoard.setDepth(current.getDepth() + 1);
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
