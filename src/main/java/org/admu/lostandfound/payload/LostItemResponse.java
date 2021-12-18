package org.admu.lostandfound.payload;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.Location;
import org.admu.lostandfound.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class LostItemResponse {
    private Long id;
    private String title;
    private String description;
    private String itemStatus;
    private LocalDate date;
    private LocalTime time;
    private Location location;
    private Set<NestedClaimResponse> claims;
    private Category category;

    public LostItemResponse(Long id, String title, String description,
                            String itemStatus, LocalDate date, LocalTime time,
                            Location location, Category category,
                            Set<Claim> originalClaims) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.itemStatus = itemStatus;
        this.date = date;
        this.time = time;
        this.location = location;
        this.category = category;
        claims = new HashSet<>();
        for (Claim claim: originalClaims) {
            User claimer = claim.getClaimer();
            claims.add(new NestedClaimResponse(claim.getId(),claimer.getUsername(),claimer.getId()));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<NestedClaimResponse> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> originalClaims) {
        for (Claim claim: originalClaims) {
            User claimer = claim.getClaimer();
            claims.add(new NestedClaimResponse(claim.getId(),claimer.getUsername(),claimer.getId()));
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
