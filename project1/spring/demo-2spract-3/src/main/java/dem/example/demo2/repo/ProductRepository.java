package dem.example.demo2.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dem.example.demo2.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	 Optional<Product> findByPartcode(String partcode);
	    boolean existsByPartcode(String partcode);
	    List<Product> findByType(String type);
}