package ru.template.example.cities.controller.dto;

import java.util.Set;

public class IdsDto {

    private Set<Long> ids;

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }
}