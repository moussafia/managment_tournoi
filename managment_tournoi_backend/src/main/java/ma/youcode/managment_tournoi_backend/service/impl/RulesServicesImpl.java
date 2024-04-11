package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Rules;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.RulesRepository;
import ma.youcode.managment_tournoi_backend.service.RulesServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class RulesServicesImpl implements RulesServices {
    private RulesRepository rulesRepository;
    @Override
    public List<Rules> getRules() {
        return rulesRepository.findAll();
    }

    @Override
    public Rules addRule(Rules rules) {

        return rulesRepository.save(rules);
    }

    @Override
    public Rules updateRule(Rules rules) {
        rulesRepository.findById(rules.getId()).orElseThrow(()-> new EntityNotFoundException("Rule with id " + rules.getId() + " does not exist"));
        return rulesRepository.save(rules);
    }

    @Override
    public void deleteRule(Long id) {
        rulesRepository.deleteById(id);
    }
}
