import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by AS on 07.10.2018.
 */
public class RouteTest
{
    Route route;
    private ArrayList<Nodes> nodes = new ArrayList<Nodes>();

    @Before
    public void testBefore()
    {
        nodes.add(new Nodes(0,"Osna", 100,120));
        nodes.add(new Nodes(1,"Dortmund", 100,120));
    }


}