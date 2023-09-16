package gr.cinema.api.repository;

import gr.cinema.api.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findByPerformanceIdAndRoomIdAndSectionIdAndDateOrderByDate(Long performanceId, Long roomId, Long sectionId, LocalDate date);


}
