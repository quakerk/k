package TestReact.k.JpaRepo;

import TestReact.k.Entity.Tbl_WorkGrp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_WorkGrp extends JpaRepository<Tbl_WorkGrp, Long>, CusRepo_WorkGrp
{
}
