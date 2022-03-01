package uz.mexmonxona.mexmonxona.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.mexmonxona.mexmonxona.dto.ApiResponse;
import uz.mexmonxona.mexmonxona.model.Hotel;
import uz.mexmonxona.mexmonxona.repository.HotelRepository;

import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public ApiResponse addHotel( Hotel hotel){
        if (hotelRepository.existsByName(hotel.getName()))
            return  new ApiResponse("Already exist",false);
        hotelRepository.save(hotel);
        return new ApiResponse("Successfully added",true);
    }

    public ApiResponse edit(Integer id, Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return new ApiResponse("Hotel not found",false);
        if (hotelRepository.existsByName(hotel.getName()))
            return new ApiResponse("Already exist hotel",false);
        Hotel hotel1 = optionalHotel.get();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return new ApiResponse("Successfully edited",true);

    }
}
