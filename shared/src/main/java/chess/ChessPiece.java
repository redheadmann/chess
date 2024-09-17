package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type; //might need to be mutable

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
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

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return new ArrayList<>();
    }

    private Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        /** We go out in one direction at a time and stop at the edge of a board
         * or when we encounter another piece
         *
         * Go up-left, then up-right, then down-right, then down-left
         */
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row + i;
            int possibleCol = col - i;
            ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
            // if endPosition is still on the board
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;

                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
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
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
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
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
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
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;

                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                break; //we have left the board
            }
        }

        return moves;
    }


    private Collection<ChessMove> rookMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        /** We go out in one direction at a time and stop at the edge of a board
         * or when we encounter another piece
         *
         * Go left, then up, then right, then down
         */
        // go left
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleCol = col - i;
            ChessPosition endPosition = new ChessPosition(row, possibleCol);

            // if endPosition is still on the board
            if (row >= 0 && row < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(row, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
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
            if (possibleRow >= 0 && possibleRow < 8 && col >= 0 && col < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, col));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }

            } else {
                break; //we have left the board
            }
        }
        // go right
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleCol = col + 1;
            ChessPosition endPosition = new ChessPosition(row, possibleCol);

            // if endPosition is still on the board
            if (row >= 0 && row < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(row, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }

            } else {
                break; //we have left the board
            }
        }
        // go down
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row - i;
            ChessPosition endPosition = new ChessPosition(possibleRow, col);

            // if endPosition is still on the board
            if (possibleRow >= 0 && possibleRow < 8 && col >= 0 && col < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, col));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;

                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }

            } else {
                break; //we have left the board
            }
        }

        return moves;
    }

    private Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        /** We go out in one direction at a time and stop at the edge of a board
         * or when we encounter another piece
         *
         * Go left, up-left, up, up-right, right, down-right, down, and down-left
         */
        // go left
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleCol = col - i;
            ChessPosition endPosition = new ChessPosition(row, possibleCol);

            // if endPosition is still on the board
            if (row >= 0 && row < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(row, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            }

            else {
                break; //we have left the board
            }
        }
        // go up-left
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row + i;
            int possibleCol = col - i;
            ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
            // if endPosition is still on the board
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;

                }
                else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
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
            if (possibleRow >= 0 && possibleRow < 8 && col >= 0 && col < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, col));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }

            } else {
                break; //we have left the board
            }
        }
        // upper right direction
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row + i;
            int possibleCol = col + i;
            ChessPosition endPosition = new ChessPosition(possibleRow, possibleCol);
            // if endPosition is still on the board
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                break; //we have left the board
            }
        }
        // go right
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleCol = col + 1;
            ChessPosition endPosition = new ChessPosition(row, possibleCol);

            // if endPosition is still on the board
            if (row >= 0 && row < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(row, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
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
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                break; //we have left the board
            }
        }
        // go down
        for (int i = 1; i < 8; i++) { // iterate at most 7 times
            int possibleRow = row - i;
            ChessPosition endPosition = new ChessPosition(possibleRow, col);

            // if endPosition is still on the board
            if (possibleRow >= 0 && possibleRow < 8 && col >= 0 && col < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, col));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;

                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
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
            if (possibleRow >= 0 && possibleRow < 8 && possibleCol >= 0 && possibleCol < 8) {
                // check for a piece already at the end position
                ChessPiece pieceOnPosition = board.getPiece(new ChessPosition(possibleRow, possibleCol));
                if (pieceOnPosition != null) {
                    // check if piece on end position is of the same color
                    //we can't capture behind an enemy piece
                    if (pieceOnPosition.pieceColor != this.pieceColor) { // the piece is on the opposing team and we can capture it
                        moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                    }
                    break;

                } else {
                    moves.add(new ChessMove(myPosition, endPosition, PieceType.BISHOP));
                }
            } else {
                break; //we have left the board
            }
        }

        return moves;
    }
}
