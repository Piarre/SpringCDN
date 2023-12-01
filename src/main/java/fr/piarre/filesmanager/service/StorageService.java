package fr.piarre.filesmanager.service;

import fr.piarre.filesmanager.model.ImageData;
import fr.piarre.filesmanager.repository.StorageRepository;
import fr.piarre.filesmanager.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(URLEncoder.encode(file.getOriginalFilename(), "UTF-8"))
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        if (imageData != null) {
            return "Image uploaded successfully";
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImgData = storageRepository.findByName(URLDecoder.decode(fileName));
        byte[] image = ImageUtils.decompressImage(dbImgData.get().getImageData());
        return image;
    }

}
