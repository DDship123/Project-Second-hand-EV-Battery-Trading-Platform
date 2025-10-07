package org.example.fe.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dn116d6pk",
                "api_key", "881383716774527",
                "api_secret", "UGIu9RhnxDBZWz9FqG9uHqMmWXI"
        ));
    }

    public String uploadImage(MultipartFile file) throws IOException {
//        System.out.println("=== CLOUDINARY UPLOAD START ===");

        if (file == null || file.isEmpty()) {
            System.out.println("ERROR: File is null or empty");
            throw new IOException("File is null or empty");
        }

//        System.out.println("File details:");
//        System.out.println("- Original filename: " + file.getOriginalFilename());
//        System.out.println("- Size: " + file.getSize() + " bytes");
//        System.out.println("- Content type: " + file.getContentType());

        try {
//            System.out.println("Converting file to bytes...");
            byte[] fileBytes = file.getBytes();
//            System.out.println("File converted to bytes successfully. Size: " + fileBytes.length);

//            System.out.println("Starting Cloudinary upload...");

            String imageType = file.getContentType().split("/")[1];
            Map uploadResult = cloudinary.uploader().upload(fileBytes,
                    ObjectUtils.asMap(
                            "folder", "my_app_test_images",
                            "resource_type", "image",
                            "quality", "auto",
                            "format", imageType
                    ));

//            System.out.println("Upload completed successfully!");
//            System.out.println("Upload result keys: " + uploadResult.keySet());

            String secureUrl = (String) uploadResult.get("secure_url");
//            System.out.println("Secure URL: " + secureUrl);

            if (secureUrl == null || secureUrl.isEmpty()) {
                throw new IOException("Cloudinary returned null or empty URL");
            }

            return secureUrl;

        } catch (IOException e) {
            System.out.println("ERROR: IOException during Cloudinary upload: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected exception during Cloudinary upload: " + e.getMessage());
            e.printStackTrace();
            throw new IOException("Cloudinary upload failed: " + e.getMessage(), e);
        }
    }
}
