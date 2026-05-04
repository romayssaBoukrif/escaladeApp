package myapp.repo;

import myapp.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = "sorties")
    @Query("select c from Category c where c.id = :id")
    Optional<Category> findDetailedById(@Param("id") Long id);
}