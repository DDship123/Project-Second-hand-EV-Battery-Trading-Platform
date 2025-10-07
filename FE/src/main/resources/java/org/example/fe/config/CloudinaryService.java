
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
                "cloud_name", "tester",
                "api_key", "881383716774527",
                "api_secret", "UGIu9RhnxDBZWz9FqG9uHqMmWXI"
        ));
    }

    public String uploadImage(MultipartFile file) throws IOException {
        System.out.println("=== CLOUDINARY UPLOAD START ===");

        if (file == null || file.isEmpty()) {
            System.out.println("ERROR: File is null or empty");
            throw new IOException("File is null or empty");
        }

        System.out.println("File details:");
        System.out.println("- Original filename: " + file.getOriginalFilename());
        System.out.println("- Size: " + file.getSize() + " bytes");
        System.out.println("- Content type: " + file.getContentType());

        try {
            byte[] fileBytes = file.getBytes();

            // Determine optimal format based on file type and size
            String originalFormat = getFormatFromContentType(file.getContentType());
            String targetFormat = determineOptimalFormat(file, originalFormat);

            System.out.println("- Original format: " + originalFormat);
            System.out.println("- Target format: " + targetFormat);
            System.out.println("Starting Cloudinary upload...");

            // Build upload parameters dynamically
            Map<String, Object> uploadParams = ObjectUtils.asMap(
                    "folder", "my_app_test_images",
                    "resource_type", "image",
                    "quality", "auto"
            );

            // Add format only if we want to convert
            if (targetFormat != null && !targetFormat.equals(originalFormat)) {
                uploadParams.put("format", targetFormat);
                System.out.println("Converting from " + originalFormat + " to " + targetFormat);
            } else {
                System.out.println("Keeping original format: " + originalFormat);
            }

            // Add additional optimization based on format
            addFormatSpecificParams(uploadParams, targetFormat != null ? targetFormat : originalFormat);

            Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadParams);

            System.out.println("Upload completed successfully!");
            String secureUrl = (String) uploadResult.get("secure_url");
            System.out.println("Secure URL: " + secureUrl);

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

    /**
     * Extract format from content type
     */
    private String getFormatFromContentType(String contentType) {
        if (contentType == null) return "jpg";

        switch (contentType.toLowerCase()) {
            case "image/jpeg":
            case "image/jpg":
                return "jpg";
            case "image/png":
                return "png";
            case "image/webp":
                return "webp";
            case "image/gif":
                return "gif";
            case "image/bmp":
                return "bmp";
            case "image/tiff":
            case "image/tif":
                return "tiff";
            case "image/svg+xml":
                return "svg";
            default:
                return "jpg"; // Default fallback
        }
    }

    /**
     * Determine optimal format based on file characteristics
     */
    private String determineOptimalFormat(MultipartFile file, String originalFormat) {
        long fileSize = file.getSize();
        String filename = file.getOriginalFilename();

        // Keep original format for small files
        if (fileSize < 500 * 1024) { // Less than 500KB
            return originalFormat;
        }

        // Convert large PNG to JPG for better compression (except for images that likely need transparency)
        if ("png".equals(originalFormat) && fileSize > 2 * 1024 * 1024) { // Larger than 2MB
            if (filename != null && (filename.toLowerCase().contains("logo") ||
                                   filename.toLowerCase().contains("icon") ||
                                   filename.toLowerCase().contains("transparent"))) {
                return "png"; // Keep PNG for logos/icons that might need transparency
            }
            return "jpg"; // Convert large PNG to JPG
        }

        // Convert BMP and TIFF to JPG for web optimization
        if ("bmp".equals(originalFormat) || "tiff".equals(originalFormat)) {
            return "jpg";
        }

        // Keep WebP as it's already optimized
        if ("webp".equals(originalFormat)) {
            return "webp";
        }

        // Keep GIF for animations, convert static GIF to JPG
        if ("gif".equals(originalFormat)) {
            return "gif"; // Cloudinary will handle static vs animated automatically
        }

        // Default: keep original format
        return originalFormat;
    }

    /**
     * Add format-specific optimization parameters
     */
    private void addFormatSpecificParams(Map<String, Object> params, String format) {
        switch (format.toLowerCase()) {
            case "jpg":
            case "jpeg":
                params.put("quality", "auto:good"); // Balance between quality and size
                break;
            case "png":
                params.put("quality", "auto:best"); // PNG needs higher quality to maintain transparency
                break;
            case "webp":
                params.put("quality", "auto:good");
                params.put("fetch_format", "auto"); // Let Cloudinary decide best format for client
                break;
            case "gif":
                // Keep original quality for GIFs to preserve animation
                params.put("quality", "auto:best");
                break;
            default:
                params.put("quality", "auto");
                break;
        }

        // Add responsive transformation for all formats
        params.put("width", "auto");
        params.put("dpr", "auto");
        params.put("crop", "limit");
        params.put("max_bytes", "auto");
    }
}
