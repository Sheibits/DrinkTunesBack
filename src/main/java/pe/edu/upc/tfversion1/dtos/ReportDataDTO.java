package pe.edu.upc.tfversion1.dtos;

public class ReportDataDTO {
    private String label; // Nombre del rol o categoría
    private Long value; // Número de usuarios o actividad correspondiente

    public ReportDataDTO() {
    }

    public ReportDataDTO(String label, Long value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
