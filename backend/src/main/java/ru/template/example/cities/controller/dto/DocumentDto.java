package ru.template.example.cities.controller.dto;

public class DocumentDto {
    private Long id;

    private String name;

    private String organization;

    private CodeName status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public CodeName getStatus() {
        return status;
    }

    public void setStatus(CodeName status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
