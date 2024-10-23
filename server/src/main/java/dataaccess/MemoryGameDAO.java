package dataaccess;

import model.GameData;

import java.util.HashMap;
import java.util.LinkedList;

public class MemoryGameDAO implements  GameDAO {

    private HashMap<Integer, GameData> data = new HashMap<>();


    @Override
    public void clear() {
        data.clear();
    }
}
