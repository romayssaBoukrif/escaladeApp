package myapp.repo;

import myapp.model.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SortieRepository extends JpaRepository<Sortie, Long>, JpaSpecificationExecutor<Sortie> {
    List<Sortie> findByCreateurId(Long createurId);
}