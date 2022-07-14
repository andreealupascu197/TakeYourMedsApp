package ro.fasttarckit.treatment.service;

import org.springframework.stereotype.Service;
import ro.fasttarckit.treatment.domain.Medicament;
import ro.fasttarckit.treatment.repository.MedicamentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MedicamentServiceImplementation implements MedicamentService{


    private MedicamentRepository medicamentRepository;

    public MedicamentServiceImplementation(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;

    }

    @Override
   public List<Medicament> getAllMedicaments() {
        return medicamentRepository.findAll();
    }

    @Override
    public void saveMedicament(Medicament medicament) {
        this.medicamentRepository.save(medicament);
    }

    @Override
    public Medicament getMedicamentById(long id) {
        Optional<Medicament> optional = medicamentRepository.findById(id);
        Medicament medicament = null;
        if(optional.isPresent()) {
            medicament = optional.get();
        } else {
            throw  new RuntimeException("Medicament not found for id: " + id);
        }
        return medicament;
    }

    @Override
    public void deleteMedicamentById(long id) {
        this.medicamentRepository.deleteById(id);
    }
}
