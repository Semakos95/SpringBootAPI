package gr.cinema.api.repository;

import gr.cinema.api.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
