package dem.example.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dem.example.demo2.dto.ScanRequest;
import dem.example.demo2.entity.Operator;
import dem.example.demo2.entity.Product;
import dem.example.demo2.entity.Scan;
import dem.example.demo2.repo.OperatorRepository;
import dem.example.demo2.repo.ProductRepository;
import dem.example.demo2.repo.ScanRepository;
import lombok.RequiredArgsConstructor;


@Service
public class ScanService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OperatorRepository operatorRepo;

    @Autowired
    private ScanRepository scanRepo;

    public Scan scan(ScanRequest req) {

        Operator op = operatorRepo.findById(req.operatorId)
                .orElseThrow(() -> new RuntimeException("Operator not found"));

        Product product = productRepo.findByPartcode(req.partcode)
                .orElseThrow(() -> new RuntimeException("Invalid partcode"));

        if (!product.getType().equals(op.getType())) {
            throw new RuntimeException("Type mismatch");
        }

        String value = op.getType()
                + ":OP" + op.getId()
                + "PR" + product.getId();

        Scan scan = new Scan();
        scan.setOperator(op);
        scan.setProduct(product);
        scan.setType(op.getType());
        scan.setScannedproduct(value);

        return scanRepo.save(scan);
    }
}