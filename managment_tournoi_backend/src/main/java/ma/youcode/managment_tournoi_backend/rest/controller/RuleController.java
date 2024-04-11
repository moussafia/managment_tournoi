package ma.youcode.managment_tournoi_backend.rest.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Rules;
import ma.youcode.managment_tournoi_backend.service.RulesServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rule")
@RequiredArgsConstructor
public class RuleController {
    private final RulesServices rulesServices;
    public ResponseEntity<List<Rules>> getAllRules(){
        return ResponseEntity.ok(rulesServices.getRules());
    }
    public ResponseEntity<Rules>> updateRules
        return ResponseEntity.ok(rulesServices.getRules());
    }

}
