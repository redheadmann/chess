package chess;

import java.util.*;

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
     * Moves a single chess piece
     *
     * @param move to implement, should know beforehand it is valid
     */
    public void movePiece(ChessMove move) {
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();

        // Check for a piece at the endPosition
        ChessPiece potentialPiece = getPiece(endPosition);
        if (potentialPiece != null) {
            potentialPiece = null; // delete the captured piece
        }

        // Move the piece
        ChessPiece movingPiece = getPiece(startPosition); // get piece which is moving
        ChessPiece.PieceType promotionPieceType = move.getPromotionPiece(); // promotion type
        if (promotionPieceType != null) { // if promoting the piece, create a new chess piece
            ChessGame.TeamColor pieceColor = movingPiece.getTeamColor();
            movingPiece = null;
            addPiece(endPosition, new ChessPiece(pieceColor, promotionPieceType));
        } else {
            addPiece(startPosition, null); // remove reference to moving piece at its starting position
            addPiece(endPosition, movingPiece);
        }

    }


    public class BoardIterator<T> implements Iterator<T> {
        private Queue<ChessPosition> placements;
        /**
         * Initialize the Iterator with a queue of chess pieces on the board
         */
        public BoardIterator(ChessGame.TeamColor teamColor) {
            placements = new LinkedList<>();
            // Check each square on the board
            for (int row = 1; row <= 8; row++) {
                for (int col = 1; col <= 8; col++) {
                    ChessPosition position = new ChessPosition(row, col);
                    ChessPiece chessPiece = getPiece(position);
                    if (chessPiece != null) { // If there is a piece of the correct color
                        if (chessPiece.getTeamColor() == teamColor) {
                            placements.add(position); // add the position to the queue
                        }
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !placements.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Chess Board Iterator has no more pieces");
            }
            return (T) placements.remove();
        }
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
