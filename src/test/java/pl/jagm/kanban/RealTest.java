package pl.jagm.kanban;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;

public class RealTest {

    @Test
    public void testImmutableListFromNull() {
        assert Collections.unmodifiableCollection(new LinkedList()) != null;
    }
}
