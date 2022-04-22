package TestReact.k.JpaRepo;


import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.persistence.NamedNativeQuery;
import java.util.List;

public interface Repo_ProjectSet_WorkCate extends JpaRepository<Tbl_ProjectSet_WorkCate, Long>, CusRepo_Prj_WorkCate
{
    @Query(value =
            "DROP PROCEDURE IF EXISTS getpath;\n" +
            "DELIMITER $$\n" +
            "CREATE PROCEDURE getpath(IN cat_id INT, OUT path TEXT)\n" +
            "BEGIN\n" +
            "    DECLARE catname VARCHAR(20);\n" +
            "    DECLARE temppath TEXT;\n" +
            "    DECLARE tempparent INT;\n" +
            "    DECLARE tempid INT;\n" +
            "    SET max_sp_recursion_depth = 255;\n" +
            "    SELECT id, title, parent_id FROM category WHERE id=cat_id INTO tempid, catname, tempparent ;\n" +
            "    IF tempparent IS NULL\n" +
            "    THEN\n" +
            "        SET path = tempid;\n" +
            "    ELSE\n" +
            "        CALL getpath(tempparent, temppath);\n" +
            "        SET path = CONCAT(temppath, '/', tempid);\n" +
            "    END IF;\n" +
            "END$$\n" +
            "DELIMITER ;\n" +
            "\n" +
            "DROP FUNCTION IF EXISTS getpath;\n" +
            "DELIMITER $$\n" +
            "CREATE FUNCTION getpath(cat_id TEXT) RETURNS TEXT DETERMINISTIC\n" +
            "BEGIN\n" +
            "    DECLARE res TEXT;\n" +
            "    CALL getpath(cat_id, res);\n" +
            "    RETURN res;\n" +
            "END$$\n" +
            "DELIMITER ;\n" +
            "\n" +
            "SELECT id, title, getpath(id) AS path FROM category ORDER BY path;",
            nativeQuery = true)
    List<Tbl_ProjectSet_WorkCate> Jpa_FindAll();


    @Query(value =
            "WITH RECURSIVE cte (id, title, parent_id) " +
                    "AS (\n" +
            "   SELECT id, title, parent_id\n" +
            "   FROM category\n" +
            "   WHERE parent_id = '1'\n" +
            "   UNION ALL\n" +
            "   SELECT p.id, p.title, p.parent_id\n" +
            "   FROM category p\n" +
            "   INNER JOIN cte\n" +
            "       ON p.parent_id = cte.id\n" +
            ")\n" +
            "SELECT * FROM cte;", nativeQuery = true)
    List<Tbl_ProjectSet_WorkCate> Jpa_FindTree();


//    @Procedure(procedureName = "GET_PARENT_TREE_PATH")
//    String GetParentTreePath(String path, int pid);
}
