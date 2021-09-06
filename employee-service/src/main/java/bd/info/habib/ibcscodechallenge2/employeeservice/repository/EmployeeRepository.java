package bd.info.habib.ibcscodechallenge2.employeeservice.repository;


import bd.info.habib.ibcscodechallenge2.employeeservice.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
}
