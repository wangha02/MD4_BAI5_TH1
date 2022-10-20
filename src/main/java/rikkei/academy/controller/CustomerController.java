package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Customer;
import rikkei.academy.service.ICustomerService;

@Controller
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ModelAndView listCustomers() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customerForm", customer);
        System.out.println("name" + customer.getName());
        return "/customer/add";
    }

    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("customerForm") Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String showFormForAdd(@PathVariable("id") Long id, Model theModel) {
        System.out.println("id ==== " + id);
        Customer theCustomer = customerService.findById(id);
        theModel.addAttribute("customer", theCustomer);
        return "customer/detail";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/delete");
        modelAndView.addObject("deleteForm", customer);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteById(@ModelAttribute("deleteForm") Customer customer) {
        customerService.deleteById(customer.getId());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("editForm", customer);
        return modelAndView;
    }

    @PostMapping("/edit/customer")
    public String editCustomer(@ModelAttribute("editForm") Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }
}
