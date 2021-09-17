package platform.codingnomads.co.springmvc.customerwebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.domain.RentalCar;
import platform.codingnomads.co.springmvc.customerwebsite.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<RentalCar> getCars() {
        return carRepository.findAll();
    }

    public List<RentalCar> getAvailableCars() {
        return getCars().stream().filter(c -> c.getCustomer() == null)
                .collect(Collectors.toList());
    }

    public RentalCar getCar(Long id) {
        return carRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    public RentalCar saveCar(RentalCar car) throws IllegalArgumentException {
        return carRepository.save(car);
    }

    public void removeAssignment(Long customerId) {
        RentalCar car = carRepository.findByCustomerId(customerId);
        if (car != null) {
            car.setCustomer(null);
            saveCar(car);
        }
    }


}
