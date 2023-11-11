package eetac.upc.dsa.services;

import eetac.upc.dsa.GameManager;
import eetac.upc.dsa.GameManagerImpl;
import eetac.upc.dsa.models.User;
import eetac.upc.dsa.models.Game;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UserService {

    private GameManager gm;

    public UserService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.UserSize()==0) {
            this.gm.addUser("Obifran");
            this.gm.addUser("Lobi");
            this.gm.addUser("Fernando33");
        }
    }

    @GET
    @ApiOperation(value = "get all Users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> User = this.gm.findAllUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(User) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        User u = this.gm.getUser(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }
    @PUT
    @ApiOperation(value = "level up a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response nextlevelUser(int id, int points, String date) {

        User u = this.gm.nextlevelUser(id, points, date);

        if (u == null) return Response.status(404).build();
        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.GetUserName() == null)  return Response.status(500).entity(user).build();
        this.gm.addUser(user);
        return Response.status(201).entity(user).build();
    }

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
}