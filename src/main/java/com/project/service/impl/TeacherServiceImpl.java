package com.project.service.impl;

import com.project.model.Teacher;
import com.project.repository.TeacherRepository;
import com.project.service.TeacherInterface;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherServiceImpl implements TeacherInterface {

  @Autowired
  public TeacherRepository teacherRepository;

  @Override
  public Flux<Teacher> findAll() {
    return teacherRepository.findAll();
  }

  @Override
  public Flux<Teacher> findByDate(String birthdate) {
    return null;
  }

  @Override
  public Flux<Teacher> findByBirthdateBetween(Date birthdate, Date birthdate1) {
    return teacherRepository.findByBirthdateBetween(birthdate, birthdate1);
  }

  @Override
  public Mono<Teacher> findByFullName(String fullName) {
    return teacherRepository.findByFullName(fullName);
  }

  @Override
  public Mono<Teacher> findBynumberDocument(String numberDocument) {
    return teacherRepository.findBynumberDocument(numberDocument);
  }

  @Override
  public Mono<Teacher> findById(String idTeacher) {
    return teacherRepository.findById(idTeacher);
  }

  @Override
  public Mono<Teacher> save(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  @Override
  public Mono<Void> delete(String idTeacher) {
    return teacherRepository.deleteById(idTeacher);
  }

}
