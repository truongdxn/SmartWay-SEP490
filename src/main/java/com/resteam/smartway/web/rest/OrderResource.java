package com.resteam.smartway.web.rest;

import com.resteam.smartway.service.SwOrderService;
import com.resteam.smartway.service.dto.order.DetailAddNoteDTO;
import com.resteam.smartway.service.dto.order.OrderCreationDTO;
import com.resteam.smartway.service.dto.order.OrderDetailDTO;
import com.resteam.smartway.service.dto.order.SwOrderDTO;
import com.resteam.smartway.web.rest.errors.BadRequestAlertException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/orders")
@Transactional
@RequiredArgsConstructor
public class OrderResource {

    private static final String ENTITY_NAME = "order";

    private final SwOrderService swOrderService;

    @PostMapping
    public ResponseEntity<SwOrderDTO> createOrder(@RequestBody OrderCreationDTO orderDTO) {
        SwOrderDTO createdOrder = swOrderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    //    @PostMapping("/add-item")
    //    public ResponseEntity<OrderDetailDTO> addItemToOrder(@RequestBody OrderDetailDTO orderDetailDTO) {
    //        OrderDetailDTO updatedOrderDetail = swOrderService.addItemToOrder(orderDetailDTO);
    //        return ResponseEntity.ok(updatedOrderDetail);
    //    }

    @GetMapping("/{orderId}")
    public ResponseEntity<SwOrderDTO> getOrderById(@PathVariable UUID orderId) {
        SwOrderDTO order = swOrderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<SwOrderDTO> updateOrder(@PathVariable UUID orderId, @RequestBody SwOrderDTO swOrderDTO) {
        SwOrderDTO updatedOrder = swOrderService.updateOrder(orderId, swOrderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        swOrderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/kitchen")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsForKitchen() {
        List<OrderDetailDTO> orderDetails = swOrderService.getUncookedOrderDetailsForKitchen();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsForTable(@PathVariable UUID tableId) {
        List<OrderDetailDTO> orderDetails = swOrderService.getOrderDetailsForTable(tableId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/not-paid")
    public ResponseEntity<List<SwOrderDTO>> loadListOrderNotPaid() {
        List<SwOrderDTO> notPaidOrders = swOrderService.getAllIsPaidFalseOrder();
        return ResponseEntity.ok(notPaidOrders);
    }

    @PutMapping("/add-note")
    public ResponseEntity<OrderDetailDTO> addNote(@RequestBody DetailAddNoteDTO detailAddNoteDTO) {
        OrderDetailDTO updatedOrderDetail = swOrderService.addNote(detailAddNoteDTO);
        return ResponseEntity.ok(updatedOrderDetail);
    }
}
