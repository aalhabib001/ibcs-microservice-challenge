package bd.info.habib.ibcscodechallenge2.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel{
    public Date timestamp;
    public int status;
    public String error;
    public String message;
    public String path;
}

