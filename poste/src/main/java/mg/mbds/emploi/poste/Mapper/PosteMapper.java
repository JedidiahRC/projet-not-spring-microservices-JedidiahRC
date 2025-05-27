package mg.mbds.emploi.poste.Mapper;


import mg.mbds.emploi.poste.Dto.CompetenceResponseDto;
import mg.mbds.emploi.poste.Dto.PosteDto;
import mg.mbds.emploi.poste.Entity.Poste;
import mg.mbds.emploi.poste.Model.CompetenceClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PosteMapper {
    public static PosteDto toDto(Poste poste, CompetenceClient competenceClient) {
        PosteDto posteDto = PosteDto.builder()
                .id(poste.getId())
                .nom(poste.getNom())
                .competences(poste.getCompetences())
                .build();

        if (poste.getCompetences() != null) {
            List<CompetenceResponseDto> competenceDetails = poste.getCompetences().stream()
                    .map(competenceClient::getCompetenceById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            posteDto.setCompetenceDetails(competenceDetails);
        }

        return posteDto;
    }

    public static Poste toEntity(PosteDto posteDto) {
        return Poste.builder()
                .id(posteDto.getId())
                .nom(posteDto.getNom())
                .competences(posteDto.getCompetences())
                .build();
    }
}