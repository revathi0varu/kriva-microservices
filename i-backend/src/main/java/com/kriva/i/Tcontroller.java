package com.verizon.itts;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/itts")
public class TicketController {

    private final List<Map<String, String>> tickets = new ArrayList<>();

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "ITTS-Backend Network Core");
    }

    @GetMapping("/tickets")
    public List<Map<String, String>> getAllTickets() {
        return tickets;
    }

    @PostMapping("/tickets")
    public Map<String, String> createTicket(@RequestBody Map<String, String> request) {
        Map<String, String> ticket = Map.of(
            "ticketId", "TK-" + (tickets.size() + 1001),
            "issue", request.getOrDefault("issue", "Cell Tower Degradation"),
            "severity", request.getOrDefault("severity", "P2"),
            "status", "OPEN"
        );
        tickets.add(ticket);
        return ticket;
    }
}
