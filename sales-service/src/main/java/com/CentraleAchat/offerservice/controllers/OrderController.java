package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.entities.Bill;
import com.CentraleAchat.offerservice.entities.Order;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.repositories.OrderRepository;
import com.CentraleAchat.offerservice.services.API.APIUserService;
import com.CentraleAchat.offerservice.services.entities.OrderService;
import com.CentraleAchat.offerservice.services.entities.OrderServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    OrderService orderService;
    APIUserService apiUserService;
    OrderServiceImp orderServiceImp;
    BillRepository billRepository;
    OrderRepository orderRepository;

    @GetMapping("/getBill/{idOrder}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:4200/")
    public Bill getBill(@PathVariable Long idOrder){
       return billRepository.findByOrderrIdOrder(idOrder);
    }

      @PostMapping("/createOrder/{idClient}")
      @ResponseStatus(HttpStatus.CREATED)
    //  @RolesAllowed({"OPERATOR"})
      @CrossOrigin(origins = "http://localhost:4200/")
      public Order createOrder(@RequestBody Order Order, @PathVariable String idClient) {
          return orderService.sendOrder(Order,idClient);
      }

    @PutMapping("/updateOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order updateOrder(@Valid @RequestBody Order order) {
        return orderService.updateOrder(order);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/retrieveAllOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> retrieveAllOrder() {
        return orderService.retrieveAllOrder();
    }
    @CrossOrigin(origins = "http://localhost:4200/")
  //  @RolesAllowed({"CLIENT"})
    @PostMapping("/confirmerOrder/{idOrder}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order confirmerOrder(@PathVariable Long idOrder){
        return orderService.confirmerOrder(idOrder);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
  //  @RolesAllowed({"CLIENT"})
    @PostMapping("/denyOrder/{idOrder}")
    public Order denyOrder(@PathVariable Long idOrder) {
        return    orderService.denyOrder(idOrder);
    }
   // @RolesAllowed({"CLIENT"})
    @PostMapping("/retournerOrder/{idOrder}")
    public Order retournerOrder(@PathVariable Long idOrder) {
          return orderService.retournerOrder(idOrder);
    }

   // @RolesAllowed({"SUPPLIER"})
    @GetMapping("/get")
    public Map<String, Integer> calculateClientProductCounts() {
       return    orderServiceImp.calculateClientProductCounts();
    }
   // @RolesAllowed({"SUPPLIER"})
    @GetMapping("/getByCategory")
    public Map<String, Map<String, Integer>> displayClientProductCounts(){
          return orderServiceImp.displayClientProductCounts();
    }
}
