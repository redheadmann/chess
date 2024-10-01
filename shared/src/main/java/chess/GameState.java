package chess;

public class GameState {
    private ChessGame.TeamColor teamTurn;

    public GameState (ChessBoard gameBoard) {
        this.teamTurn = ChessGame.TeamColor.WHITE; // game starts off with white to move
    }



    /*
        Getters and Setters
     */
    public ChessGame.TeamColor teamTurn() {
        return teamTurn;
    }

    /**
     *
     * @param teamColor the team color to give the turn
     */
    public void setTeamTurn(ChessGame.TeamColor teamColor) {
        teamTurn = teamColor;
    }

    private void updateColor() {
        if (this.teamTurn == ChessGame.TeamColor.WHITE) {
            this.teamTurn = ChessGame.TeamColor.BLACK;
        } else {
            this.teamTurn = ChessGame.TeamColor.WHITE;
        }
    }

    public void updateBoard(ChessBoard nextBoard) {
        this.updateColor();
    }
}
