package ro.fasttarckit.treatment.service;

import org.springframework.data.domain.Page;
import ro.fasttarckit.treatment.domain.Medicament;
import java.util.List;

public interface  MedicamentService {
    List<Medicament> getAllMedicaments();
    void saveMedicament(Medicament medicament);
    void updateMedicament(Medicament medicament);
    Medicament getMedicamentById(long id);
    void deleteMedicamentById(long id);
    Page<Medicament> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    List<Medicament> generateReport();

}
