package uz.akbarali.foodappjavav8.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.projection.IdProjection;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;


public interface AnswerService {

    HttpEntity<ApiResponse> answer(String message, boolean isSuccess, Object data, HttpStatus status);

    public <K extends JpaRepository<O, UUID>, O extends AbsEntity> HttpEntity<?> saveObject(K repository, O object, boolean returnObject, List<O> listObject);

    public <K extends JpaRepository<O, UUID>, O extends AbsEntity> HttpEntity<?> deleteObject(K repository, UUID id);

    ResponseEntity<ApiResponse> getError(Errors errors);

    public <K extends BaseRepository<O>, O extends AbsEntity , P extends IdProjection> HttpEntity<ApiResponse> getAllObject(K repository);

}
