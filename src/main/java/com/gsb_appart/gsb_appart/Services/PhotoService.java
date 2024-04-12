package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Photo.Photo;
import com.gsb_appart.gsb_appart.Repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    @PostMapping
    public Photo addPhoto(@RequestBody Photo photo) {
        return photoRepository.save(photo);
    }


    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Optional<Photo> getPhotoById(Long id) {
        return photoRepository.findById(id);
    }

    public Photo updatePhoto(Long id, Photo photoDetails) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo non trouvée pour cet id :: " + id));
        photo.setUrl(photoDetails.getUrl());

        return photoRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo non trouvée pour cet id :: " + id));
        photoRepository.delete(photo);
    }
}
