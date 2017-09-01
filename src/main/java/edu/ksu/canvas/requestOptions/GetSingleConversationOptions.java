package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSingleConversationOptions extends BaseOptions {

    public enum FilterMode {AND, OR, DEFAULT_OR;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    private Integer conversationId;

    public GetSingleConversationOptions(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    /**
     * Used when setting the "visible" field in the response. See the "List Conversations" docs for details
     * @param filters Filter strings to be applied to the visibility of conversations
     * @return this to continue building options
     */
    public GetSingleConversationOptions filters(List<String> filters) {
        if(filters.size() == 1) { //Canvas API doesn't want the [] if it is only one value
            addSingleItem("filter", filters.get(0));
        } else {
            optionsMap.put("filter[]", filters);
        }
        return this;
    }

    /**
     * Used when setting the "visible" field in the response. See the "List Conversations" docs for details
     * @param filterMode Mode to use when multiple filters are given
     * @return this to continue building options
     */
    public GetSingleConversationOptions filterMode(FilterMode filterMode) {
        addSingleItem("filter_mode", filterMode.toString());
        return this;
    }

    /**
     * If true, unread conversations will be automatically marked as read.
     * Currently this defaults to true but this is planned to change to false in the future
     * @param markAsRead Whether to mark conversations as read
     * @return this to continue building options
     */
    public GetSingleConversationOptions autoMarkAsRead(Boolean markAsRead) {
        addSingleItem("auto_mark_as_read", markAsRead.toString());
        return this;
    }
}
