package sahkoinenAloitelaatikko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AloitelaatikkoController {
    private TekijaRepository tekijaRepository;
    private AloiteRepository aloiteRepository;

    public AloitelaatikkoController(TekijaRepository tekijaRepository, AloiteRepository aloiteRepository){
        this.tekijaRepository = tekijaRepository;
        this.aloiteRepository = aloiteRepository;
    }

    @GetMapping("/")
    public String ShowMenu(Model model){
        model.addAttribute("tekijat", this.tekijaRepository.findAll());
        return "index";
    }

    @GetMapping("/addAuthor")
    public  String showAddAuthorForm(){
        return "addAuthor";
    }

    @PostMapping("/addAuthor")
    public String createAuthor(@RequestParam String name,
                               @RequestParam String lastName,
                               @RequestParam String email){
        List<Aloite> aloiteet = new ArrayList<>();
        Tekija tekija = new Tekija(name, lastName, email, aloiteet);

        this.tekijaRepository.save(tekija);

        return "redirect:/addAuthor";
    }

    @GetMapping("/addInitiative")
    public String showAddInitiativeForm(Model model){
        List<Tekija> tekijat = this.tekijaRepository.findAll();
        model.addAttribute("tekijat", tekijat);
        return "addInitiative";
    }

    @PostMapping("/addInitiative")
    public String addInitiative(@RequestParam String description,
                                @RequestParam String pvm,
                                @RequestParam Long id){
        LocalDate localDate = LocalDate.parse(pvm);
        Date date = java.sql.Date.valueOf(localDate);
        Tekija tekija = this.tekijaRepository.getReferenceById(id);
        Aloite aloite = new Aloite(description, date, tekija);
        tekija.getAloiteet().add(aloite);
        this.aloiteRepository.save(aloite);
        this.tekijaRepository.save(tekija);

        return "redirect:/addInitiative";
    }

    @GetMapping("initiativeList")
    public String showInitiatives(Model model){
        model.addAttribute("initiatives", this.aloiteRepository.findByIdNotNull());

        return "initiativeList";
    }
}
