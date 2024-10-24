package service;

import dataaccess.GameDAO;
import model.GameData;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    public record ReducedGameData(int gameID, String whiteUsername, String blackUsername, String gameName) {}
    public record ListResult(List<ReducedGameData> games, String message) {}

    public ListResult list(GameDAO gameDAO) {
        // 1. list all games
        List<GameData> games = gameDAO.listGames();
        // 2. convert list to ListResult
        List<ReducedGameData> resultList = new ArrayList<>();
        for (GameData game: games) {
            ReducedGameData reducedGame = new ReducedGameData(game.gameID(), game.whiteUsername(),
                    game.blackUsername(), game.gameName());
            resultList.add(reducedGame);
        }
        // 3. return list
        return new ListResult(resultList, null);
    }

    public record CreateRequest(String gameName) {}
    public record CreateResult(Integer gameID, String message) {}

    public CreateResult createGame(CreateRequest request, GameDAO gameDAO) {
        String gameName = request.gameName();
        gameDAO.createGame(gameName);

        return new CreateResult(1);
    }

}
