package ma.youcode.managment_tournoi_backend.mapper;

import ma.youcode.managment_tournoi_backend.dto.rulesDto.RulesCreateDto;
import ma.youcode.managment_tournoi_backend.dto.rulesDto.RulesUpdateDto;
import ma.youcode.managment_tournoi_backend.entity.Rules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RuleMapper {
    RuleMapper INSTANCE = Mappers.getMapper(RuleMapper.class);
    @Mapping(target = "id", ignore = true)
    Rules CreateRulesDtotoRules(RulesCreateDto rule);
    Rules UpdateRulesDtotoRules(RulesUpdateDto rule);

}
