package org.admu.lostandfound.payload;

import org.admu.lostandfound.models.LostItem;

public class ClaimResponse {
    private Long claimId;
    private String username;
    private long userId;
    private NestedLostItemResponse lostItem;

    public ClaimResponse(Long claimId, String username, long userId, LostItem lostItem) {
        this.claimId = claimId;
        this.username = username;
        this.userId = userId;
        this.lostItem = new NestedLostItemResponse(lostItem);
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public NestedLostItemResponse getLostItem() {
        return lostItem;
    }

    public void setLostItem(NestedLostItemResponse lostItem) {
        this.lostItem = lostItem;
    }

    /*
        "id": 4,
        "title": "iPhone 7",
        "description": "Has a blue sticker on the back",
        "itemStatus": "Unclaimed",
        "date": "2021-12-18",
        "time": "09:44:29",
     */
}
