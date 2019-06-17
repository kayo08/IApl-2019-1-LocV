/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import com.google.gson.Gson;
import model.dao.UserDAO;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import model.bean.Client;
import model.bean.Leasing;
import model.bean.User;
import model.bean.Vehicle;
import model.dao.ClientDAO;
import model.dao.LeasingDAO;
import model.dao.VehicleDAO;

/**
 * REST Web Service
 *
 * @author Near
 */
@Path("LeasingProject")
@Consumes({"application/json"})
@Produces({"application/json"})
public class LeasingWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LeasingWS
     */
    public LeasingWS() {
    }

    //http://localhost:8080/LocacaoWS/test-resbeans.html
    /**
     * Retrieves representation of an instance of webservice.LeasingWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/text")
    public String getJson() {
        return "my first WS RESTFULL";
    }

    @GET
    @Produces("application/json")
    @Path("/User/Search/{Login}")
    public String getUser(@PathParam("Login") String login) {
        User u = new User();
        u.setLogin(login);
        UserDAO dao = new UserDAO();
        u = dao.search(u);
        //Convert to json
        Gson g = new Gson();
        return g.toJson(u);
    }

    @GET
    @Produces("application/json")
    @Path("/Vehicle/Search/{Plate}")
    public String getVehicle(@PathParam("Plate") String plate) {
        Vehicle u = new Vehicle();
        u.setPlate(plate);
        VehicleDAO dao = new VehicleDAO();
        u = dao.search(u);
        //Convert to json
        Gson g = new Gson();
        return g.toJson(u);
    }

    @GET
    @Produces("application/json")
    @Path("/Client/Search/{CPF}")
    public String getClient(@PathParam("CPF") String cpf) {
        Client u = new Client();
        u.setCpf(cpf);
        ClientDAO dao = new ClientDAO();
        u = dao.search(u);
        //Convert to json
        Gson g = new Gson();
        return g.toJson(u);
    }

    @GET
    @Produces("application/json")
    @Path("/Leasing/Search/{Number_leasing}")
    public String getLeasing(@PathParam("Number_leasing") int number_leasing) {
        Leasing u = new Leasing();
        u.setNumberLeasing(number_leasing);
        LeasingDAO dao = new LeasingDAO();
        u = dao.search(u);
        //Convert to json
        Gson g = new Gson();
        return g.toJson(u);
    }

    @GET
    @Produces("application/json")
    @Path("/User/List")
    public String listUsers() {
        List<User> list;

        UserDAO dao = new UserDAO();
        list = dao.list();

        // Convert to json
        Gson g = new Gson();
        return g.toJson(list);
    }

    @GET
    @Produces("application/json")
    @Path("/Vehicle/List")
    public String listVehicles() {
        List<Vehicle> list;
        VehicleDAO dao = new VehicleDAO();
        list = dao.read();
        // Convert to json
        Gson g = new Gson();
        return g.toJson(list);
    }

    @GET
    @Produces("application/json")
    @Path("/Client/List")
    public String listClient() {
        List<Client> list;
        ClientDAO dao = new ClientDAO();
        list = dao.read();
        // Convert to json
        Gson g = new Gson();
        return g.toJson(list);
    }

    @GET
    @Produces("application/json")
    @Path("/Leasing/List")
    public String listLeasing() {
        List<Leasing> list;
        LeasingDAO dao = new LeasingDAO();
        list = dao.read();
        // Convert to json
        Gson g = new Gson();
        return g.toJson(list);
    }

    @GET
    @Consumes({"application/json"})
    @Path("/User/Insert/{Login}/{Password}/{Email}")
    public String insertUser(@PathParam("Login") String login, @PathParam("Password") String password, @PathParam("Email") String email) {
        User u = new User();
        u.setLogin(login);
        u.setPassword(password);
        u.setEmail(email);
        UserDAO dao = new UserDAO();
        dao.insert(u);
        String message = "Username: " + login + " successfully registered";
        return message;
    }

    @POST
    @Consumes({"application/json"})
    @Path("/Vehicle/Insert")
    public void insertVehicle(String content) {
        Gson g = new Gson();
        Vehicle u = (Vehicle) g.fromJson(content, Vehicle.class);
        VehicleDAO dao = new VehicleDAO();
        dao.create(u);
    }

    @POST
    @Consumes({"application/json"})
    @Path("/Client/Insert")
    public void insertClient(String content) {
        Gson g = new Gson();
        Client u = (Client) g.fromJson(content, Client.class);
        ClientDAO dao = new ClientDAO();
        dao.create(u);
    }

    @POST
    @Consumes({"application/json"})
    @Path("/Leasing/Insert")
    public void insertLeasing(String content) {
        Gson g = new Gson();
        Leasing u = (Leasing) g.fromJson(content, Leasing.class);
        LeasingDAO dao = new LeasingDAO();
        dao.create(u);
    }

    @GET
    @Consumes("application/json")
    @Path("/User/Update/{Login}/{Password}/{Email}")
    public String updateUser(@PathParam("Login") String login, @PathParam("Password") String password, @PathParam("Email") String email) {
        List<User> list;
        String message = "Username not found";
        User u = new User();
        u.setLogin(login);
        u.setPassword(password);
        u.setEmail(email);
        UserDAO dao = new UserDAO();
        list = dao.list();
        for (User p : dao.list()) {
            if(p.getLogin().equals(u.getLogin())){
            dao.update(u);
            message = "Username: "+u.getLogin()+" updated successfully";
            }
        }
        return message;
    }

    @PUT
    @Consumes("application/json")
    @Path("/Vehicle/Update")
    public void updateVehicle(String content) {
        Gson g = new Gson();
        Vehicle u = (Vehicle) g.fromJson(content, Vehicle.class);
        VehicleDAO dao = new VehicleDAO();
        dao.update(u);
    }

    @PUT
    @Consumes("application/json")
    @Path("/Client/Update")
    public void updateClient(String content) {
        Gson g = new Gson();
        Client u = (Client) g.fromJson(content, Client.class);
        ClientDAO dao = new ClientDAO();
        dao.update(u);
    }

    @PUT
    @Consumes("application/json")
    @Path("/Leasing/Update")
    public void updateLeasing(String content) {
        Gson g = new Gson();
        Leasing u = (Leasing) g.fromJson(content, Leasing.class);
        LeasingDAO dao = new LeasingDAO();
        dao.update(u);
    }

    @GET
    @Produces("application/json")
    @Path("/User/Delete/{Login}")
    public String deleteUser(@PathParam("Login") String login) {
        User u = new User();
        u.setLogin(login);
        UserDAO dao = new UserDAO();
        u = dao.search(u);
        String message = "User login: " + login + " was deleted";
        dao.delete(u);
        return message;
    }

    @GET
    @Produces("application/json")
    @Path("/Vehicle/Delete/{Plate}")
    public String deleteVehicle(@PathParam("Plate") String plate) {
        Vehicle u = new Vehicle();
        u.setPlate(plate);
        VehicleDAO dao = new VehicleDAO();
        u = dao.search(u);
        String message = "Vehicle Plate: " + plate + " was deleted";
        dao.delete(u);
        return message;
    }

    @GET
    @Produces("application/json")
    @Path("/Client/Delete/{CPF}")
    public String deleteClient(@PathParam("CPF") String CPF) {
        Client u = new Client();
        u.setCpf(CPF);
        ClientDAO dao = new ClientDAO();
        u = dao.search(u);
        String message = "CPF Client: " + CPF + " was deleted";
        dao.delete(u);
        return message;
    }

    @GET
    @Produces("application/json")
    @Path("/Leasing/Delete/{Number_leasing}")
    public String deleteLeasing(@PathParam("Number_leasing") int number_leasing) {
        Leasing u = new Leasing();
        u.setNumberLeasing(number_leasing);
        LeasingDAO dao = new LeasingDAO();
        u = dao.search(u);
        String message = "Leasing Number: " + number_leasing + " was deleted";
        dao.delete(u);
        return message;
    }
}
