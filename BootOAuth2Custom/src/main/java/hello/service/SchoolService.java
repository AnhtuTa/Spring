package hello.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.common.ResponseStatus;
import hello.entity.School;
import hello.exception.RestException;
import hello.repository.SchoolRepository;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchool(int id) {
        Optional<School> schoolOp = schoolRepository.findById(id);
        if(schoolOp.isPresent()) {
            return schoolOp.get();
        }
        throw new RestException(ResponseStatus.SCHOOL_NOT_FOUND);
    }
}
