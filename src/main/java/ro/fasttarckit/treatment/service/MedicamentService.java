package ro.fasttarckit.treatment.service;

import ro.fasttarckit.treatment.domain.Medicament;

import java.util.List;

public interface  MedicamentService {
    List<Medicament> getAllMedicaments();
    void saveMedicament(Medicament medicament);
    Medicament getMedicamentById(long id);
    void deleteMedicamentById(long id);
}
