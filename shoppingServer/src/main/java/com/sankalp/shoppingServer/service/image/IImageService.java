package com.sankalp.shoppingServer.service.image;

import com.sankalp.shoppingServer.dto.ImageDto;
import com.sankalp.shoppingServer.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id );
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);

}
