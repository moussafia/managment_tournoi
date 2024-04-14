package ma.youcode.managment_tournoi_backend.rest.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.rulesDto.RulesCreateDto;
import ma.youcode.managment_tournoi_backend.dto.rulesDto.RulesUpdateDto;
import ma.youcode.managment_tournoi_backend.entity.Rules;
import ma.youcode.managment_tournoi_backend.mapper.RuleMapper;
import ma.youcode.managment_tournoi_backend.service.RulesServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rule")
@RequiredArgsConstructor
public class RuleController {
    private final RulesServices rulesServices;
    @GetMapping
    public ResponseEntity<List<Rules>> getAllRules(){
        return ResponseEntity.ok(rulesServices.getRules());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Rules> getRuleById(@PathVariable("id") Long id){
        return ResponseEntity.ok(rulesServices.getRuleById(id));
    }
    @PostMapping
    public ResponseEntity<Rules> createRule(@RequestBody RulesCreateDto rulesCreateDto){
        return ResponseEntity.ok(rulesServices.addRule(RuleMapper.INSTANCE.CreateRulesDtotoRules(rulesCreateDto)));
    }
    @PutMapping
    public ResponseEntity<Rules> updateRule(@RequestBody RulesUpdateDto rulesUpdateDto){
        return ResponseEntity.ok(rulesServices.addRule(RuleMapper.INSTANCE.UpdateRulesDtotoRules(rulesUpdateDto)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRule(@PathVariable Long id){
        rulesServices.deleteRule(id);
        return ResponseEntity.ok("rule is deleted with success");
    }


}
