package kz.basqaru.service.domain.image.service;


import kz.basqaru.service.domain.image.model.Image;
import kz.basqaru.service.domain.image.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageService {

    private ImageRepository imageRepository;

    @Transactional
    public String saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        byte[] fileData = file.getBytes();
        Image image = Image.builder().name(fileName).type(fileType).data(fileData).build();
        Image savedImage = imageRepository.save(image);
        return "/image/" + savedImage.getId();
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}