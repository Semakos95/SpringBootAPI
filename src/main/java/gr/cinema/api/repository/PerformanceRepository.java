package gr.cinema.api.repository;

import gr.cinema.api.entity.Performance;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance,Long> {
    List<Performance> findByTitle(String title);
    List<Performance> findByUserId(Long id);
    List<Performance> findByToStage(boolean toStage);

    @Query("SELECT p FROM Performance p WHERE p.title LIKE %:keyword%")
    List<Performance> findByTitleContaining(@Param("keyword") String keyword);

    @Query(value = "select distinct perf.* from performance perf, performance_staging perfStag " +
            "where perfStag.date = (:date)"+
            "and perf.id = perfStag.performance_id order by perfStag.date", nativeQuery = true)
    List<Performance> findAllStagingPerformanceByDate(Date date);


}
