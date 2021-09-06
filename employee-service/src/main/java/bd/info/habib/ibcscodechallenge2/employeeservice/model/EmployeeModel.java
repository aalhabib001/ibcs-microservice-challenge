package bd.info.habib.ibcscodechallenge2.employeeservice.model;

import bd.info.habib.ibcscodechallenge2.employeeservice.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_MODEL")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Size(min = 4, max = 4)
    @Column(name = "CODE", unique = true, length = 4)
    private String code;

    @Size(min = 3, max = 35)
    @Column(name = "NAME", nullable = false, length = 35)
    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "DEPT_ID")
    private Long departmentId;
}
