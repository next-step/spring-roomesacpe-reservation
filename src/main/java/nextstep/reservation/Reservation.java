package nextstep.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String name;

    public Reservation(Long id, LocalDate date, LocalTime time, String name) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
