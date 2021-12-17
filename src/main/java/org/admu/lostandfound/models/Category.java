package org.admu.lostandfound.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @NotNull
    @Column(name="created_date", nullable = false)
    private LocalDate createdDate;

    @NotNull
    @Column(name="updated_date")
    private LocalDate updatedDate;

    public Category(String title, User admin, LocalDate createdDate) {
        this.title = title;
        this.admin = admin;
        this.createdDate = createdDate;
    }

    // no-arg constructor
    public Category() {
        this.title = null;
        this.admin = null;
        this.createdDate = null;
        this.updatedDate = null;
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

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", admin=" + admin +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
