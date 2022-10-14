package nextstep.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    List<Reservation> findAllByDate(LocalDate date);

    int deleteByDateTime(LocalDate date, LocalTime time);

    boolean existsByDateTime(LocalDate date, LocalTime time);
}
