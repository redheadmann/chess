package service;

import chess.ChessGame;

import java.util.List;

public class GameService {
    public record ReducedGameData(int gameID, String whiteUsername, String blackUsername, String gameName) {}
    public record ListResult(List<ReducedGameData> games) {}

    public ListResult list() {

        return null;
    }
}
