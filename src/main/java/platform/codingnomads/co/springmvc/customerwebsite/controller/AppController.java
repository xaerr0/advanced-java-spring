package platform.codingnomads.co.springmvc.customerwebsite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.domain.RentalCar;
import platform.codingnomads.co.springmvc.customerwebsite.service.CarService;
import platform.codingnomads.co.springmvc.customerwebsite.service.CustomerService;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AppController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    CarService carService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        final List<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customerList", customerList);
        return "index";
    }

    @GetMapping("/new")
    public String showNewCustomerPage(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new-customer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            customerService.saveCustomer(customer);
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", "Could not save customer, " + e.getMessage());
            return "error-page";
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-customer");
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            mav.setViewName("error-page");
            mav.addObject("message", "Customer with id " + id + " does not exist.");
        } else {
            mav.addObject("customer", customer);
        }
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable(name = "id") Long id, @ModelAttribute("customer") Customer customer, Model model) {
        if (!id.equals(customer.getId())) {
            model.addAttribute("message",
                    "Cannot update, customer id " + customer.getId()
                            + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long customerId, Model model) {
        if (customerService.getCustomer(customerId) == null) {
            model.addAttribute("message",
                    "Cannot delete, customer with id " + customerId + " does not exist.");
            return "error-page";
        }
        carService.removeAssignment(customerId);
        customerService.deleteCustomer(customerId);
        return "redirect:/";
    }

}
