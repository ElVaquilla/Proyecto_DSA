package eetac.upc.dsa.services;

import eetac.upc.dsa.GameManager;
import eetac.upc.dsa.GameManagerImpl;

import eetac.upc.dsa.models.Jugador;
import eetac.upc.dsa.models.Partida;
import eetac.upc.dsa.models.Avatar;
import eetac.upc.dsa.models.Tienda;
import eetac.upc.dsa.models.Mapa;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Api(value = "/jugadores", description = "Endpoint to Jugador Service")
@Path("/jugadores")
public class JugadorService {

    private GameManager gm;

    public JugadorService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.JugadoresSize()==0) {
            this.gm.addJugador("Antonio","Fernanditox_47","SweetP2");
            this.gm.addJugador("Lobi","malasia.2010@gmail.com","Perico23");
            this.gm.addJugador("Fernando33","brasil.2005@gmail.com","33?");
        }
    }

    @GET
    @ApiOperation(value = "get all Jugadores", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Jugador.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJugadores() {

        List<Jugador> Jugadores = this.gm.findAllJugadores();

        GenericEntity<List<Jugador>> entity = new GenericEntity<List<Jugador>>(Jugadores) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get a Jugador", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Jugador.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        Jugador j = this.gm.getJugador(id);
        if (j == null) return Response.status(404).build();
        else  return Response.status(201).entity(j).build();
    }
    @PUT
    @ApiOperation(value = "More points for jugador", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response nextlevelUser(int id, int points, String date) {

        Jugador j = this.gm.addPointsJugador(id, points, date);

        if (j == null) return Response.status(404).build();
        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "register a new Jugador", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response regJugador(Jugador jugador) {
        if (jugador.GetMail().split("@")[1] != "gmail.com" || jugador.GetMail() == null  || jugador.GetUserName() == null || jugador.GetPasword() == null)  return Response.status(500).build();
        this.gm.addJugador(jugador);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "login a jugador", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesful"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logJugador(String username, String pasword) {
        if(username == null || pasword == null)
            return Response.status(500).build();
        else {
            List<Jugador> jugadores = this.gm.findAllJugadores();
            boolean encontrado = false;
            for(Jugador j : jugadores){
                if(j.GetUserName() == username && j.GetPasword() == pasword)
                    return Response.status(201).build();
            }
            return Response.status(404).build();
        }
    }

    /*
    @PUT
    @ApiOperation(value = "Start game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response startGameUser(Game g, User u) {
        gm.startGame(g,u);

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "End game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response endGameUser(int idg, int id) {
        this.gm.endGame(id);

        if (this.gm.getUser(id) == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Get all games played by a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGamesByUser(@PathParam("id") int id) {
        if(this.gm.getUser(id) != null) {
            User u = this.gm.getUser(id);
            List<Game> games = this.gm.findAllGamesInUser(u);

            GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
            return Response.status(201).entity(entity).build()  ;
        }
        else
            return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "Get level of user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Object.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLevelUser(@PathParam("id") int id) {
        if(this.gm.getUser(id) != null){
            int level = this.gm.getLevel(id);
            GenericEntity<Integer> entity = new GenericEntity<Integer>(level) {};
            return Response.status(201).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "Punctuation of user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Object.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPointsUser(@PathParam("id") int id) {
        if(this.gm.getUser(id) != null){
            int points = this.gm.getPoints(id);

            GenericEntity<Integer> entity = new GenericEntity<Integer>(points) {};
            return Response.status(201).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }
    */
}