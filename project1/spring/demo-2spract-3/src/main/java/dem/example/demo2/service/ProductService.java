package dem.example.demo2.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dem.example.demo2.config.AppConfig.QRConfig;
import dem.example.demo2.dto.ProductResponse;
import dem.example.demo2.entity.Product;
import dem.example.demo2.repo.ProductRepository;
import dem.example.demo2.repo.ScanRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import lombok.RequiredArgsConstructor;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ScanRepository scanRepo;

    @Autowired
    private QRConfig qrConfig;

 

    public void uploadProducts(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        for (Row row : sheet) {

            if (row.getRowNum() == 0) continue; // skip header

            
            String partcode = formatter.formatCellValue(row.getCell(0)).trim();
            String type = formatter.formatCellValue(row.getCell(2)).trim();
            String prodname = formatter.formatCellValue(row.getCell(1)).trim();

            if (prodname.isEmpty()) {
                throw new RuntimeException("Product name empty at row " + (row.getRowNum() + 1));
            }
            
            if (partcode.isEmpty()) {
                throw new RuntimeException("Partcode empty at row " + (row.getRowNum() + 1));
            }

            if (productRepo.existsByPartcode(partcode)) {
                throw new RuntimeException("Duplicate partcode: " + partcode);
            }

            Product p = new Product();
            p.setPartcode(partcode);
            p.setProdname(prodname);
            p.setType(type);
            p.setQrImage(qrConfig.generateQR(partcode));

            productRepo.save(p);
        }
    }

    public List<ProductResponse> getProductsByType(String type) {
        List<Product> products = productRepo.findByType(type);

        return products.stream().map(p -> {
            ProductResponse res = new ProductResponse();
            res.id = p.getId();
            res.partcode = p.getPartcode();
            res.type = p.getType();
            res.prodname = p.getProdname();
            res.qrImage = p.getQrImage();
            res.scanned = scanRepo.existsByProduct(p);
            return res;
        })
        .sorted((a, b) -> Boolean.compare(a.scanned, b.scanned))
        .toList();
    }
}