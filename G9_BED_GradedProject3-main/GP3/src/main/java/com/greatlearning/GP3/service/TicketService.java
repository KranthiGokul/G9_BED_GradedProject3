package com.greatlearning.GP3.service;

import java.util.List;
import java.util.Optional;

import com.greatlearning.GP3.model.Ticket;

public interface TicketService {
    List<Ticket> getAllTickets();

    Optional<Ticket> getTicketById(Long id);

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(Long id);
}
