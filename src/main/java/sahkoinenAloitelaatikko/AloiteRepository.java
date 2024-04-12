package sahkoinenAloitelaatikko;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AloiteRepository extends JpaRepository<Aloite, Long> {
    @EntityGraph(value = "Aloite.tekija")
    List<Aloite> findByIdNotNull();
}
