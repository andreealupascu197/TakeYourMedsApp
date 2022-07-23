package ro.fasttarckit.treatment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.fasttarckit.treatment.domain.Medicament;
import ro.fasttarckit.treatment.domain.TimeOfDay;
import ro.fasttarckit.treatment.repository.MedicamentRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Service
public class MedicamentServiceImplementation implements MedicamentService {


    private MedicamentRepository medicamentRepository;

    public MedicamentServiceImplementation(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;

    }

    @Override
    public List<Medicament> getAllMedicaments() {
        return medicamentRepository.findAll();
    }

    @Override
    public void saveMedicament(Medicament newMedicament) {
        for (Medicament medicament : getAllMedicaments()) {
            if (medicament.getName().equals(newMedicament.getName())
                    && medicament.getType().equals(newMedicament.getType())) {
                throw new NewMedicamentError();
            }
        }
        medicamentRepository.save(newMedicament);

    }

    @Override
    public void updateMedicament(Medicament updatedMedicament) {
        for (Medicament medicament : getAllMedicaments()) {
            if (medicament.getName().equals(updatedMedicament.getName())
                    && medicament.getType().equals(updatedMedicament.getType())
                    && medicament.getId() != updatedMedicament.getId()) {
                throw new NewMedicamentError();
            }
        }
        medicamentRepository.save(updatedMedicament);
    }

    @Override
    public Medicament getMedicamentById(long id) {
        return medicamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Medicament not found for id: " + id));
    }

    @Override
    public void deleteMedicamentById(long id) {
        this.medicamentRepository.deleteById(id);
    }

    @Override
    public Page<Medicament> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.medicamentRepository.findAll(pageable);
    }

    @Override
    public List<Medicament> generateReport() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int localTime = localDateTime.toLocalTime().getHour();
        if (localTime < 12) {
            return medicamentRepository.findAll().stream()
                    .sorted(Comparator.comparing(Medicament::getTimeOfDay)
                            .thenComparing(Medicament::getBeforeEating))
                    .collect(Collectors.toList());


        } else if (localTime < 18) {
            return medicamentRepository.findAll().stream()
                    .filter(m -> m.getTimeOfDay().equals(TimeOfDay.AFTERNOON)
                            || m.getTimeOfDay().equals(TimeOfDay.MORNING_AND_AFTERNOON)
                            || m.getTimeOfDay().equals(TimeOfDay.AFTERNOON_AND_EVENING)
                            || m.getTimeOfDay().equals(TimeOfDay.EVENING)
                            || m.getTimeOfDay().equals(TimeOfDay.ALL))
                    .sorted(Comparator.comparing(Medicament::getTimeOfDay)
                            .thenComparing(Medicament::getBeforeEating))
                    .collect(toList());
        } else {
            return medicamentRepository.findAll().stream().
                    filter(m -> m.getTimeOfDay().equals(TimeOfDay.EVENING)
                            || m.getTimeOfDay().equals(TimeOfDay.AFTERNOON_AND_EVENING)
                            || m.getTimeOfDay().equals(TimeOfDay.ALL))
                    .sorted(Comparator.comparing(Medicament::getTimeOfDay)
                            .thenComparing(Medicament::getBeforeEating))
                    .collect(toList());
        }
    }


}
