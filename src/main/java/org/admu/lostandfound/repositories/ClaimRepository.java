package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    public List<Claim> findByUser(User claimer);
    public List<Claim> findByLostItem(LostItem item);
}
