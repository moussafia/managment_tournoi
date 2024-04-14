package ma.youcode.managment_tournoi_backend.util.image;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.exception.ExtensionTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class ImageUtils {
    @Autowired
    private Cloudinary cloudinary;
    @Data
    public class ImageUploadResult {
        public String url;
        public String public_id;
    }
    public  ImageUploadResult saveImageToCloudinary(MultipartFile image, String nameFolder) {
        try{
            Map<Object, Object> options = new HashMap<>();
            options.put("folder", nameFolder);
            Map uploadFile =  cloudinary.uploader().upload(image.getBytes(), options);
            ImageUploadResult imageUploadResult = new ImageUploadResult();
            String public_id = uploadFile.get("public_id").toString();
            imageUploadResult.setPublic_id(public_id);
            imageUploadResult.setUrl(uploadFile.get("url").toString());

            return imageUploadResult;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }
    public void validateImage(MultipartFile image) {
        if(image.isEmpty()) throw new EntityNotFoundException("Image is empty");
        String contentType = image.getContentType();
        if (contentType == null || (!contentType.startsWith("image/jpeg") &&
                !contentType.startsWith("image/png"))){
            throw new ExtensionTypeException("Extension type not supported, it should be JPEG or PNG");
        }

    }
    public void deleteImage(String public_id){
        try{
            cloudinary.uploader().destroy(public_id, Collections.emptyMap());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
