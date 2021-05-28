package platform.codingnomads.co.springmvc.customerwebsite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.codingnomads.co.springmvc.customerwebsite.domain.Customer;
import platform.codingnomads.co.springmvc.customerwebsite.service.CustomerService;

import javax.annotation.Nonnull;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {
    @Nonnull private final CustomerService customerService;

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
        return "new_customer";
    }

    @PostMapping(value = "/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_customer");
        Customer customer = customerService.getCustomer(id);
        mav.addObject("customer", customer);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }
}
