package dataaccess;

import model.GameData;

import java.util.List;

public interface GameDAO {
    void createGame();
    GameData getGame(String gameID);
    List<GameData> listGames();
    void clear();
}
