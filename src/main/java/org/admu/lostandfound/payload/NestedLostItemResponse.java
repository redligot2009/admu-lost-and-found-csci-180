package org.admu.lostandfound.payload;

import org.admu.lostandfound.models.LostItem;

public class NestedLostItemResponse {
    private long itemId;
    private String title;
    private String description;
    private String itemStatus;

    public NestedLostItemResponse(LostItem lostItem) {
        itemId = lostItem.getId();
        title = lostItem.getTitle();
        itemStatus = lostItem.getItemStatus();
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
}
