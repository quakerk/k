package TestReact.k.JpaRepo;


import TestReact.k.Entity.TblProjectMain;
import TestReact.k.Response.ResultList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static TestReact.k.Entity.QTblProjectMain.tblProjectMain;

@Repository
public class CusRepo_PrjMngImpl implements CusRepo_PrjMng
{
    private JPAQueryFactory JpaQF;

    @Override
    public List<TblProjectMain> Dsl_FindAll()
    {
        return JpaQF.selectFrom(tblProjectMain).fetch();
    }

    @Override
    public TblProjectMain Dsl_Create()
    {
        return null;
    }

    @Override
    public TblProjectMain Dsl_Update() {
        return null;
    }
}
