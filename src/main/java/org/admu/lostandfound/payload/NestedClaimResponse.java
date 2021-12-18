package org.admu.lostandfound.payload;

public class NestedClaimResponse {
    private Long claimId;
    private String username;
    private long userId;

    public NestedClaimResponse(Long claimId, String username, long userId) {
        this.claimId = claimId;
        this.username = username;
        this.userId = userId;
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
}
