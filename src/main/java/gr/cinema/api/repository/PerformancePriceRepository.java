package gr.cinema.api.repository;

import gr.cinema.api.entity.PerformancePrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformancePriceRepository extends JpaRepository<PerformancePrice,Long> {

    List<PerformancePrice> findByPerformanceId(Long id);

}
