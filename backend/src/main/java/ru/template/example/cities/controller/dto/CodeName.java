package ru.template.example.cities.controller.dto;

public class CodeName {
    private String code;
    private String name;

    public static CodeName of(String code, String name) {
        CodeName codeName = new CodeName();
        codeName.setCode(code);
        codeName.setName(name);
        return codeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
