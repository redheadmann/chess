package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getColumn();
        ChessPiece bishop = board.getPiece(position);
        ChessGame.TeamColor teamColor = bishop.getTeamColor();

        /* We go out in one direction at a time and stop at the edge of a board
          or when we encounter another piece

         Go up-left, then up-right, then down-right, then down-left
        */
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row + i;
            int possibleCol = col - i;
            ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
            // if endPosition is still on the board
            if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(position, endPosition, null));
                    }
                    break;

                }
                else {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            }
            else {
                break; //we have left the board
            }
        }
        // upper right direction
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row + i;
            int possibleCol = col + i;
            ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
            // if endPosition is still on the board
            if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(position, endPosition, null));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            } else {
                break; //we have left the board
            }
        }
        // lower right direction
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row - i;
            int possibleCol = col + i;
            ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
            // if endPosition is still on the board
            if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(position, endPosition, null));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            } else {
                break; //we have left the board
            }
        }
        // lower left direction
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row - i;
            int possibleCol = col - i;
            ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
            // if endPosition is still on the board
            if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(position, endPosition, null));
                    }
                    break;

                } else {
                    moves.add(new ChessMove(position, endPosition, null));
                }
            } else {
                break; //we have left the board
            }
        }

        return moves;
    }

}
