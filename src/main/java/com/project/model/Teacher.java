package com.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teacher")
public class Teacher {
  @Id
  @NotNull
  public String idTeacher;
  @NotEmpty(message = "name cannot be empty")
  public String fullName;
  @NotEmpty(message = "gender cannot be empty")
  public String gender;
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  public Date birthdate;
  @NotEmpty(message = "Document type cannot be empty")
  public String typeDocument;
  @NotEmpty(message = "number document cannot be empty")
  @Size(min = 8, max = 8, message = "document number can only have 8 characters")
  public String numberDocument;
  @NotEmpty(message = "'idCourse' cannot be empty")
  public String idCourse;
  @NotEmpty(message = "cannot be empty")
  public String idFamily;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt = new Date();
}
