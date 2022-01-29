package org.kenne.noudybaapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.kenne.noudybaapi.dto.*;
import org.kenne.noudybaapi.enumeration.RubricType;
import org.kenne.noudybaapi.service.declaration.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication
@AllArgsConstructor
public class NoudybaApiApplication implements CommandLineRunner {

    private VilleService villeService;
    private MembreService membreService;
    private RubriqueService rubriqueService;
    private AnneeService anneeService;
    private EvenementService evenementService;
    private SouscriptionService souscriptionService;
    private PayementSouscriptionService payementSouscriptionService;
    private ContributionService contributionService;
    private PosteService posteService;

    public static void main(String[] args) {
        SpringApplication.run(NoudybaApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        VilleRequestDTO ville1 = new VilleRequestDTO(null, "Bafoussam");
        VilleRequestDTO ville2 = new VilleRequestDTO(null, "Douala");
        VilleRequestDTO ville3 = new VilleRequestDTO(null, "Yaoundé");
        VilleRequestDTO ville4 = new VilleRequestDTO(null, "Paris");
        VilleRequestDTO ville5 = new VilleRequestDTO(null, "Limbé");

        villeService.save(ville1);
        villeService.save(ville2);
        villeService.save(ville3);
        villeService.save(ville4);
        villeService.save(ville5);

        List<VilleResponseDTO> listVilles = villeService.findAll();

        MembreRequestDTO membre1 = new MembreRequestDTO(null, "Diffo", "Rodrigue Martial", "674949013", true, listVilles.get(new Random().nextInt(listVilles.size())).getIdVille());;
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

        RubriqueRequestDTO r1 = new RubriqueRequestDTO(null, "Projet", "R001" , RubricType.NON_REPEAT);
        rubriqueService.save(r1);

        RubriqueRequestDTO r2 = new RubriqueRequestDTO(null, "Anniversaire", "R002" , RubricType.REPEAT);
        rubriqueService.save(r2);

        RubriqueRequestDTO r3 = new RubriqueRequestDTO(null, "Dons", "R003" , RubricType.REPEAT);
        rubriqueService.save(r3);

        RubriqueRequestDTO r4 = new RubriqueRequestDTO(null, "Félicitations", "R004" , RubricType.REPEAT);
        rubriqueService.save(r4);

        RubriqueRequestDTO r5 = new RubriqueRequestDTO(null, "Sanctions", "R005" , RubricType.REPEAT);
        rubriqueService.save(r5);

        DateTime dateDebut = new DateTime("2021-01-01");
        DateTime dateFin = new DateTime("2021-12-31");
        AnneeRequestDTO a1 = new AnneeRequestDTO(null, "2021/2022", "2022", dateDebut.toDate(), dateFin.toDate(), true, true);
        anneeService.save(a1);

        AnneeRequestDTO a2 = new AnneeRequestDTO(null, "2022/2023", "2023", new DateTime("2022-01-01").toDate(), new DateTime("2022-12-31").toDate(), true, true);
        anneeService.save(a2);

        EvenementRequestDTO e1 = new EvenementRequestDTO(null, null, 1, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Projet 2022");
        e1.setIdEvenement(evenementService.save(e1).getIdEvenement());

        EvenementRequestDTO e2 = new EvenementRequestDTO(null,null, 2, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Anniversaire .... 2022");
        e2.setIdEvenement(evenementService.save(e2).getIdEvenement());

        EvenementRequestDTO e3 = new EvenementRequestDTO(null,null, 3, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Dons ...... 2022");
        e3.setIdEvenement(evenementService.save(e3).getIdEvenement());

        EvenementRequestDTO e4 = new EvenementRequestDTO(null,null,  4, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Felicitations ........ 2022");
        e4.setIdEvenement(evenementService.save(e4).getIdEvenement());

        EvenementRequestDTO e5 = new EvenementRequestDTO(null,null, 5, 2, Date.from(Instant.now()), Date.from(Instant.now()), "Sanctions ...... 2022");
        e5.setIdEvenement(evenementService.save(e5).getIdEvenement());

        SouscriptionRequestDTO s1 = new SouscriptionRequestDTO(null, e1.getIdEvenement(), membre1.getIdMembre(), 5000d, Date.from(Instant.now()));
        souscriptionService.save(s1);

        SouscriptionRequestDTO s2 = new SouscriptionRequestDTO(null, e2.getIdEvenement(), membre2.getIdMembre(), 2500d, Date.from(Instant.now()));
        souscriptionService.save(s2);

        SouscriptionRequestDTO s3 = new SouscriptionRequestDTO(null, e3.getIdEvenement(), membre3.getIdMembre(), 1000d, Date.from(Instant.now()));
        souscriptionService.save(s3);

        SouscriptionRequestDTO s4 = new SouscriptionRequestDTO(null, e4.getIdEvenement(), membre4.getIdMembre(), 3000d, Date.from(Instant.now()));
        souscriptionService.save(s4);

        SouscriptionRequestDTO s5 = new SouscriptionRequestDTO(null, e5.getIdEvenement(), membre5.getIdMembre(), 4000d, Date.from(Instant.now()));
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
                Double val = ((r.nextInt(5) + 1) * 3500) + 1000d;
                ContributionRequestDTO requestDTO = new ContributionRequestDTO();
                requestDTO.setIdMembre(membres.get(r.nextInt(membres.size())).getIdMembre());
                requestDTO.setIdEvenement(e.getIdEvenement());
                requestDTO.setDateContribution(Date.from(Instant.now()));
                requestDTO.setMontant(val);
                contributionService.save(requestDTO);
            }
        });

        PosteRequestDTO p1 = new PosteRequestDTO(null, "PR", "Président");
        posteService.save(p1);

        p1 = new PosteRequestDTO(null, "SEC", "Secrétaire");
        posteService.save(p1);

        p1 = new PosteRequestDTO(null, "TR", "Trésorier");
        posteService.save(p1);

        p1 = new PosteRequestDTO(null, "CC", "Commissaire aux comptes");
        posteService.save(p1);

        p1 = new PosteRequestDTO(null, "CE", "Censeur");
        posteService.save(p1);

        p1 = new PosteRequestDTO(null, "COM", "Communicateur");
        posteService.save(p1);
    }

    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        Info info = new Info();
        info.title("NOUDYBA API")
                .description("API FOR NOUDYBA ASSOCIATION MANAGEMENT PROCESS")
                .version("V1.0.0")
                .contact(new Contact()
                        .email("kennegervais@gmail.com")
                        .name("Kenne Gervais")
                        .url("http://lolhost:8050/"));
        return info;
    }

}
