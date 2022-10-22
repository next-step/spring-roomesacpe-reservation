package nextstep.domain.schedule.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import nextstep.domain.schedule.ScheduleEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcScheduleRepository implements ScheduleRepository {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<ScheduleEntity> rowMapper = (resultSet, rowNum) -> {
    var entity = ScheduleEntity.builder()
        .id(resultSet.getLong("id"))
        .themeId(resultSet.getLong("theme_id"))
        .date(resultSet.getObject("date", LocalDate.class))
        .time(resultSet.getObject("time", LocalTime.class))
        .build();
    return entity;
  };

  @Override
  public ScheduleEntity save(ScheduleEntity schedule) {
    var keyHolder = new GeneratedKeyHolder();
    var sql = "insert into schedule (theme_id, date, time) values (?, ?, ?)";
    jdbcTemplate.update(con -> {
      var ps = con.prepareStatement(sql, new String[]{"id"});
      ps.setLong(1, schedule.getThemeId());
      ps.setString(2, schedule.getDate().toString());
      ps.setString(3, schedule.getTime().toString());
      return ps;
    }, keyHolder);
    return schedule.toBuilder()
        .id(keyHolder.getKey().longValue())
        .build();
  }
}
