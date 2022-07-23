package ro.fasttarckit.treatment.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.fasttarckit.treatment.domain.Medicament;
import ro.fasttarckit.treatment.domain.TimeOfDay;
import ro.fasttarckit.treatment.domain.Type;
import ro.fasttarckit.treatment.domain.Usage;
import ro.fasttarckit.treatment.dto.MedicamentDTO;
import ro.fasttarckit.treatment.service.MedicamentService;
import ro.fasttarckit.treatment.service.NewMedicamentError;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MedicamentAPIController {
    private MedicamentService medicamentService;

    public MedicamentAPIController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }



    @PostMapping("/medicaments")
    public String saveMedicament(
            @RequestParam("name") String name,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("price") Double price,
            @RequestParam("expiryDate") Date expiryDate,
            @RequestParam("usage")Usage usage,
            @RequestParam("type") Type type,
            @RequestParam("frequency") Integer frequency,
            @RequestParam("timeOfDay")TimeOfDay timeOfDay,
            @RequestParam("beforeEating") Boolean beforeEating
            ) {
        try {
            Medicament medicament = new Medicament();
            medicament.setName(name);
            medicament.setQuantity(quantity);
            medicament.setPrice(price);
            medicament.setExpiryDate(expiryDate);
            medicament.setUsage(usage);
            medicament.setType(type);
            medicament.setFrequency(frequency);
            medicament.setTimeOfDay(timeOfDay);
            medicament.setBeforeEating(beforeEating);
            medicamentService.saveMedicament(medicament);

            return "Successfully added";
        } catch (NewMedicamentError e) {
            return "Could not be added";
        }
    }
    @PutMapping("/medicaments/{id}")
    public String updateMedicament(
            @PathVariable(value = "id") long id,
            @RequestParam("name") String name,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("price") Double price,
            @RequestParam("expiryDate") Date expiryDate,
            @RequestParam("usage")Usage usage,
            @RequestParam("type") Type type,
            @RequestParam("frequency") Integer frequency,
            @RequestParam("timeOfDay")TimeOfDay timeOfDay,
            @RequestParam("beforeEating") Boolean beforeEating
    ) {
        try {
            Medicament medicament = new Medicament();
            medicament.setId(id);
            medicament.setName(name);
            medicament.setQuantity(quantity);
            medicament.setPrice(price);
            medicament.setExpiryDate(expiryDate);
            medicament.setUsage(usage);
            medicament.setType(type);
            medicament.setFrequency(frequency);
            medicament.setTimeOfDay(timeOfDay);
            medicament.setBeforeEating(beforeEating);

            medicamentService.updateMedicament(medicament);
            return "Successfully updated";
        } catch (NewMedicamentError e) {
            return "Cannot update";
        }
    }

    @DeleteMapping("/medicaments/{id}")
    public String deleteMedicament(
            @PathVariable(value = "id") long id
    ) {
        Medicament medicament = new Medicament();
        medicament.setId(id);
        medicamentService.deleteMedicamentById(id);
        return "Medicament was deleted";
    }

    @GetMapping("/medicaments/{pageNo}")
    public List<MedicamentDTO> findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                          @RequestParam("sortField") String sortField,
                                          @RequestParam("sortDir") String sortDir) {
        int pageSize = 5;

        Page<Medicament> page = medicamentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        return page.getContent().stream()
                .map(MedicamentDTO::new)
                .collect(Collectors.toList());

    }

    @GetMapping("/medicaments/report")
    public List<MedicamentDTO> generateReport() {
        return medicamentService.generateReport().stream()
                .map(MedicamentDTO::new)
                .collect(Collectors.toList());

    }
}
