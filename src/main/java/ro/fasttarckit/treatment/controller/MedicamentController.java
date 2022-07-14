package ro.fasttarckit.treatment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.fasttarckit.treatment.domain.Medicament;
import ro.fasttarckit.treatment.service.MedicamentService;

@Controller
public class MedicamentController {

    private MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listMedicaments", medicamentService.getAllMedicaments());
        return "index";
    }

    @GetMapping("/showNewMedicamentForm")
    public String showNewMedicamentForm(Model model) {
        //create model attribute to bind from data
        Medicament medicament = new Medicament();
        model.addAttribute("medicament", medicament);
        return "new_medicament";
    }

    @PostMapping("/saveMedicament")
    public String saveMedicament(@ModelAttribute("medicament") Medicament medicament){
        //save medicament to db
        medicamentService.saveMedicament(medicament);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
        //get medicament from service
        Medicament medicament = medicamentService.getMedicamentById(id);
        //set employee as a model attibuteto pre populate the form data

        model.addAttribute("medicament", medicament);
        return "update_medicament";
    }
    @GetMapping("/deleteMedicament/{id}")
    public String deleteMedicament(@PathVariable (value = "id") long id) {
        //call delete medicament method
        this.medicamentService.deleteMedicamentById(id);
        return "redirect:/";
    }
}
