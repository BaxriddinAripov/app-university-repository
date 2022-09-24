package uz.pdp.appspringjpalesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appspringjpalesson7.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean existsByName(String name);
}
