package TestReact.k.JpaRepo;

import TestReact.k.Entity.Tbl_WorkGrp;

import java.util.List;

public interface CusRepo_WorkGrp
{
    public List<Tbl_WorkGrp> Dsl_FindSubAll(final long pid);
    public Tbl_WorkGrp Dsl_FindById(final long id);
}
