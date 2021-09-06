package bd.info.habib.ibcscodechallenge2.departmentservice.repository;

import bd.info.habib.ibcscodechallenge2.departmentservice.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {
}
