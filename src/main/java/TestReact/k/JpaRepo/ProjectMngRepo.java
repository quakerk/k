package TestReact.k.JpaRepo;

import TestReact.k.Entity.TblProjectMain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMngRepo extends JpaRepository<TblProjectMain, Long>
{
}
