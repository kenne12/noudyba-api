package org.kenne.noudybaapi;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class NoudybaApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(NoudybaApiApplication.class, args);
    }

    @Bean
    CommandLineRunner run(/*VilleService villeService,
                          MembreService membreService,
                          RubriqueService rubriqueService,
                          AnneeService anneeService,
                          EvenementService evenementService,
                          SouscriptionService souscriptionService,
                          PayementSouscriptionService payementSouscriptionService,
                          ContributionService contributionService,
                          PosteService posteService,
                          UtilisateurService utilisateurService,
                          PeriodeService periodeService*/) {
        return args -> {

            /*villeService.save(new VilleRequestDTO(null, "Bafoussam"));
            villeService.save(new VilleRequestDTO(null, "Douala"));
            villeService.save(new VilleRequestDTO(null, "Yaoundé"));
            villeService.save(new VilleRequestDTO(null, "Paris"));
            villeService.save(new VilleRequestDTO(null, "Limbé"));
            villeService.save(new VilleRequestDTO(null, "Mbouda"));

            List<VilleResponseDTO> listVilles = villeService.findAll();

            MembreRequestDTO membre1 = new MembreRequestDTO(null, "Diffo", "Rodrigue Martial", "674949013", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            ;
            membre1.setIdMembre(membreService.save(membre1).getIdMembre());

            MembreRequestDTO membre2 = new MembreRequestDTO(null, "Diffo Kenne", "Ludovic", "674949013", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre2.setIdMembre(membreService.save(membre2).getIdMembre());

            MembreRequestDTO membre3 = new MembreRequestDTO(null, "Melaguie", "Ferdinand", "674949013", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre3.setIdMembre(membreService.save(membre3).getIdMembre());

            MembreRequestDTO membre4 = new MembreRequestDTO(null, "Kenne", "Gervais", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre4.setIdMembre(membreService.save(membre4).getIdMembre());

            MembreRequestDTO membre5 = new MembreRequestDTO(null, "Djiméné", "Serge", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre5.setIdMembre(membreService.save(membre5).getIdMembre());

            MembreRequestDTO membre6 = new MembreRequestDTO(null, "Doungmo", "Carlos", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre6.setIdMembre(membreService.save(membre6).getIdMembre());

            MembreRequestDTO membre7 = new MembreRequestDTO(null, "Tiwa", "Narcisse", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre7.setIdMembre(membreService.save(membre7).getIdMembre());

            MembreRequestDTO membre8 = new MembreRequestDTO(null, "Douanla", "Boris", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre8.setIdMembre(membreService.save(membre8).getIdMembre());

            MembreRequestDTO membre9 = new MembreRequestDTO(null, "Dagah", "Yannick", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre9.setIdMembre(membreService.save(membre9).getIdMembre());

            MembreRequestDTO membre10 = new MembreRequestDTO(null, "Tchinda", "Belmond", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre10.setIdMembre(membreService.save(membre10).getIdMembre());

            MembreRequestDTO membre11 = new MembreRequestDTO(null, "Dmoffo Mogou", "Idriss", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre11.setIdMembre(membreService.save(membre11).getIdMembre());

            MembreRequestDTO membre12 = new MembreRequestDTO(null, "Dongmo Messoueko", "Franklin", "673564186", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());
            membre12.setIdMembre(membreService.save(membre12).getIdMembre());

            RubriqueRequestDTO r1 = new RubriqueRequestDTO(null, "Projet", "R001", RubricType.NON_REPEAT);
            rubriqueService.save(r1);

            RubriqueRequestDTO r2 = new RubriqueRequestDTO(null, "Anniversaire", "R002", RubricType.REPEAT);
            rubriqueService.save(r2);

            RubriqueRequestDTO r3 = new RubriqueRequestDTO(null, "Dons", "R003", RubricType.REPEAT);
            rubriqueService.save(r3);

            RubriqueRequestDTO r4 = new RubriqueRequestDTO(null, "Félicitations", "R004", RubricType.REPEAT);
            rubriqueService.save(r4);

            RubriqueRequestDTO r5 = new RubriqueRequestDTO(null, "Sanctions", "R005", RubricType.REPEAT);
            rubriqueService.save(r5);

            DateTime dateDebut = new DateTime("2021-01-01");
            DateTime dateFin = new DateTime("2021-12-31");
            AnneeRequestDTO a1 = new AnneeRequestDTO(null, "2021/2022", "2022", dateDebut.toDate(), dateFin.toDate(), true, true);
            anneeService.save(a1);

            AnneeRequestDTO a2 = new AnneeRequestDTO(null, "2022/2023", "2023", new DateTime("2022-01-01").toDate(), new DateTime("2022-12-31").toDate(), true, true);
            anneeService.save(a2);

            EvenementRequestDTO e1 = new EvenementRequestDTO(null, null, 1, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Projet 2022");
            e1.setIdEvenement(evenementService.save(e1).getIdEvenement());

            EvenementRequestDTO e2 = new EvenementRequestDTO(null, null, 2, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Anniversaire .... 2022");
            e2.setIdEvenement(evenementService.save(e2).getIdEvenement());

            EvenementRequestDTO e3 = new EvenementRequestDTO(null, null, 3, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Dons ...... 2022");
            e3.setIdEvenement(evenementService.save(e3).getIdEvenement());

            EvenementRequestDTO e4 = new EvenementRequestDTO(null, null, 4, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Felicitations ........ 2022");
            e4.setIdEvenement(evenementService.save(e4).getIdEvenement());

            EvenementRequestDTO e5 = new EvenementRequestDTO(null, null, 5, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Sanctions ...... 2022");
            e5.setIdEvenement(evenementService.save(e5).getIdEvenement());

            SouscriptionRequestDTO s1 = new SouscriptionRequestDTO(null, e1.getIdEvenement(), membre1.getIdMembre(), 5000d, Date.from(Instant.now()), "-");
            souscriptionService.save(s1);

            SouscriptionRequestDTO s2 = new SouscriptionRequestDTO(null, e2.getIdEvenement(), membre2.getIdMembre(), 2500d, Date.from(Instant.now()), "-");
            souscriptionService.save(s2);

            SouscriptionRequestDTO s3 = new SouscriptionRequestDTO(null, e3.getIdEvenement(), membre3.getIdMembre(), 1000d, Date.from(Instant.now()), "-");
            souscriptionService.save(s3);

            SouscriptionRequestDTO s4 = new SouscriptionRequestDTO(null, e4.getIdEvenement(), membre4.getIdMembre(), 3000d, Date.from(Instant.now()), "-");
            souscriptionService.save(s4);

            SouscriptionRequestDTO s5 = new SouscriptionRequestDTO(null, e5.getIdEvenement(), membre5.getIdMembre(), 4000d, Date.from(Instant.now()), "-");
            souscriptionService.save(s5);

            souscriptionService.getAllSouscription().stream().map(s -> {
                        PayementSouscriptionRequestDTO ps = new PayementSouscriptionRequestDTO();
                        ps.setIdSouscription(s.getIdSouscription());
                        ps.setDatePayement(Date.from(Instant.now()));
                        ps.setMontant(s.getMontant());
                        return ps;
                    }).collect(Collectors.toList())
                    .forEach(ps -> {
                        payementSouscriptionService.save(ps);
                    });

            Random r = new Random();

            List<MembreResponseDTO> membres = membreService.getAllMembers();

            evenementService.getAllEvents().forEach(e -> {
                for (int i = 0; i < 5; i++) {
                    Double val = ((r.nextInt(5)) * 3500) + 1000d;
                    ContributionRequestDTO requestDTO = new ContributionRequestDTO();
                    requestDTO.setIdMembre(membres.get(r.nextInt(membres.size())).getIdMembre());
                    requestDTO.setIdEvenement(e.getIdEvenement());
                    requestDTO.setDateContribution(Date.from(Instant.now()));
                    requestDTO.setMontant(val);
                    contributionService.save(requestDTO);
                }
            });


            posteService.save(new PosteRequestDTO(null, "PR", "Président"));
            posteService.save(new PosteRequestDTO(null, "SEC", "Secrétaire"));
            posteService.save(new PosteRequestDTO(null, "TR", "Trésorier"));
            posteService.save(new PosteRequestDTO(null, "CC", "Commissaire aux comptes"));
            posteService.save(new PosteRequestDTO(null, "CE", "Censeur"));
            posteService.save(new PosteRequestDTO(null, "COM", "Communicateur"));

            utilisateurService.save(new UtilisateurRequestDTO(null, "Kenne", "Gervais", "kennegervais@gmail.com", "1234", true, "1234"));
            utilisateurService.save(new UtilisateurRequestDTO(null, "Pouamoun", "Abdel", "pouamounabdel@gmail.com", "1234", true, "1234"));

            periodeService.saveMois(new Mois(null, "Janvier", "Jan", 1));
            periodeService.saveMois(new Mois(null, "Février", "Fév", 2));
            periodeService.saveMois(new Mois(null, "Mars", "Mar", 3));
            periodeService.saveMois(new Mois(null, "Avril", "Avr", 4));
            periodeService.saveMois(new Mois(null, "Mai", "Mai", 5));
            periodeService.saveMois(new Mois(null, "Juin", "Jui", 6));
            periodeService.saveMois(new Mois(null, "Juillet", "Juil", 7));
            periodeService.saveMois(new Mois(null, "Aout", "Sept", 8));
            periodeService.saveMois(new Mois(null, "Septembre", "Sep", 9));
            periodeService.saveMois(new Mois(null, "Oct", "Oct", 10));
            periodeService.saveMois(new Mois(null, "Nov", "Nov", 11));
            periodeService.saveMois(new Mois(null, "Décembre", "Déc", 12));*/
        };
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Acces-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
