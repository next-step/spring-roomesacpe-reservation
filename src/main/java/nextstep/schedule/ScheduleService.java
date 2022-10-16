package nextstep.schedule;

import nextstep.theme.ThemeJdbcRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ThemeJdbcRepository themeJdbcRepository;
    private final ScheduleJdbcRepository scheduleJdbcRepository;

    public ScheduleService(ThemeJdbcRepository themeJdbcRepository, ScheduleJdbcRepository scheduleJdbcRepository) {
        this.themeJdbcRepository = themeJdbcRepository;
        this.scheduleJdbcRepository = scheduleJdbcRepository;
    }

    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {
        if (!(themeJdbcRepository.existById(request.getThemeId()))) {
            throw new IllegalStateException("스케줄 생성 실패: 요청한 테마 ID를 가진 테마가 존재하지 않습니다.");
        }
        if (scheduleJdbcRepository.existsByThemeIdAndDateAndTime(request.getThemeId(), request.getDate(), request.getTime())) {
            throw new IllegalStateException("스케줄 생성 실패: 해당 테마의 해당 일시에 이미 스케줄이 존재합니다.");
        }
        Schedule schedule = scheduleJdbcRepository.save(request.toObject());
        return ScheduleResponse.from(schedule);
    }
}