package workshopPizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import workshopPizza.model.Delivery;
import workshopPizza.model.Order;
import workshopPizza.model.Pizza;
import workshopPizza.service.OrderService;

@Controller
// @RequestMapping
public class PizzaController {
    @Autowired
    OrderService service;
    
    @GetMapping
    public String orderPage(Model model, HttpSession session) {
        Order o = (Order) session.getAttribute("order");
        if (o == null) {
            o = new Order();
        }

        Pizza p = (Pizza) session.getAttribute("pizza");
        if (p == null) {
            p = new Pizza();
        }

        Delivery d = (Delivery) session.getAttribute("delivery");
        if (d == null) {
            d = new Delivery();
        }

        model.addAttribute("order", o);
        model.addAttribute("pizza", p);
        model.addAttribute("delivery", d);
        return "index";
    }

    @PostMapping(path="/pizza", consumes="application/x-www-form-urlencoded")
    public String getPizza(Model model, Pizza pizza, HttpSession session) {
        System.out.println(pizza.getPizza());
        // model.addAttribute("pizza", pizza.getPizza());
        // model.addAttribute("size", pizza.getSize());
        // model.addAttribute("quantity", pizza.getQuantity());
        model.addAttribute("pizza", new Pizza(pizza.getPizza(), 
                        pizza.getSize(), pizza.getQuantity()));
        session.setAttribute("pizza", new Pizza(pizza.getPizza(), 
        pizza.getSize(), pizza.getQuantity()));

        return "details";
    }

    @PostMapping(path = "/pizza/order", consumes="application/x-www-form-urlencoded")
    public String getDelivery(Model model, Delivery delivery, HttpSession session) {
        System.out.println(delivery.getName());
        System.out.println(delivery.getAddress());
        Pizza p = (Pizza) session.getAttribute("pizza");
        System.out.println(p.getPizza());
        Delivery d = new Delivery(delivery.getName(), 
        delivery.getAddress(),delivery.getPhone(),delivery.isRush(),delivery.getComments());
        model.addAttribute("delivery", d);

        Order o =  new Order(p, d);
        o = service.calculateCost(o);
        model.addAttribute("order", o);
        session.invalidate();
        return "confirm";
    }


}
