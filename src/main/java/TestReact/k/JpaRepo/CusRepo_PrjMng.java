package TestReact.k.JpaRepo;

import TestReact.k.Entity.TblProjectMain;
import TestReact.k.Response.ResultList;

import java.util.List;

public interface CusRepo_PrjMng
{
    public List<TblProjectMain> Dsl_FindAll();
    public TblProjectMain Dsl_Create();
    public TblProjectMain Dsl_Update();
}
