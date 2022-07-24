package ro.fasttarckit.treatment.dto;
import ro.fasttarckit.treatment.domain.Medicament;
import ro.fasttarckit.treatment.domain.TimeOfDay;
import ro.fasttarckit.treatment.domain.Type;
import ro.fasttarckit.treatment.domain.Usage;
import java.sql.Date;


public record MedicamentDTO(
        String name,
        Integer quantity,
        Double price,
        Date expiryDate,
        Usage usage,
        Type type,
        Integer frequency,
        TimeOfDay timeOfDay,
        Boolean beforeEating
) {

    public MedicamentDTO(Medicament medicament) {
        this(medicament.getName(),
                medicament.getQuantity(),
                medicament.getPrice(),
                medicament.getExpiryDate(),
                medicament.getUsage(),
                medicament.getType(),
                medicament.getFrequency(),
                medicament.getTimeOfDay(),
                medicament.getBeforeEating()
        );
    }
}
