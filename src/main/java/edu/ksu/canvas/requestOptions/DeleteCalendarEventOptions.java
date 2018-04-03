package edu.ksu.canvas.requestOptions;

public class DeleteCalendarEventOptions extends BaseOptions {

    public DeleteCalendarEventOptions cancelReason(String cancelReason) {
        addSingleItem("cancel_reason", cancelReason);
        return this;
    }

}
