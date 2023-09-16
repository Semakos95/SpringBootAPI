package gr.cinema.api.repository;
import gr.cinema.api.entity.OnlineTicket;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OnlineTicketRepository extends JpaRepository<OnlineTicket,Long> {
}
