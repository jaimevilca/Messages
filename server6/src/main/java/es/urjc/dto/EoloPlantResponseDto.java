package es.urjc.dto;

public class EoloPlantResponseDto {
    private Long id;
    private String city;
    private String planning;

    public EoloPlantResponseDto(Long id, String city, String planning) {
        this.id = id;
        this.city = city;
        this.planning = planning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }
}
