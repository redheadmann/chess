package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessPiece bishop = board.getPiece(myPosition);
        ChessGame.TeamColor teamColor = bishop.getTeamColor();

        /* We go out in one direction at a time and stop at the edge of a board
          or when we encounter another piece
        */
        for (int rowDirection: new int[]{-1,1}) { // iterate at most 7 times
            for  (int colDirection: new int[]{-1,1}) {
                moveUntilBlocked(board, myPosition, rowDirection, colDirection, moves);
            }
        }

        return moves;
    }

}
