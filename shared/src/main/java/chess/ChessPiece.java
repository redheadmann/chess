package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type; //might need to be mutable

    public ChessPiece(ChessGame.TeamColor pieceColor, PieceType type) {
        this.pieceColor = pieceColor; //should be one of the enum ChessGame.TeamColor
        this.type = type;
    }


    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    @Override
    public String toString() {
        HashMap<PieceType, String> map = new HashMap<PieceType, String>();
        map.put(PieceType.KING, "K");
        map.put(PieceType.QUEEN, "Q");
        map.put(PieceType.ROOK, "R");
        map.put(PieceType.KNIGHT, "N");
        map.put(PieceType.BISHOP, "B");
        map.put(PieceType.PAWN, "P");

        ChessGame.TeamColor color = this.getTeamColor();
        PieceType type = this.getPieceType();

        if (color == ChessGame.TeamColor.BLACK) {
            return map.get(type).toLowerCase();
        } else {
            return map.get(type);
        }


    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        PieceType type = this.type;
        PieceMovesCalculator movesCalculator = switch (type) {
            case KING -> new KingMovesCalculator();
            case QUEEN -> new QueenMovesCalculator();
            case BISHOP -> new BishopMovesCalculator();
            case KNIGHT -> new KnightMovesCalculator();
            case ROOK -> new RookMovesCalculator();
            case PAWN -> new PawnMoveCalculator();
        };

        return movesCalculator.pieceMoves(board, myPosition);
    }


    private Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        /**
        *  We go out in one direction at a time and observe whether
        *  we encounter another piece
        *  <p>
        *  Go left, up-left, up, up-right, right, down-right, down, and down-left
        */
        // go left
        int possibleRow = row;
        int possibleCol = col - 1;
        ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        // go up-left
        possibleRow = row + 1;
        possibleCol = col - 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        // go up
        possibleRow = row + 1;
        possibleCol = col;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        // go up-right
        possibleRow = row + 1;
        possibleCol = col + 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        // go right
        possibleRow = row;
        possibleCol = col + 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        // go down-right
        possibleRow = row - 1;
        possibleCol = col + 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }


        // go down
        possibleRow = row - 1;
        possibleCol = col;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        // go down-left
        possibleRow = row - 1;
        possibleCol = col - 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        return moves;
    }


    private Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        /*=
           We go out in one direction at a time and observe whether
           we encounter another piece
           <p>
           Go left, up-left, up, up-right, right, down-right, down, and down-left
         */
        // go left
        int possibleRow = row;
        int possibleCol = col - 1;
        ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            }
            else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        // go down-left
        possibleRow = row - 1;
        possibleCol = col - 1;
        endPosition = new ChessPosition(possibleRow, possibleCol);
        // if endPosition is still on the board
        if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
            // check for a piece already at the end position
            ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
            if (pieceOnPosition != null) {
                // check if piece on end position is of the same color
                if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
            }
        }

        return moves;
    }

}
