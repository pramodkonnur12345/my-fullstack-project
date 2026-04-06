package dem.example.demo2.config;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    /**
     * Enable CORS for frontend (Angular)
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")   // allow all for interview/demo
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
    
    @Configuration
    public class QRConfig {

        public byte[] generateQR(String text) {
            try {
                BitMatrix matrix = new MultiFormatWriter()
                        .encode(text, BarcodeFormat.QR_CODE, 200, 200);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(matrix, "PNG", baos);

                return baos.toByteArray();

            } catch (Exception e) {
                throw new RuntimeException("QR generation failed");
            }
        }
    }
    
    
}