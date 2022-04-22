//package TestReact.k.Dto;
//
//import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Ret_ProjectSet_WorkCate
//{
//    private Long Id;
//    private String Title;
//    private List<Ret_ProjectSet_WorkCate> arrSub;
//
//    public Ret_ProjectSet_WorkCate(final Tbl_ProjectSet_WorkCate cate)
//    {
//        this.Id = cate.getId();
//        this.Title  = cate.getTitle();
//        this.arrSub = cate.getSubCate().stream().map( Ret_ProjectSet_WorkCate::new ).collect(Collectors.toList());
//    }
//}
