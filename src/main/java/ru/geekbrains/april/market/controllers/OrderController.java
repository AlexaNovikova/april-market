package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dtos.OrderDto;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.services.OrderService;
import ru.geekbrains.april.market.services.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
        public void createNewOrder(Principal principal, @RequestParam String telephone, @RequestParam String email,
            @RequestParam String address) {
            User user = userService.findByUsername(principal.getName()).get();
            userService.setContactInfoForUser(user,telephone, email, address);
            orderService.createOrderForCurrentUser(user);
        }


    @GetMapping
    @Transactional
    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
