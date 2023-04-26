package kz.iitu.kidtirp.model.entity.enums;

public enum Gender {
    M("Male"),
    F("Female");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
