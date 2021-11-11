package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findByTitle(String title);
}
