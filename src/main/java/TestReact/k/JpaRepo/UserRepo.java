package TestReact.k.JpaRepo;

import TestReact.k.Entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<TblUser, Long>
{
//    Optional<TblUser> findByUid(String name);
//    Optional<TblUser> findByName(long uid);
//    Optional<TblUser> findByIdNum(long uid);
}
