package TestReact.k.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse
{
    boolean Ok;    // 0:싶패, 1:성공
    int Code;      // 리턴 코드
    String Msg;    // 리턴 메세지

//    public ResultResponse(boolean sw, int code, String msg)
//    {
//        this.Ok = true;
//        this.Code = 0;
//        this.Msg = "";
//    }
}
