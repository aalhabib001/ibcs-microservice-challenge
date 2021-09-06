package bd.info.habib.ibcscodechallenge2.departmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT_MODEL")
public class DepartmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Size(min = 3, max = 35, message = "Name should be min 3 to max 35 char")
    @Column(name = "NAME", nullable = false, length = 35)
    private String name;

    @Column(name = "ACTIVE")
    private Boolean active;
}
