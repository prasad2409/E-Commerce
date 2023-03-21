package com.OnlineMarket.Ecommerce.Repository;

import com.OnlineMarket.Ecommerce.Enum.ProductCategory;
import com.OnlineMarket.Ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByProductCategory(ProductCategory productCategory);
}
