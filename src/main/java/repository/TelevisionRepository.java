package repository;

import model.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TelevisionRepository extends JpaRepository<Television, Long> {



}
