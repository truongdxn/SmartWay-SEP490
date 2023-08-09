package com.resteam.smartway.service;

import com.itextpdf.text.DocumentException;
import com.resteam.smartway.service.dto.BillDTO;
import com.resteam.smartway.service.dto.order.*;
import com.resteam.smartway.service.dto.order.notification.CancellationDTO;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<BillDTO> loadAllBillWithSort(Instant startDay, Instant endDay, UUID tableId, Pageable pageable);
    OrderDTO createOrder(OrderCreationDTO orderDTO);
    OrderDTO createTakeAwayOrder();

    OrderDTO adjustDetailQuantity(OrderDetailAdjustQuantityDTO dto);

    OrderDTO addOrderDetail(OrderDetailAdditionDTO orderDetailDTO);

    OrderDTO notifyKitchen(UUID orderId);

    List<OrderDTO> getAllActiveOrders();

    OrderDTO deleteOrderDetail(UUID orderDetailId);

    OrderDTO addNoteToOrderDetail(DetailAddNoteDTO dto);

    OrderDTO groupTables(UUID orderId, List<String> ids);

    OrderDTO findById(UUID orderId);

    void ungroupTables(UUID orderId, List<String> tableIds);

    OrderDTO splitOrder(UUID orderId, UUID targetTableId, List<UUID> orderDetailIds);

    OrderDTO changePriority(OrderDetailPriorityDTO orderDetailDTO);

    byte[] generatePdfOrder(OrderDTO orderDTO) throws DocumentException;

    byte[] generatePdfOrderForPay(OrderDTO orderDTO) throws DocumentException;

    OrderDTO cancelOrderDetail(CancellationDTO dto);
}
