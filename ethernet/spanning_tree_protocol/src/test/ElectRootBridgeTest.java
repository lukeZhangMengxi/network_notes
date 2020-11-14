package test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import main.Switch;

public class ElectRootBridgeTest {
    Switch a, b, c;
    byte[] expectedRootSwitchId;
    
    @Before
    public void init() {
        a = new Switch(3276, new byte[] {0,0,0,0,0,1});
        b = new Switch(3276, new byte[] {0,0,0,0,0,2});
        c = new Switch(3276, new byte[] {0,0,0,0,0,3});
        a.addNeighbor(b);
        a.addNeighbor(c);
        b.addNeighbor(c);
    }

    @Test
    public void tiePriorityElectLowMAC() throws InterruptedException {

        expectedRootSwitchId = a.getSwitchId();

        a.start();
        b.start();
        c.start();

        a.join();
        b.join();
        c.join();

        assertArrayEquals(a.getRootSwitchId(), expectedRootSwitchId);
        assertArrayEquals(b.getRootSwitchId(), expectedRootSwitchId);
        assertArrayEquals(c.getRootSwitchId(), expectedRootSwitchId);
    }

    @Test
    public void electLowPriorityValue() throws InterruptedException {

        c.setNetworkPriority(200);
        expectedRootSwitchId = c.getSwitchId();

        a.start();
        b.start();
        c.start();

        a.join();
        b.join();
        c.join();

        assertArrayEquals(a.getRootSwitchId(), expectedRootSwitchId);
        assertArrayEquals(b.getRootSwitchId(), expectedRootSwitchId);
        assertArrayEquals(c.getRootSwitchId(), expectedRootSwitchId);
    }

}
