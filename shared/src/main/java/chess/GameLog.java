package chess;

import java.util.LinkedList;

public class GameLog {
    public record LogEntry(ChessMove move, ChessPiece piece) { }
    LinkedList<LogEntry> moves;


    public void addMove(ChessMove move, ChessPiece piece) {
        moves.add(new LogEntry(move, piece));
    }

    public LogEntry getLastMove() {
        return moves.peekLast(); // returns null if there are no moves in the log
    }

}

