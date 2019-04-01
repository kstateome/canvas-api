package edu.ksu.canvas.requestOptions;

public class DeleteCalendarEventOptions extends BaseOptions {

    private Integer id;

    /**
     * @param id The calendar event to delete.
     */
    public DeleteCalendarEventOptions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public DeleteCalendarEventOptions cancelReason(String cancelReason) {
        addSingleItem("cancel_reason", cancelReason);
        return this;
    }

}
