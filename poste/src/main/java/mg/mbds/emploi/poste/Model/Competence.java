package mg.mbds.emploi.poste.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competence {
    private Long id;
    private String nom;

    public Competence(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Competence{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }

}
