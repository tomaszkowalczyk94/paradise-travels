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
    public List<T> getAll() throws Exception{
        return bean.findAll();
    }

    @GET
    @Path("/{entityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public T getOne(@PathParam("entityId") int id) throws Exception{
        return (T)bean.findById(id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public T createNew(T entity) throws Exception{
        bean.add(entity);
        return entity;
    }

    @PUT
    @Path("/{entityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public T updateUser(T entity, @PathParam("entityId") int id) throws Exception{
        entity.setId(id);
        bean.update(entity);
        return entity;
    }

    @DELETE
    @Path("/{entityId}")
    public void delete(@PathParam("entityId") int id) throws Exception {
        T entity = (T)bean.findById(id);
        bean.delete(entity);
    }
}
