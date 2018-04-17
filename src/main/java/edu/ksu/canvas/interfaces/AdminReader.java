package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.AccountAdmin;
import edu.ksu.canvas.requestOptions.ListAccountAdminsOptions;

import java.io.IOException;
import java.util.List;

public interface AdminReader extends CanvasReader<AccountAdmin, AdminReader> {
    /**
     * Return a list of account admins that the current user can view or manage.
     * @param options Object encapsulating parameters to the list accounts API call
     * @return List of account admins
     * @throws IOException When there is an error communicating with Canvas
     */
    List<AccountAdmin> listAccountAdmins(ListAccountAdminsOptions options) throws IOException;
}
