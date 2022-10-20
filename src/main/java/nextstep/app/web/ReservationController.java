package nextstep.app.web;

import nextstep.app.web.dto.ReservationCreateRequest;
import nextstep.app.web.dto.ReservationResponse;
import nextstep.core.Reservation;
import nextstep.core.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequestMapping("/reservations")
@RestController
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReservationCreateRequest request) {
        Reservation reservation = service.save(request.to());
        return ResponseEntity
                .created(URI.create("/reservations/" + reservation.getId()))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> list(@RequestParam String date) {
        List<Reservation> reservations = service.findAllByDate(LocalDate.parse(date));
        return ResponseEntity.ok(
                reservations.stream()
                        .map(ReservationResponse::from)
                        .toList()
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String date, @RequestParam String time) {
        service.deleteByDateAndTime(LocalDate.parse(date), LocalTime.parse(time));
        return ResponseEntity.noContent().build();
    }
}
