package gr.cinema.api.repository;

import gr.cinema.api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByPerformanceId(Long id);
}
