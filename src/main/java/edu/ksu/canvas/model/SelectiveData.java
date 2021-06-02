package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.List;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

/**
 * Class to represent Canvas items for selective import.
 * See <a href="https://canvas.instructure.com/doc/api/content_migrations.html#method.content_migrations.content_list">Content Migrations - Selective Data</a> documentation.
 */
 @CanvasObject(postKey = "selective_data")
public class SelectiveData extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private String type;
    private String property;
    private String title;
    private Integer count;
    private String subItemsUrl;
    private List<SelectiveData> subItems;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @CanvasField(postKey = "sub_items_url")
    public String getSubItemsUrl() {
        return subItemsUrl;
    }

    public void setSubItemsUrl(String subItemsUrl) {
        this.subItemsUrl = subItemsUrl;
    }

    @CanvasField(postKey = "sub_items")
    public List<SelectiveData> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<SelectiveData> subItems) {
        this.subItems = subItems;
    }

}
