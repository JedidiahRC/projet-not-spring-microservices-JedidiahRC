package mg.mbds.emploi.competence.Repository;

import mg.mbds.emploi.competence.Entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
//    Competence findByNom(String nom);
//    Optional<Competence> findById(Long id);
//    void deleteById(Long id);
}
