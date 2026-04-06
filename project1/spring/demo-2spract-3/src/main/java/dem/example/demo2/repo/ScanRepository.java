package dem.example.demo2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dem.example.demo2.entity.Product;
import dem.example.demo2.entity.Scan;

public interface ScanRepository extends JpaRepository<Scan, Long> {
	
	 boolean existsByProduct(Product product);
}