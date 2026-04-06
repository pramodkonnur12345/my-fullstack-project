package dem.example.demo2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dem.example.demo2.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByOpname(String opname);
    Optional<Operator> findByLoginToken(String token);
}