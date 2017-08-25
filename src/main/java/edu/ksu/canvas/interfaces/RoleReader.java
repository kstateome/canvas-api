package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Role;
import edu.ksu.canvas.requestOptions.ListRolesOptions;

import java.io.IOException;
import java.util.List;

public interface RoleReader extends CanvasReader<Role, RoleReader> {
    /**
     * Return a list of roles for an account.
     * @param options Object encapsulating parameters to the list accounts API call
     * @return List of roles
     * @throws IOException When there is an error communicating with Canvas
     */
    public List<Role> listRoles(ListRolesOptions options) throws IOException;
}
