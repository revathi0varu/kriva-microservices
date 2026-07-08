package com.verizon.ot;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ot")
public class OrderController {

    private final List<Map<String, String>> orders = new ArrayList<>();

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "OT-Backend Provisioning Core");
    }

    @GetMapping("/orders")
    public List<Map<String, String>> getAllOrders() {
        return orders;
    }

    @PostMapping("/orders")
    public Map<String, String> createOrder(@RequestBody Map<String, String> request) {
        Map<String, String> order = Map.of(
            "orderId", "OR-" + (orders.size() + 7001),
            "item", request.getOrDefault("item", "FiOS 1-Gigabit Provisioning"),
            "quantity", request.getOrDefault("quantity", "1"),
            "status", "PENDING_FULFILLMENT"
        );
        orders.add(order);
        return order;
    }
}
