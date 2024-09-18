package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        ChessPiece king = board.getPiece(myPosition);
        ChessGame.TeamColor teamColor = king.getTeamColor();

        /* We go out in one direction at a time and stop at the edge of a board
          or when we encounter another piece

         Go left, up-left, up, up-right, right, down-right, down, then down-left
        */

        // go left
        int possibleCol = col - 1;
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
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        // up-left
        int possibleRow = row + 1;
        possibleCol = col - 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                //we can't capture behind an enemy piece
                if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        // go up
        possibleRow = row + 1;
        endPosition = new ChessPosition(possibleRow, col);
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
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        // upper right direction
        possibleRow = row + 1;
        possibleCol = col + 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                //we can't capture behind an enemy piece
                if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        // go right
        possibleCol = col + 1;
        endPosition = new ChessPosition(row, possibleCol);

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
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        // lower right direction
        possibleRow = row - 1;
        possibleCol = col + 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                //we can't capture behind an enemy piece
                if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        // go down
        possibleRow = row - 1;
        endPosition = new ChessPosition(possibleRow, col);

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
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        // lower left direction
        possibleRow = row - 1;
        possibleCol = col - 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow > 0 && possibleRow <= 8 && possibleCol > 0 && possibleCol <= 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                //we can't capture behind an enemy piece
                if (pieceOnPosition.getTeamColor() != teamColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, null));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, null));
            }
        }


        return moves;
    }


}
