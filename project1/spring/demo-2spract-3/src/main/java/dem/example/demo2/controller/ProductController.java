package dem.example.demo2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dem.example.demo2.dto.ProductResponse;
import dem.example.demo2.entity.Product;

import dem.example.demo2.service.ProductService;
import lombok.RequiredArgsConstructor;


@RestController

@RequestMapping("/employee2/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        service.uploadProducts(file);
        return "Uploaded successfully";
    }

    @GetMapping("/{type}")
    public List<ProductResponse> getByType(@PathVariable String type) {
        return service.getProductsByType(type);
    }
}
