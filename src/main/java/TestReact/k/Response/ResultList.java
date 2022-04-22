package TestReact.k.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultList<T> extends ResultResponse
{
    public List<T> Data;
}
