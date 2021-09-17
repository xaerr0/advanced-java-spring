package platform.codingnomads.co.springmvc.customerwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.domain.RentalCar;
import platform.codingnomads.co.springmvc.customerwebsite.service.CarService;
import platform.codingnomads.co.springmvc.customerwebsite.service.CustomerService;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/cars")
    public String viewHomePage(Model model) {
        final List<RentalCar> carList = carService.getCars();
        model.addAttribute("carList", carList);
        return "cars";
    }

    @GetMapping("/new-car")
    public String showNewCarPage(Model model) {
        RentalCar car = new RentalCar();
        model.addAttribute("car", car);
        return "new-car";
    }

    @PostMapping("/cars")
    public String saveCar(@ModelAttribute("car") RentalCar car, Model model) {
        try {
            carService.saveCar(car);
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", "Could not save car, " + e.getMessage());
            return "error-page";
        }
        return "redirect:/cars";
    }

    @RequestMapping("/remove/{id}")
    public String removeCar(@PathVariable(name = "id") Long carId) {
        RentalCar car = carService.getCar(carId);
        car.setCustomer(null);
        carService.saveCar(car);
        return "redirect:/";
    }

    @GetMapping("/assign/{id}")
    public String assignCar(@PathVariable(name = "id") Long id, Model model) {
        Customer customer = customerService.getCustomer(id);
        List<RentalCar> carList = carService.getAvailableCars();
        model.addAttribute("customer", customer);
        model.addAttribute("carList", carList);
        return "assign-car";
    }

    @PostMapping("/assign")
    public String saveCarAssignment(@RequestParam("customerId") Long customerId, @RequestParam("carId") Long carId) {
        RentalCar car = carService.getCar(carId);
        car.setCustomer(customerService.getCustomer(customerId));
        carService.saveCar(car);
        return "redirect:/";
    }
}
