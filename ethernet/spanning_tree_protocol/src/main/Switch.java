package main;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Switch extends Thread implements SwitchAPI {
    // Switch Bridge ID, 8 Bytes, 2 for priority, 6 for MAC address
    private byte[] bid;
    private List<Switch> neighbors;
    private BlockingQueue<BPDU> q;
    private byte[] rootSwithId;

    public Switch(int priority, byte[] MACAdress) {
        bid = new byte[8];
        neighbors = new LinkedList<>();
        q = new LinkedBlockingQueue<>();

        // Assume big endian
        bid[0] = (byte) (priority >> 8);
        bid[1] = (byte) (priority);
        System.arraycopy(MACAdress, 0, bid, 2, 6);
    }

    public void addNeighbor(Switch other) {
        if (other == null) return;
        this.neighbors.add(other);
        other.neighbors.add(this);
    }

    public void receiveBPDU(BPDU msg) {
        this.q.offer(msg);
    }

    public void setNetworkPriority(int val) {
        // TODO: assert val is 2 bytes
        bid[0] = (byte) (val >> 8);
        bid[1] = (byte) (val);
    }

    public byte[] getSwitchId() { return bid; }
    public byte[] getRootSwitchId() { return rootSwithId; }

    @Override
    public void run() {
        this.electRootBridge();
    }

    @Override
    public void electRootBridge() {
        this.rootSwithId = this.bid;
        BPDU msg = new BPDU(
            this.getSwitchId(),
            this.getSwitchId(),
            new byte[] {0,0}
        );

        for (Switch neighbor : neighbors) {
            System.out.println(this.bid[7] + " send BPDU to " + neighbor.bid[7] + ", msg age = " + msg.messageAge);
            neighbor.receiveBPDU(msg);
        }

        int maxWaitIteration = 5, wait = 0;

        while(wait < maxWaitIteration) {
            BPDU received;
            if ((received = this.q.poll()) != null) {
                if (Utils.leftIsSmaller(received.rootSwitchId, this.rootSwithId)) {
                    this.rootSwithId = received.rootSwitchId.clone();
                }

                if (
                    received.messageAge <= received.maxAge &&
                    !received.rootSwitchId.equals(this.bid)
                ) {
                    received.messageAge++;
                    for (Switch neighbor : neighbors) {
                        System.out.println(this.bid[7] + " send BPDU to " + neighbor.bid[7] + ", msg age = " + received.messageAge);
                        neighbor.receiveBPDU(received);
                    }
                }

                wait = 0;
            }
            else {
                wait++;
            }
        }

    }

}
