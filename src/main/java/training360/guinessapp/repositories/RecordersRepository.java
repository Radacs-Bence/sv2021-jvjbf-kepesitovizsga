package training360.guinessapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import training360.guinessapp.entities.Recorder;

import java.util.List;

public interface RecordersRepository extends JpaRepository<Recorder, Long> {

    @Query("SELECT r FROM Recorder r WHERE r.name LIKE 'B%' OR r.name LIKE '%e%' ORDER BY r.dateOfBirth ASC")
    List<Recorder> getCertainRecorders();
}
