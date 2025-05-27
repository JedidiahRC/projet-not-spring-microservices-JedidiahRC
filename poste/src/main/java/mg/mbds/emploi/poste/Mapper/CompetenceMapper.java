package mg.mbds.emploi.poste.Mapper;

import mg.mbds.emploi.poste.Dto.CompetenceResponseDto;
import mg.mbds.emploi.poste.Model.Competence;

public class CompetenceMapper {
    public static CompetenceResponseDto toDto(Competence competence) {
        return CompetenceResponseDto.builder()
                .id(competence.getId())
                .nom(competence.getNom())
                .build();
    }

    public static Competence toEntity(CompetenceResponseDto competenceDto) {
        return Competence.builder()
                .id(competenceDto.getId())
                .nom(competenceDto.getNom())
                .build();
    }
}
