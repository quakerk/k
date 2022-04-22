package TestReact.k.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultOne<T> extends ResultResponse
{
    public T Data;

//    public ResultOne(boolean sw, int code, String msg)
//    {
//        super(sw, code, msg);
//    }
}
