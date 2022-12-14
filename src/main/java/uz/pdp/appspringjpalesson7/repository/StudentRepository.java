package uz.pdp.appspringjpalesson7.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appspringjpalesson7.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page <Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);

    Page<Student> findAllByGroup_Faculty_Id(Integer group_faculty_id, Pageable pageable);

    Page<Student> findByGroup_Id(Integer group_id, Pageable pageable);
}
