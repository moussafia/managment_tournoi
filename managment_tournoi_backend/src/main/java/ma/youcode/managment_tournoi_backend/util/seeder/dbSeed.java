package ma.youcode.managment_tournoi_backend.util.seeder;

import ma.youcode.managment_tournoi_backend.entity.AppRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class dbSeed {
    private final boolean seedFakeData;
    private final RoleSeeder roleSeeder;

    public dbSeed(@Value("${socceryc.seedFakeData}") boolean seedFakeData, RoleSeeder roleSeeder) {
        this.seedFakeData = seedFakeData;
        this.roleSeeder = roleSeeder;
    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            if(seedFakeData){
                roleSeeder.createRoles()
            }
        };
    }
}
