package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.Location;
import org.admu.lostandfound.models.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    @Query("SELECT l FROM LostItem l " +
            "WHERE (:title IS NULL OR l.title = :title) " +
            "AND (:itemStatus IS NULL OR l.itemStatus = :itemStatus) " +
            "AND (:date IS NULL OR l.date = :date) " +
            "AND (:category IS NULL OR l.category = :category) " +
            "AND (:location IS NULL OR l.location = :location ) ")
    public List<LostItem> findByTitleAndItemStatusAndDateAndCategoryAndLocation(
            @Param("title") String title,
            @Param("itemStatus") String itemStatus,
            @Param("date") LocalDate date,
            @Param("category") Category category,
            @Param("location") Location location
    );

    public List<LostItem> findByCategory(Category category);
    public List<LostItem> findByLocation(Location location);
    public List<LostItem> findByItemStatus(String itemStatus);
    public List<LostItem> findByDate(Date date);
}
