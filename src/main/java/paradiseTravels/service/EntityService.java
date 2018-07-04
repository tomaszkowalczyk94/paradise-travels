package paradiseTravels.service;

import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.bean.user.UserBean;
import paradiseTravels.dao.EntityDAO;
import paradiseTravels.model.EntityIdInterface;
import paradiseTravels.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

abstract public class EntityService<T extends EntityIdInterface, K extends EntityBean> {

    @Inject
    protected K bean;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> getUsers() throws Exception{
        return bean.findAll();
    }

    @GET
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public T getUser(@PathParam("userid") int id) throws Exception{
        return (T)bean.findById(id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public T createNew(T user) throws Exception{
        bean.add(user);
        return user;
    }

    @PUT
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public T updateUser(T entity, @PathParam("userid") int id) throws Exception{
        entity.setId(id);
        bean.update(entity);
        return entity;
    }

    @DELETE
    @Path("/{userid}")
    public void delete(@PathParam("userid") int id) throws Exception {
        T entity = (T)bean.findById(id);
        bean.delete(entity);
    }
}
