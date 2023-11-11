package eetac.upc.dsa;

import eetac.upc.dsa.models.Game;
import eetac.upc.dsa.models.User;

import java.util.List;

public interface GameManager {

    // User manager
    public User addUser(String username);
    public User addUser(User user);
    public User getUser(int id);
    public int getLevel(int id);
    public void endGame(int id);
    public int getPoints(int id);
    public List<User> findAllUsers();
    public List <User> findAllUsersInGame(Game game);
    public User nextlevelUser(int id, int points, String date);

    // Team manager


    // Game manager
    public Game addGame(String description, int levels);
    public Game addGame(Game game);
    public void startGame(Game game, User user);
    public Game getGame(int id);
    public int getLevels(int id);
    public List<Game> findAllGames();
    public List<Game> findAllGamesInUser(User User);


    public int GameSize();
    public int UserSize();

}