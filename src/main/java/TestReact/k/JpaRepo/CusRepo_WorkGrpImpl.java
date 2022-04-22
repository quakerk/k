package TestReact.k.JpaRepo;

import TestReact.k.Entity.QTbl_WorkGrp;
import TestReact.k.Entity.Tbl_WorkGrp;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import static TestReact.k.Entity.QTbl_WorkGrp.tbl_WorkGrp;

@Repository
public class CusRepo_WorkGrpImpl implements CusRepo_WorkGrp
{
    private final JPAQueryFactory JpaQF;

    public CusRepo_WorkGrpImpl(JPAQueryFactory jpaQF)
    {
        JpaQF = jpaQF;
    }

    @Override
    public List<Tbl_WorkGrp> Dsl_FindSubAll(long pid)
    {
        QTbl_WorkGrp parent = new QTbl_WorkGrp("parent");
        QTbl_WorkGrp sub    = new QTbl_WorkGrp("sub");

        return JpaQF.selectFrom(parent)
                    .where(parent.id.eq(pid))
                    .distinct()
                    .leftJoin(parent.Sub, sub)
                    .fetchJoin()
                //.where(parent.parent.id == Long.valueOf(pid) )
                    .where(parent.parent.isNull())
                    .orderBy(parent.id.asc())
                    .fetch();
    }

    @Override
    public Tbl_WorkGrp Dsl_FindById(long id)
    {
//        JpaQF.selectFrom(this).selectOne(id).fetch();
        return null;
    }
}
