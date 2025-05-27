package mg.mbds.emploi.poste.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poste {
    @Id
    private String id;
    private String nom;

    @ElementCollection
    @CollectionTable(name = "competence_ids", joinColumns = @JoinColumn(name = "id"))
    private List<Long> competences;
}
