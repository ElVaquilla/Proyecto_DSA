package eetac.upc.dsa;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import eetac.upc.dsa.models.Jugador;
import eetac.upc.dsa.models.Partida;
import eetac.upc.dsa.models.Avatar;
import eetac.upc.dsa.models.Mapa;
import eetac.upc.dsa.models.Tienda;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected List<Partida> Partidas;
    protected List<Jugador> Jugadores;
    protected List<Partida> Jugadas;
    protected List<Avatar> Avatares;
    protected List<Mapa> Mapas;
    protected List<Tienda> Productos;

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private GameManagerImpl() {
        this.Partidas = new LinkedList<>();
        this.Jugadores = new LinkedList<>();
        this.Avatares = new LinkedList<>();
        this.Mapas = new LinkedList<>();
        this.Productos = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null)
            instance = new GameManagerImpl();
        return instance;
    }

    public int PartidaSize() {      // Número de partidas creadas
        int ret = this.Partidas.size();
        logger.info("Game size " + ret);
        return ret;
    }

    public int JuegosSize() {       // Número de partidas jugadas
        int ret = this.Jugadas.size();
        logger.info("Se ha jugado a " + ret + "Juegos");
        return ret;
    }

    public int JugadoresSize() {    // Número de jugadores
        int ret = this.Jugadores.size();
        logger.info("Jugadores size " + ret);
        return ret;
    }

    public Partida addPartida(Partida p) {
        logger.info("Nueva partida " + p);

        this.Partidas.add (p);
        logger.info("Nueva partida añadida");
        return p;
    }
    public Partida addPartida(int dif, int idPlayer, int idMapa) { return this.addPartida(new Partida(dif, idPlayer, idMapa)); }

    public Jugador addJugador(Jugador j) {
        logger.info("new Jugador " + j);

        this.Jugadores.add (j);
        logger.info("new Jugador added");
        return j;
    }
    public Jugador addJugador(String username, String mail, String pasword) { return this.addJugador(new Jugador(username, mail, pasword)); }

    // Que pasa si yo ya tengo una partida guardada de antes??
    // Cómo se tendría que inciar?
    public void startPartida(Partida p, Jugador j, Mapa m, Avatar a) {        // El jugador inicicia una partida
        if(p.GetMapId() == -1){     // Un id negativo implica que la partida es nueva
            logger.info(j.GetUserName() + " inicia partida en el nivel 1");
            p.SetNivel(1);
            p.SetMapaId(m.GetId());
            j.SetPoints(50);
            this.Mapas.add(m);
            this.Jugadores.add(j);
        }
        /* else{
            logger.info(j.GetUserName() + " inicia partida en el nivel" + p.GetNivel());
            p.SetNivel(1);
            p.SetMapaId(m.GetId());
            j.SetPoints(50);
            this.Mapas.add(m);
            this.Jugadores.add(j);
        } */

    }
    /*
    public Partida getPartida(int id) {
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

    */

    public Jugador getJugador(int id) {
        logger.info("getUser("+id+")");

        for (Jugador u: this.Jugadores) {
            if (u.GetUserId() == id) {
                logger.info("getUser("+id+"): "+u);

                return u;
            }
        }

        logger.error("not found " + id);
        return null;
    }
    /*
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

     */
}
