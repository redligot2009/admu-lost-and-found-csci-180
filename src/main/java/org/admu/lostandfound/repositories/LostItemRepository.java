package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Category;
import org.admu.lostandfound.models.Location;
import org.admu.lostandfound.models.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    public List<LostItem> findByCategory(Category category);
    public List<LostItem> findByLocation(Location location);
    public List<LostItem> findByItemStatus(String itemStatus);
    public List<LostItem> findByDate(Date date);
}
