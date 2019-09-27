package com.project.service;

import com.project.model.Teacher;
import java.util.Date;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherInterface {

  public Flux<Teacher> findAll();

  public Flux<Teacher> findByDate(String birthdate);

  public Flux<Teacher> findByBirthdateBetween(Date birthdate, Date birthdate1);

  public Mono<Teacher> findByFullName(String fullName);

  public Mono<Teacher> findBynumberDocument(String numberDocument);

  public Mono<Teacher> findById(String idTeacher);

  public Mono<Teacher> save(Teacher teacher);

  public Mono<Void> delete(String idTeacher);
  
}
