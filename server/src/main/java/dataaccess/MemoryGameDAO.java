package dataaccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoryGameDAO implements  GameDAO {

    private HashMap<Integer, GameData> data = new HashMap<>();


    @Override
    public void createGame(String gameName) {
        // create a unique id

        GameData game = new GameData(null, null, null, gameName, new ChessGame());
    }

    @Override
    public GameData getGame(String gameID) {
        return null;
    }

    @Override
    public List<GameData> listGames() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void clear() {
        data.clear();
    }
}
