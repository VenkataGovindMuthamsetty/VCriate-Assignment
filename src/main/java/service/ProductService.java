package service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory updateStock(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findById(productId).orElseThrow();
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory getInventory(Long productId) {
        return inventoryRepository.findById(productId).orElseThrow();
    }
}

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryService inventoryService;

    public Order placeOrder(Long productId, int quantity) {
        Inventory inventory = inventoryService.getInventory(productId);
        if (inventory.getQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock available");
        }

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        order.setProduct(new Product(productId));
        inventoryService.updateStock(productId, -quantity);
        return orderRepository.save(order);
    }

    public Order fulfillOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.FULFILLED);
        return orderRepository.save(order);
    }
}

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplier(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow();
    }
}
