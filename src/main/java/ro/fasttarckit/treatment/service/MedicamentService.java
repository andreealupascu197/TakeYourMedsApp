package ro.fasttarckit.treatment.service;

import org.springframework.data.domain.Page;
import ro.fasttarckit.treatment.domain.Medicament;
import ro.fasttarckit.treatment.domain.Usage;

import java.util.List;

public interface  MedicamentService {
    List<Medicament> getAllMedicaments();
    void saveMedicament(Medicament medicament);
    Medicament getMedicamentById(long id);
    void deleteMedicamentById(long id);
    Page<Medicament> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
