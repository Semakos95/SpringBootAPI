package gr.cinema.api.repository;

import gr.cinema.api.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
}
