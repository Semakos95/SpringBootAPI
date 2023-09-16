package gr.cinema.api.repository;

import gr.cinema.api.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent,Long> {

    List<Rent> findByDateAndPeriod(LocalDate date, Long period);

    List<Rent> findByUserIdOrderByDateDesc(Long id);
    List<Rent> findByDate(LocalDate date);


}
