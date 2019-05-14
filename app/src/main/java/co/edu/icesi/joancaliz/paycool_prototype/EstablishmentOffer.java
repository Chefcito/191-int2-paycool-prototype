package co.edu.icesi.joancaliz.paycool_prototype;

public class EstablishmentOffer {
    private String id, offer, description, company;

    public EstablishmentOffer() {

    }

    public EstablishmentOffer(String id, String offer, String description, String company) {
        this.id = id;
        this.offer = offer;
        this.description = description;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
