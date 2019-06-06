package appp.timsnky.todo_full.repository;

import appp.timsnky.todo_full.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
