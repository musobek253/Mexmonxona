package uz.mexmonxona.mexmonxona.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mexmonxona.mexmonxona.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    boolean existsByNumberAndHotelId(Integer number, Integer hotel_id);
    boolean existsByNumberAndHotelIdNot(Integer number, Integer hotel_id);

    Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);
}
