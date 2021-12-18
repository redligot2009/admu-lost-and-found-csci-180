package org.admu.lostandfound.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Transactional
@Entity
@Table(name="claims")
public class Claim {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "claimer_id")
    private User claimer;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "lost_item_id")
    private LostItem lostItem;

    @NotNull
    @Column(name="created_date", nullable = false)
    private LocalDate createdDate;

    public Claim(User claimer, LostItem lostItem, LocalDate createdDate) {
        this.claimer = claimer;
        this.lostItem = lostItem;
        this.createdDate = createdDate;
    }

    public Claim() {
        this.claimer = null;
        this.lostItem = null;
        this.createdDate = null;
    }

    public Long getId() {
        return id;
    }

    public User getClaimer() {
        return claimer;
    }

    public void setClaimer(User claimer) {
        this.claimer = claimer;
    }

    public LostItem getLostItem() {
        return lostItem;
    }

    public void setLostItem(LostItem lostItem) {
        this.lostItem = lostItem;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
