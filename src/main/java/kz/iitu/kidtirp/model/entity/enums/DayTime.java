package kz.iitu.kidtirp.model.entity.enums;

public enum DayTime {
    MORNING(1),
    AFTERNOON(2),
    EVENING(3);

    private final Integer value;

    DayTime(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
