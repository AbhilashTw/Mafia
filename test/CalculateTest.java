import org.junit.Assert;
import org.junit.Test;

public class CalculateTest {
    @Test
    public void one_plus_one_is_two() {
        Calculate calculate = new Calculate(1, 1);
        Assert.assertEquals(2, calculate.sum());
    }

    @Test
    public void two_plus_one_is_three() {
        Calculate calculate = new Calculate(2, 1);
        Assert.assertEquals(3, calculate.sum());
    }

    @Test
    public void zero_plus_zero_is_zero() {
        Calculate calculate = new Calculate(0, 0);
        Assert.assertEquals(0, calculate.sum());
    }
    @Test
    public void zero_plus_ero_is_zero() {
        Calculate calculate = new Calculate(0, 0);
        Assert.assertEquals(0, calculate.sum());
    }
    @Test
    public void zero_plus_ero_is_ero() {
        Calculate calculate = new Calculate(0, 0)
        Assert.assertEquals(0, calculate.sum());
    }
}
