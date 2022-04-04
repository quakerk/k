package TestReact.k.JpaRepo;

import TestReact.k.Entity.TblTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepo extends JpaRepository<TblTest, Long>
{
//    Optional<TblTest> findById(Long id);
    Optional<TblTest> findByStr(String str);
}
