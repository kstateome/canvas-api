package edu.ksu.canvas.requestOptions;

public class ListExternalToolsOptions extends BaseOptions {

    private String id;

    public ListExternalToolsOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * Only return tools with a name matching this partial string
     * @param searchTerm Tool name to search for
     * @return This object to allow adding more options
     */
    public ListExternalToolsOptions searchTerm(String searchTerm) {
        if(searchTerm == null || searchTerm.length() < 3) {
            throw new IllegalArgumentException("Search term must be at least 3 characters");
        }
        addSingleItem("search_term", searchTerm);
        return this;
    }

    /**
     * If true, only tools that are meant to be selectable are returned
     * @param selectable Whether to filter tools by selectability
     * @return This object to allow adding more options
     */
    public ListExternalToolsOptions isSelectable(Boolean selectable) {
        addSingleItem("selectable", selectable.toString());
        return this;
    }

    /**
     * If true, include tools installed in all accounts above the current context.
     * <p>
     * This will give you ALL the tools available in the specified course/group/account
     * even if they were installed in a parent account.
     * @param includeParents True to include tools from parent accounts
     * @return This object to allow adding more options
     */
    public ListExternalToolsOptions includeParentTools(Boolean includeParents) {
        addSingleItem("include_parents", includeParents.toString());
        return this;
    }
}
