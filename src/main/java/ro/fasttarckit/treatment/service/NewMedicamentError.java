package ro.fasttarckit.treatment.service;

public class NewMedicamentError extends RuntimeException {
    @Override
    public String getMessage() {
        return "Medicament already on the list";
    }
}
