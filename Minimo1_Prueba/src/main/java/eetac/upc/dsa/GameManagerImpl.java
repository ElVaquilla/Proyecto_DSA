package eetac.upc.dsa;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import eetac.upc.dsa.models.User;
import eetac.upc.dsa.models.Game;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected List<Game> Games;
    protected List<User> Users;
    protected List<Game> Juegos;
    protected List<User> Usuarios;

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private GameManagerImpl() {
        this.Games = new LinkedList<>();
        this.Users = new LinkedList<>();
        this.Juegos = new LinkedList<>();
        this.Usuarios = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null)
            instance = new GameManagerImpl();
        return instance;
    }

    public int GameSize() {
        int ret = this.Games.size();
        logger.info("Game size " + ret);

        return ret;
    }

    public int JuegosSize() {
        int ret = this.Juegos.size();
        logger.info("Se ha jugado a " + ret + "Juegos");
        return ret;
    }

    public int UsuariosSize() {
        int ret = this.Usuarios.size();
        logger.info("Han jugado " + ret + "usuarios");
        return ret;
    }


    public int UserSize() {
        int ret = this.Users.size();
        logger.info("User size " + ret);

        return ret;
    }

    public Game addGame(Game g) {
        logger.info("new Game " + g);

        this.Games.add (g);
        logger.info("new Game added");
        return g;
    }
    public Game addGame(String description, int levels) { return this.addGame(new Game(description, levels)); }

    public User addUser(User u) {
        logger.info("new User " + u);

        this.Users.add (u);
        logger.info("new User added");
        return u;
    }
    public User addUser(String user_name) { return this.addUser(new User(user_name)); }

    public void startGame(Game g, User u) {
        if(u.GetPartida() != true)
        {
            logger.info(u.GetUserName()+ " inicia " +g.GetDescription());
            u.SetLevel(1);
            u.SetPartida(true);
            u.SetPoints(50);
            this.Juegos.add(g);
            this.Usuarios.add(u);
        }
        if(u.GetPartida() == true)
        {
            logger.error("Error, el ususario ya está en una partida");
        }
    }

    public Game getGame(int id) {
        logger.info("getGame("+id+")");

        for (Game g: this.Games) {
            if (g.GetGameId() == id) {
                logger.info("getGame("+id+"): "+g);

                return g;
            }
        }

        logger.error("not found " + id);
        return null;
    }

    @Override
    public int getLevels(int id) {
        Game g = this.getGame(id);
        return g.GetLevels();
    }


    public User getUser(int id) {
        logger.info("getUser("+id+")");

        for (User u: this.Users) {
            if (u.GetUserId() == id) {
                logger.info("getUser("+id+"): "+u);

                return u;
            }
        }

        logger.error("not found " + id);
        return null;
    }

    @Override
    public int getLevel(int id) {
        User u = this.getUser(id);
        if(u != null) {
            logger.info(u.GetUserName() + " está en el nivel " + u.GetLevel());
            return u.GetLevel();
        }
        return -1;
    }

    @Override
    public int getPoints(int id) {
        User u = this.getUser(id);
        if(u != null) {
            logger.info(u.GetUserName() + " tiene " + u.GetPoints() +" puntos");
            return u.GetPoints();
        }
        return -1;
    }

    @Override
    public void endGame(int id) { User user = this.getUser(id); user.SetPartida(false); }

    public List<Game> findAllGames() {
        return this.Games;
    }

    @Override
    public List<Game> findAllGamesInUser(User user) { return this.Juegos; }

    public List<User> findAllUsers() {
        return this.Users;
    }

    @Override
    public List<User> findAllUsersInGame(Game game) { return this.Usuarios; }

    @Override
    public User nextlevelUser(int id, int points, String date) {
        User u = this.getUser(id);
        Game g = this.getGame(u.GetGameId());
        if (u!=null) {
            logger.info(" rebut!!!! ");

            if(u.GetPartida() == true && u.GetLevel() < g.GetLevels())
            {
                u.SetLevel(u.GetLevel() + 1);
                u.SetPoints(points);
            }
            if(u.GetPartida()== true && u.GetLevel() == g.GetLevels())
            {
                u.SetPoints(100);
                this.endGame(id);
            }
            u.SetDate(date);

            logger.info("User updated ");
        }
        else {
            logger.error("User not found ");
        }
        return u;
    }
}
