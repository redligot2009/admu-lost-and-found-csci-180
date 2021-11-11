package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.Location;
import org.admu.lostandfound.models.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    public LostItem[] findByCategory(Category category);
    public LostItem[] findByLocation(Location location);
    public LostItem[] findByStatus(String status);
    public LostItem[] findByDate(Date date);
}
