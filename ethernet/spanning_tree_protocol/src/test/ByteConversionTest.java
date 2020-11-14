package test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import main.Switch;

public class ByteConversionTest {
    
    @Test
    public void simple() {
        Switch a = new Switch(32768, new byte[] {0,0,0,0,0b100,0b01010111});

        assertArrayEquals(
            a.getSwitchId(), 
            new byte[] { (byte) 0b10000000, 0, 0, 0, 0, 0, 0b100, 0b01010111 }
        );
    }    
}
