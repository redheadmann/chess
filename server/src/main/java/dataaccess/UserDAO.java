package dataaccess;

import model.UserData;

import java.util.LinkedList;

public class UserDAO {

    // while we have no actual database, use linked lists in memory
    private LinkedList<UserData> data = new LinkedList<>();

}
