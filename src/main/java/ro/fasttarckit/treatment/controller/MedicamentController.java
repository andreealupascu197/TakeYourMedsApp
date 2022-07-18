package ro.fasttarckit.treatment.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.fasttarckit.treatment.domain.Medicament;
import ro.fasttarckit.treatment.service.MedicamentService;

import java.util.List;

@Controller
public class MedicamentController {

    private MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
         return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/showNewMedicamentForm")
    public String showNewMedicamentForm(Model model) {
        //create model attribute to bind from data
        Medicament medicament = new Medicament();
        model.addAttribute("medicament", medicament);
        return "new_medicament";
    }

    @PostMapping("/saveMedicament")
    public String saveMedicament(@ModelAttribute("medicament") Medicament medicament) {
        //save medicament to db
        medicamentService.saveMedicament(medicament);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        //get medicament from service
        Medicament medicament = medicamentService.getMedicamentById(id);
        //set employee as a model attibuteto pre populate the form data

        model.addAttribute("medicament", medicament);
        return "update_medicament";
    }

    @GetMapping("/deleteMedicament/{id}")
    public String deleteMedicament(@PathVariable(value = "id") long id) {
        //call delete medicament method
        this.medicamentService.deleteMedicamentById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,
                                 Model model) {
        int pageSize = 5;

        Page<Medicament> page = medicamentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Medicament> listMedicaments = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listMedicaments", listMedicaments);
        return "index";

    }
}
