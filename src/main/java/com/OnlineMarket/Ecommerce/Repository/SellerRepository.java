package com.OnlineMarket.Ecommerce.Repository;

import com.OnlineMarket.Ecommerce.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    List<Seller> findByPanCard(String panCard);
}
