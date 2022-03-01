package uz.mexmonxona.mexmonxona.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.mexmonxona.mexmonxona.Service.HotelService;
import uz.mexmonxona.mexmonxona.dto.ApiResponse;
import uz.mexmonxona.mexmonxona.model.Hotel;
import uz.mexmonxona.mexmonxona.model.Room;
import uz.mexmonxona.mexmonxona.repository.HotelRepository;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelRepository hotelRepository;
    private final HotelService hotelService;

    public HotelController(HotelRepository hotelRepository, HotelService hotelService) {
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
    }

    @PostMapping("/add")
    public ApiResponse addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @GetMapping("/all")
    public Page<Hotel> getHotel(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return hotelRepository.findAll(pageable);
    }

    @PutMapping("/{id}")
    public ApiResponse editHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        return hotelService.edit(id, hotel);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedHotel(@PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            hotelRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        }
        return new ApiResponse("Deleted eror", false);

    }
}
