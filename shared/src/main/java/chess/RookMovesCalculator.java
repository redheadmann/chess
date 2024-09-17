package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessPiece rook = board.getPiece(myPosition);
        ChessGame.TeamColor teamColor = rook.getTeamColor();

        /* We go out in one direction at a time and stop at the edge of a board
          or when we encounter another piece

         Go left, up, right, and down
        */

        // go left
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleCol = col - i;
            ChessPosition endPosition = new ChessPosition(row, possibleCol);

            // if endPosition is still on the board
            if (row > 0 && row <= 8 && possibleCol > 0 && possibleCol <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(row, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, null));
                    }
                    break;
                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            }
            else {
                break; //we have left the board
            }
        }

        // go up
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row + i;
            ChessPosition endPosition = new ChessPosition(possibleRow, col);

            // if endPosition is still on the board
            if (possibleRow > 0 && possibleRow <= 8 && col > 0 && col <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, col));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, null));
                    }
                    break;
                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            }
            else {
                break; //we have left the board
            }
        }

        // go right
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleCol = col + i;
            ChessPosition endPosition = new ChessPosition(row, possibleCol);

            // if endPosition is still on the board
            if (row > 0 && row <= 8 && possibleCol > 0 && possibleCol <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(row, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, null));
                    }
                    break;
                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            }
            else {
                break; //we have left the board
            }
        }


        // go down
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row - i;
            ChessPosition endPosition = new ChessPosition(possibleRow, col);

            // if endPosition is still on the board
            if (possibleRow > 0 && possibleRow <= 8 && col > 0 && col <= 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, col));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, null));
                    }
                    break;
                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            }
            else {
                break; //we have left the board
            }
        }

        return moves;
    }

}

