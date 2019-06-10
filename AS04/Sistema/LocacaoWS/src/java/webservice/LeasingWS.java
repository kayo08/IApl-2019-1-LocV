/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import com.google.gson.Gson;
import model.dao.UserDAO;
import java.util.ArrayList;
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
@Path("leasing")
public class LeasingWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FazendaWS
     */
    public LeasingWS() {
    }

    /**
     * Retrieves representation of an instance of ws.FazendaWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("User/get/{login}")
    public String getUser(@PathParam("login") String login) {
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
    @Path("Vehicle/get/{placa}")
    public String getVehicle(@PathParam("placa") String placa) {
        Vehicle u = new Vehicle();
        u.setPlaca(placa);
        VehicleDAO dao = new VehicleDAO();
        u = dao.search(u);
        //Convert to json
        Gson g = new Gson();
        return g.toJson(u);
    }

    @GET
    @Produces("application/json")
    @Path("Client/get/{cpf}")
    public String getClient(@PathParam("cpf") String cpf) {
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
    @Path("Leasing/get/{numero_leasing}")
    public String getLeasing(@PathParam("numero_leasing") int numero_leasing) {
        Leasing u = new Leasing();
        u.setNumeroLeasing(numero_leasing);
        LeasingDAO dao = new LeasingDAO();
        u = dao.search(u);
        //Convert to json
        Gson g = new Gson();
        return g.toJson(u);
    }

    @GET
    @Produces("application/json")
    @Path("User/list")
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
    @Path("Vehicle/list")
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
    @Path("Client/list")
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
    @Path("Leasing/list")
    public String listLeasing() {
        List<Leasing> list;
        LeasingDAO dao = new LeasingDAO();
        list = dao.read();
        // Convert to json
        Gson g = new Gson();
        return g.toJson(list);
    }

    @POST
    @Consumes({"application/json"})
    @Path("User/insert")
    public boolean insertUser(String content) {
        Gson g = new Gson();
        User u = (User) g.fromJson(content, User.class);
        UserDAO dao = new UserDAO();
        return dao.insert(u);
    }

    @POST
    @Consumes({"application/json"})
    @Path("Vehicle/insert")
    public void insertVehicle(String content) {
        Gson g = new Gson();
        Vehicle u = (Vehicle) g.fromJson(content, Vehicle.class);
        VehicleDAO dao = new VehicleDAO();
        dao.create(u);
    }

    @POST
    @Consumes({"application/json"})
    @Path("Client/insert")
    public void insertClient(String content) {
        Gson g = new Gson();
        Client u = (Client) g.fromJson(content, Client.class);
        ClientDAO dao = new ClientDAO();
        dao.create(u);
    }

    @POST
    @Consumes({"application/json"})
    @Path("Leasing/insert")
    public void insertLeasing(String content) {
        Gson g = new Gson();
        Leasing u = (Leasing) g.fromJson(content, Leasing.class);
        LeasingDAO dao = new LeasingDAO();
        dao.create(u);
    }

    @PUT
    @Consumes("application/json")
    @Path("User/update")
    public void updateUser(String content) {
        Gson g = new Gson();
        User u = (User) g.fromJson(content, User.class);
        UserDAO dao = new UserDAO();
        dao.update(u);
    }

    @PUT
    @Consumes("application/json")
    @Path("Vehicle/update")
    public void updateVehicle(String content) {
        Gson g = new Gson();
        Vehicle u = (Vehicle) g.fromJson(content, Vehicle.class);
        VehicleDAO dao = new VehicleDAO();
        dao.update(u);
    }

    @PUT
    @Consumes("application/json")
    @Path("Client/update")
    public void updateClient(String content) {
        Gson g = new Gson();
        Client u = (Client) g.fromJson(content, Client.class);
        ClientDAO dao = new ClientDAO();
        dao.update(u);
    }

    @PUT
    @Consumes("application/json")
    @Path("Leasing/update")
    public void updateLeasing(String content) {
        Gson g = new Gson();
        Leasing u = (Leasing) g.fromJson(content, Leasing.class);
        LeasingDAO dao = new LeasingDAO();
        dao.update(u);
    }

    @DELETE
    @Path("User/delete/{login}")
    public boolean deleteUser(@PathParam("login") String login) {
        User u = new User();
        u.setLogin(login);
        UserDAO dao = new UserDAO();
        u = dao.search(u);
        return dao.delete(u);
    }

    @DELETE
    @Path("Vehicle/delete/{placa}")
    public void deleteVehicle(@PathParam("placa") String placa) {
        Vehicle u = new Vehicle();
        u.setPlaca(placa);
        VehicleDAO dao = new VehicleDAO();
        u = dao.search(u);
        dao.delete(u);
    }

    @DELETE
    @Path("Client/delete/{cpf}")
    public void deleteClient(@PathParam("cpf") String cpf) {
        Client u = new Client();
        u.setCpf(cpf);
        ClientDAO dao = new ClientDAO();
        u = dao.search(u);
        dao.delete(u);
    }

    @DELETE
    @Path("Leasing/delete/{numero_leasing}")
    public void deleteLeasing(@PathParam("numero_leasing") int numero_leasing) {
        Leasing u = new Leasing();
        u.setNumeroLeasing(numero_leasing);
        LeasingDAO dao = new LeasingDAO();
        u = dao.search(u);
        dao.delete(u);
    }
}
