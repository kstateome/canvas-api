package edu.ksu.canvas.requestOptions;

public class DeleteCalendarEventOptions extends BaseOptions {

    private Long id;

    /**
     * @param id The calendar event to delete.
     */
    public DeleteCalendarEventOptions(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public DeleteCalendarEventOptions cancelReason(String cancelReason) {
        addSingleItem("cancel_reason", cancelReason);
        return this;
    }

}
