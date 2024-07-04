package com.example.VCriate_Assignment.controllers;

import model.Inventory;
import model.Order;
import model.Supplier;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PutMapping("/{productId}")
    public Inventory updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        return inventoryService.updateStock(productId, quantity);
    }

    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId) {
        return inventoryService.getInventory(productId);
    }
}

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestParam Long productId, @RequestParam int quantity) {
        return orderService.placeOrder(productId, quantity);
    }

    @PutMapping("/{orderId}")
    public Order fulfillOrder(@PathVariable Long orderId) {
        return orderService.fulfillOrder(orderId);
    }
}

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @GetMapping("/{supplierId}")
    public Supplier getSupplier(@PathVariable Long supplierId) {
        return supplierService.getSupplier(supplierId);
    }
}