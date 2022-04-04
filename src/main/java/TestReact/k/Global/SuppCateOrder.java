package TestReact.k.Global;

import java.util.ArrayList;
import java.util.Arrays;

public class SuppCateOrder
{
    long id;
    ArrayList<String > node;

    public SuppCateOrder(long id, String str)
    {
        this.id = id;
        String[] t = str.split("/");
        this.node = new ArrayList<>();
        node.addAll(Arrays.asList(t));
    }
}

