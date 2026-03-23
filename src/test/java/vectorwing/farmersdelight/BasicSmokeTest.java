package vectorwing.farmersdelight;

import org.junit.Test;
import static org.junit.Assert.*;

public class BasicSmokeTest {
    @Test
    public void testRegistrationClassesLoad() {
        assertNotNull("ModBlocks class should be present", vectorwing.farmersdelight.common.registry.ModBlocks.class);
        assertNotNull("ModItems class should be present", vectorwing.farmersdelight.common.registry.ModItems.class);
    }
}
