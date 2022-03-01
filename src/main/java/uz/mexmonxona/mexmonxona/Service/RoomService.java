package uz.mexmonxona.mexmonxona.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mexmonxona.mexmonxona.dto.ApiResponse;
import uz.mexmonxona.mexmonxona.dto.RoomDTO;
import uz.mexmonxona.mexmonxona.model.Room;
import uz.mexmonxona.mexmonxona.repository.HotelRepository;
import uz.mexmonxona.mexmonxona.repository.RoomRepository;

import java.util.Optional;

@Service
public class RoomService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    @Autowired
    public RoomService(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public ApiResponse addRoom(RoomDTO roomDTO){
        if (!hotelRepository.findById(roomDTO.getHotelId()).isPresent())
            return new ApiResponse("Hotel not found",false);
        if (roomRepository.existsByNumberAndHotelId(roomDTO.getNumber(),roomDTO.getHotelId()))
            return new ApiResponse("Already exist",false);

        Room room = new Room();
        room.setHotel(hotelRepository.findById(roomDTO.getHotelId()).get());
        room.setFloor(roomDTO.getFloor());
        room.setNumber(roomDTO.getNumber());
        room.setSize(roomDTO.getSize());
        roomRepository.save(room);
        return new ApiResponse("Successfully added",true);

    }
    public ApiResponse editRoom(Integer id,RoomDTO roomDTO){

        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return new ApiResponse("Room not found",false);
        if (!hotelRepository.findById(roomDTO.getHotelId()).isPresent())
            return new ApiResponse("Hotel not found",false);
        if (roomRepository.existsByNumberAndHotelIdNot(roomDTO.getNumber(),roomDTO.getHotelId()))
            return new ApiResponse("Already exist",false);
        Room room = optionalRoom.get();
        room.setHotel(hotelRepository.findById(roomDTO.getHotelId()).get());
        room.setFloor(roomDTO.getFloor());
        room.setNumber(roomDTO.getNumber());
        room.setSize(roomDTO.getSize());
        roomRepository.save(room);
        return new ApiResponse("Successfully edited",true);
    }
}
