package gr.cinema.api.repository;

import gr.cinema.api.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends JpaRepository<Section,Long> {

    Section findByNameAndRoomId(String name, Long id);
}
