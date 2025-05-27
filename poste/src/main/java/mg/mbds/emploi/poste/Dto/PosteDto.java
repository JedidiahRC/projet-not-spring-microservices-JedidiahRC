package mg.mbds.emploi.poste.Dto;

import lombok.Builder;

import java.util.List;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PosteDto {
    private String id;
    private String nom;
    private List<Long> competences;
    private List<CompetenceResponseDto> competenceDetails;
}
