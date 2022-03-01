package uz.mexmonxona.mexmonxona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mexmonxona.mexmonxona.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    boolean existsByName(String name);

}
