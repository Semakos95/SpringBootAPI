package gr.cinema.api.repository;

import gr.cinema.api.entity.PerformanceStaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PerformanceStagingRepository extends JpaRepository<PerformanceStaging,Long> {

    List<PerformanceStaging> findByPerformanceIdOrderByDate(Long id);
    List<PerformanceStaging> findByPerformanceToStage(boolean toStage);
    List<PerformanceStaging>findByDate(Date date);

    @Query("SELECT p FROM PerformanceStaging p WHERE CAST (p.date AS text) LIKE %:date%")
    List<PerformanceStaging> findByDateContaining(@Param("date") String date);
}
