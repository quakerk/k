package TestReact.k.JpaRepo;

import TestReact.k.Entity.TblProjectMain;
import org.springframework.data.jpa.repository.JpaRepository;

// public interface Repo_ProjectSet_WorkCate extends JpaRepository<Tbl_ProjectSet_WorkCate, Long>, CusRepo_Prj_WorkCate
public interface Repo_ProjectMng extends JpaRepository<TblProjectMain, Long>, CusRepo_PrjMng
{
}
