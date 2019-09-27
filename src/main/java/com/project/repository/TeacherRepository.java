package com.project.repository;

import com.project.model.Teacher;
import java.util.Date;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherRepository extends ReactiveMongoRepository<Teacher, String> {

  Mono<Teacher> findByFullName(String fullName);

  Mono<Teacher> findBynumberDocument(String numberDocument);

  Flux<Teacher> findByBirthdateBetween(Date birthdate,Date birthdate1);

}
