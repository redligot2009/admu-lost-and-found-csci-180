package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Claim;
import org.admu.lostandfound.models.LostItem;
import org.admu.lostandfound.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    public Claim[] findByUser(User user);
    public Claim[] findByLostItem(LostItem item);
}
