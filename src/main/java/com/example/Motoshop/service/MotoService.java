package com.example.Motoshop.service;

import com.example.Motoshop.model.Image;
import com.example.Motoshop.model.Moto;
import com.example.Motoshop.model.User;
import com.example.Motoshop.repository.MotoRepository;
import com.example.Motoshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MotoService {
    private final MotoRepository motoRepository;
    private final UserRepository userRepository;

    public List<Moto> showAll(String title){

        if(title != null) {
            return motoRepository.findByTitle(title);
        } else {
            return motoRepository.findAll();
        }
    }
    public void saveMoto(Principal principal, Moto moto, MultipartFile file1 , MultipartFile file2, MultipartFile file3 ) throws IOException {
        moto.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0 ){
            image1 = toImageEntity(file1 );
            image1.setPreviewImage(true);
            moto.addImageToMoto(image1);
        }
        if (file2.getSize() != 0 ){
            image2 = toImageEntity(file2 );
            moto.addImageToMoto(image2);
        }
        if (file3.getSize() != 0 ){
            image3 = toImageEntity(file3 );
            moto.addImageToMoto(image3);
        }

        log.info("Saving new Moto. Title:{} ; Author email: {}" ,moto.getTitle(),moto.getUser().getEmail());
        Moto motoFromDb = motoRepository.save(moto);
        motoFromDb.setPreviewImageId(motoFromDb.getImages().get(0).getId());
        motoRepository.save(moto);

    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }


    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteMoto(Long id ){
        motoRepository.deleteById(id);
    }

    public Moto getMotoById(Long id){
        return motoRepository.findById(id).orElse(null);
    }
}
