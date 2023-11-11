package eetac.upc.dsa;

import eetac.upc.dsa.models.Partida;
import eetac.upc.dsa.models.Jugador;
import eetac.upc.dsa.models.Avatar;
import eetac.upc.dsa.models.Tienda;
import eetac.upc.dsa.models.Mapa;

import java.util.List;

public interface GameManager {

    // Jugador manager
    public Jugador addJugador(String username, String mail, String pasword);
    public Jugador addJugador(Jugador jugador);
    public Jugador getJugador(int id);
    public int getPoints(int id);
    public List<Jugador> findAllJugadores();
    public List <Jugador> findAllJugadoresInGame(Partida partida);
    public Jugador addPointsJugador(int id, int points, String date);



    /* Game manager
    public Partida addPartida(int dif, int idPlayer, int idMapa);
    public Partida addPartida(Partida partida);
    // public void startGame(Game game, User user);
    public Partida getPartida(int id);
    public int getLevels(int id);
    public List<Partida> findAllPartidas();
    public List<Partida> findAllPartidasInJugadpor(Jugador jugador);


    public int GameSize(); */
    public int JugadoresSize();

}