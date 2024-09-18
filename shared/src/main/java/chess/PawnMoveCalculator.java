package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();

        int currentRow = myPosition.getRow();
        int currentCol = myPosition.getColumn();
        ChessPiece pawn = board.getPiece(myPosition);
        ChessGame.TeamColor teamColor = pawn.getTeamColor();

        // use this multiplier to move forward/backward in the following code
        int direction = switch (teamColor) {
            case WHITE -> 1;
            case BLACK -> -1;
        };

        /*
            These next movements are all in one column. We must be sure no piece is in the way
         */
        int nextRow = currentRow + direction;
        ChessPosition nextPosition = new ChessPosition(nextRow, currentCol);

        if (board.getPiece(nextPosition) == null) { // next space must be empty

            // check if we can move forward, but not to final row
            if (nextRow > 1 && nextRow < 8) { // pawn stays in middle of the board
                moves.add(new ChessMove(myPosition, nextPosition, null));
            } else if (nextRow == 1 || nextRow == 8) { //we are near the final row
                // we need a separate move for every possible promotion piece
                for (ChessPiece.PieceType promotionPiece : ChessPiece.PieceType.values()) {
                    if (promotionPiece != ChessPiece.PieceType.PAWN && promotionPiece != ChessPiece.PieceType.KING) { // cannot promote to pawn
                        moves.add(new ChessMove(myPosition, nextPosition, promotionPiece));
                    }
                }
            }


            // we can move twice if we are in starting position and space 2 ahead is free
            int nextnextRow = nextRow + direction;
            if (nextnextRow <= 8 && nextnextRow >= 1) {
                ChessPosition nextnextPosition = new ChessPosition(nextnextRow, currentCol);
                if (board.getPiece(nextnextPosition) == null) {
                    if (teamColor == ChessGame.TeamColor.WHITE && currentRow == 2) {
                        moves.add(new ChessMove(myPosition, nextnextPosition, null));
                    } else if (teamColor == ChessGame.TeamColor.BLACK && currentRow == 7) {
                        moves.add(new ChessMove(myPosition, nextnextPosition, null));
                    }
                }
            }

        }

        /*
            Check for pieces diagonal to pawn
         */
        for (int colMovement: new int[]{-1, 1}) {
            // check for piece on diagonal
            int nextCol = currentCol + colMovement;
            nextRow = currentRow + direction;

            nextPosition = new ChessPosition(nextRow, nextCol);
            if (board.getPiece(nextPosition) != null) { // if piece is diagonal to pawn
                if (board.getPiece(nextPosition).getTeamColor() != teamColor) { // and is on opposing team
                    // then you can capture it

                    // capture with promotion
                    if (nextRow == 8 || nextRow == 1) {
                        for (ChessPiece.PieceType promotionPiece : ChessPiece.PieceType.values()) {
                            if (promotionPiece != ChessPiece.PieceType.PAWN && promotionPiece != ChessPiece.PieceType.KING) { // cannot promote to pawn
                                moves.add(new ChessMove(myPosition, nextPosition, promotionPiece));
                            }
                        }
                    } else {
                        // normal capture
                        moves.add(new ChessMove(myPosition, nextPosition, null));
                    }

                }
            }
        }

        return moves;
    }
}
