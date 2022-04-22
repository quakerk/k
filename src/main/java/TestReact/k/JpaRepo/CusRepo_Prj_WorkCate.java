package TestReact.k.JpaRepo;

import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import java.util.List;

public interface CusRepo_Prj_WorkCate
{
    public List<Tbl_ProjectSet_WorkCate> Dsl_FindAll();
    public List<Tbl_ProjectSet_WorkCate> Dsl_FindCateAll();
    public Tbl_ProjectSet_WorkCate Dsl_Update(long id, String title, long pid, int child, String node);
    public Tbl_ProjectSet_WorkCate Dsl_Add(long pid, String title);
}
