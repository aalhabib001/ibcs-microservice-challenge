package bd.info.habib.ibcscodechallenge2.departmentservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

    @Size(min = 3, max = 35, message = "Name should be min 3 to max 35 char")
    private String name;

    private Boolean active;
}
