package nextstep.application.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record ReservationRes(Long id, LocalDate date, String time, String name) {

}
