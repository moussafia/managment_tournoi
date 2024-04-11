package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.Rules;

import java.util.List;

public interface RulesServices {
    List<Rules> getRules();
    Rules addRule(Rules rules);
    Rules updateRule(Rules rules);
    void deleteRule(Long id);
}
