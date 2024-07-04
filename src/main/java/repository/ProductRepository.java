package repository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
public interface OrderRepository extends JpaRepository<Order, Long> {}
public interface SupplierRepository extends JpaRepository<Supplier, Long> {}
public interface InventoryRepository extends JpaRepository<Inventory, Long> {}