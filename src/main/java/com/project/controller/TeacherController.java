package com.project.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Teacher;
import com.project.service.impl.TeacherServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v2.0")
public class TeacherController {
	
	@Autowired
	private TeacherServiceImpl teacherService;
	
	@GetMapping("/teacher")
	public Mono<ResponseEntity<Flux<Teacher>>> findAll() {
		return Mono
				.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(teacherService.findAll()));
	}

	/**
	 * . method to search students by id
	 */
	@GetMapping("/teacher/{idTeacher}")
	public Mono<ResponseEntity<Teacher>> findById(@PathVariable String idTeacher) {
		return teacherService.findById(idTeacher)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * . method to search by document number
	 */
	@GetMapping("numberDocument/{numberDocument}")
	public Mono<ResponseEntity<Teacher>> findBynumberDocument(@PathVariable String numberDocument) {
		return teacherService.findBynumberDocument(numberDocument)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * . method to search by full name
	 */
	@GetMapping("fullName/{fullName}")
	public Mono<ResponseEntity<Teacher>> findFullName(@PathVariable String fullName) {
		return teacherService.findByFullName(fullName)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * . method to create
	 */
	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<Teacher> teacherMono) {
		Map<String, Object> reply = new HashMap<String, Object>();
		return teacherMono.flatMap(teacher -> {
			if (teacher.getBirthdate() == null) {
				teacher.setBirthdate(new Date());
			}
			return teacherService.save(teacher).map(p -> {
				reply.put("teacher", p);
				reply.put("message", "Teacher created successfully");
				reply.put("dateTime", new Date());
				return ResponseEntity.created(URI.create("/api/v1.0".concat(p.getIdTeacher())))
						.contentType(MediaType.APPLICATION_JSON_UTF8).body(reply);
			});
		});
	}

	/**
	 * . method to update a student
	 */
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Teacher>> save(@RequestBody Teacher teacher, @PathVariable String idTeacher) {
		return teacherService.findById(idTeacher).flatMap(s -> {
			s.setFullName(teacher.getFullName());
			s.setGender(teacher.getGender());
			s.setBirthdate(teacher.getBirthdate());
			s.setTypeDocument(teacher.getTypeDocument());
			s.setNumberDocument(teacher.getNumberDocument());

			return teacherService.save(s);
		}).map(s -> ResponseEntity.created(URI.create("/api/v1.0".concat(s.getIdTeacher())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * . method to delete a student by id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		teacherService.delete(id).subscribe();
	}

	/**
	 * . method to search by dates
	 */
	@GetMapping("student/date/{birthdate}/{birthdate1}")
	public Flux<Teacher> findByBirthdateBetween(
			@PathVariable("birthdate") @DateTimeFormat(iso = ISO.DATE) Date birthdate,
			@PathVariable("birthdate1") @DateTimeFormat(iso = ISO.DATE) Date birthdate1) {
		return teacherService.findByBirthdateBetween(birthdate, birthdate1);
	}
}
