package controllers.server;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class RoleDeciderTest {
    @Test
    public void sample_test() {
        RoleDecider rd = new RoleDecider(mock(Players.class));
        for (int i = 0; i < 10; i++) {
            //rd.getRoleToBeAssigned();
        }

    }
}
