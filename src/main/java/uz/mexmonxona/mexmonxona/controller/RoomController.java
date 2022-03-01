package uz.mexmonxona.mexmonxona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.mexmonxona.mexmonxona.Service.RoomService;
import uz.mexmonxona.mexmonxona.dto.ApiResponse;
import uz.mexmonxona.mexmonxona.dto.RoomDTO;
import uz.mexmonxona.mexmonxona.model.Room;
import uz.mexmonxona.mexmonxona.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    @Autowired
    public RoomController(RoomRepository roomRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    @PostMapping("/add")
    public ApiResponse addRoom(@RequestBody RoomDTO roomDTO){
        return  roomService.addRoom(roomDTO);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse editRoom(@PathVariable Integer id,@RequestBody RoomDTO roomDTO){
        return roomService.editRoom(id, roomDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedRoom(@PathVariable Integer id){
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()){
            roomRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }return new ApiResponse("Deleted eror",false);
    }
    // Hotel id bilan qaytarish
    @GetMapping("/all/{hotelId}")
    public Page<Room> getByRoom(@RequestParam Integer page,@PathVariable Integer hotelId){
        Pageable pageable = PageRequest.of(page,5);
        return roomRepository.findAllByHotelId(hotelId,pageable);
    }
}
