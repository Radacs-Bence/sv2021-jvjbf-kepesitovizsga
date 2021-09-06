package training360.guinessapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.guinessapp.entities.WorldRecord;

public interface WorldRecordsRepository extends JpaRepository<WorldRecord, Long> {
}
