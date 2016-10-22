package org.bochenlong.jersey;

import org.bochenlong.dao.UserMiDao;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by bochenlong on 16-10-8.
 */
@Path("user")
public class UserResource {
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String save(@FormParam("name") String name, @FormParam("password") String password) {
        s(name, password);
        return "ok";
    }

    private void s(String name, String password) {
        UserMiDao userDao = new UserMiDao();
        userDao.save(name, password);
        userDao.close();
    }
}
