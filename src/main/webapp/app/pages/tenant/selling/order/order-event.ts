enum OrderEvent {
  RequestAllActiveOrder = 'request-all-active-orders',
  ReceiveAllActiveOrder = 'receive-all-active-orders',
  CreateOrder = 'create-order',
  ReceiveChangedOrder = 'receive-changed-order',
  AddOrderDetail = 'add-order-detail',
  DeleteOrderDetail = 'delete-order-detail',
  AddNoteOrderDetail = 'add-order-detail',
  NotifyKitchen = 'notify-kitchen',
  AjustDetailQuantity = 'adjust-detail-quantity',
  CancelOrderDetail = 'cancel-order-detail',
}

export default OrderEvent;
