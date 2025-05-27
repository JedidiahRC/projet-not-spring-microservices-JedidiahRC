package mg.mbds.emploi.competence.Mapper;

import mg.mbds.emploi.competence.Dto.CompetenceDto;
import mg.mbds.emploi.competence.Entity.Competence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompetenceMapper {
    CompetenceMapper INSTANCE = Mappers.getMapper(CompetenceMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nom", source = "nom")
    CompetenceDto toDto(Competence competence);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nom", source = "nom")
    Competence toEntity(CompetenceDto competenceDto);
}
