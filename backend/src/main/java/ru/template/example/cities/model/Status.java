package ru.template.example.cities.model;

public enum Status {
    NEW("Новый"), SEND("Отправлен")
    ;
    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
