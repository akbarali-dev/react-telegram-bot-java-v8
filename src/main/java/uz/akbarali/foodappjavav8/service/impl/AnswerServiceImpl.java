package uz.akbarali.foodappjavav8.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.projection.IdProjection;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;
import uz.akbarali.foodappjavav8.service.AnswerService;

import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

    final
    BaseRepository<?> baseRepository;

    public AnswerServiceImpl(BaseRepository<?> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public HttpEntity<ApiResponse> answer(String message, boolean isSuccess, Object data, HttpStatus status) {
        return ResponseEntity.status(
                        status)
                .body(
                        new ApiResponse(
                                message,
                                isSuccess,
                                data
                        ));
    }

    @Override
    public ResponseEntity<ApiResponse> getError(Errors errors) {
        Map<String, List<String>> error = new HashMap<>();
        if (errors.hasErrors()) {
            for (FieldError fieldError : errors.getFieldErrors()) {
                if (!error.containsKey(fieldError.getField())) {
                    error.put(fieldError.getField(),
                            new ArrayList<>(Collections.singletonList(fieldError.getDefaultMessage())));
                } else {
                    error.get(fieldError.getField()).add(fieldError.getDefaultMessage());
                }
            }

            return ResponseEntity.status(
                    HttpStatus.CONFLICT
            ).body(
                    new ApiResponse(
                            "ERROR",
                            false,
                            null,
                            error
                    ));
        }
        return null;
    }



    // TODO: 5/30   /2022 all object saved
    @Override
    public <K extends JpaRepository<O, UUID>, O extends AbsEntity> HttpEntity<?> saveObject(
            K repository, O object, boolean returnObject, List<O> listObject) {
        try {
            if (listObject == null) {
                O savedObject = repository.save(object);
                if (returnObject) {
                    return answer("SUCCESS", true, savedObject, HttpStatus.OK);
                }
            } else if (object == null) {
                List<O> savedObject = repository.saveAll(listObject);
                if (returnObject) {
                    return answer("SUCCESS", true, savedObject, HttpStatus.OK);
                }
            }
            return answer("SUCCESS", true, null, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return answer("ALREADY EXISTS", false, null, HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            return answer("ERROR", false, null, HttpStatus.CONFLICT);
        }

    }

    @Override
    public <K extends JpaRepository<O, UUID>, O extends AbsEntity> HttpEntity<?> deleteObject(K repository, UUID id) {
        try {
            repository.deleteById(id);
            return answer("SUCCESS", true, null, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return answer("NOT FOUND", false, null, HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return answer("USED BEFORE", false, null, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return answer("ERROR", false, null, HttpStatus.CONFLICT);
        }
    }

    @Override
    public <K extends BaseRepository<O>, O extends AbsEntity, P extends IdProjection> HttpEntity<ApiResponse> getAllObject(K repository) {
        final List<P> all = repository.getAll();


        if (all.size() >= 1) {
            return answer("SUCCESS", true, all, HttpStatus.OK);
        } else
            return answer("EMPTY", false, null, HttpStatus.NO_CONTENT);
    }


}
