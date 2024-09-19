package chess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] squares = new ChessPiece[8][8];

    public ChessBoard() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()-1][position.getColumn()-1]; // we call the first row 'row 1'
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        this.squares = new ChessPiece[8][8];

        HashMap<Integer, ChessPiece.PieceType> colToPiece = new HashMap<>();
        colToPiece.put(1, ChessPiece.PieceType.ROOK);
        colToPiece.put(2, ChessPiece.PieceType.KNIGHT);
        colToPiece.put(3, ChessPiece.PieceType.BISHOP);
        colToPiece.put(4, ChessPiece.PieceType.QUEEN);
        colToPiece.put(5, ChessPiece.PieceType.KING);
        colToPiece.put(6, ChessPiece.PieceType.BISHOP);
        colToPiece.put(7, ChessPiece.PieceType.KNIGHT);
        colToPiece.put(8, ChessPiece.PieceType.ROOK);


        // put in rows of pawns
        for (int col = 1; col <= 8; col++) {
            ChessPosition whitePawn = new ChessPosition(2, col);
            this.addPiece(whitePawn, new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
            ChessPosition whiteSpecial = new ChessPosition(1, col);
            this.addPiece(whiteSpecial, new ChessPiece(ChessGame.TeamColor.WHITE, colToPiece.get(col)));


            ChessPosition blackPawn = new ChessPosition(7, col);
            this.addPiece(blackPawn, new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
            ChessPosition blackSpecial = new ChessPosition(8, col);
            this.addPiece(blackSpecial, new ChessPiece(ChessGame.TeamColor.BLACK, colToPiece.get(col)));
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                if (this.getPiece(new ChessPosition(row, col)) == null) {
                    str.append("| ");
                } else {
                    str.append("|").append(this.getPiece(new ChessPosition(row, col)).toString());
                }
            }
            str.append("|/n");
        }
        return str.toString();
    }

}
