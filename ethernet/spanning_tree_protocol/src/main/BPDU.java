package main;

public class BPDU {
    byte[] protocolId;  // 2 bytes
    byte versionId;
    byte messageType;   // Conﬁguration or TCN BPDU
    byte flag;  //The Topology Change (TC) bit signals a topology change. The Topology Change Acknowledgment (TCA) bit is set to acknowledge receipt of a configuration message.
    byte[] rootSwitchId;    // 8 bytes, Identifies the root bridge by listing its 2-byte priority number followed by its 6-byte MAC address.
    byte[] rootPathCost;    // 4 bytes
    byte[] senderSwitchId;    // 8 bytes, Identifies the Sender bridge by listing its 2-byte priority number followed by its 6-byte MAC address.
    byte[] portId;  // 2 bytes, Identifies the port from which the configuration message was sent.
    int messageAge;  // 2 bytes, Specifies the amount of time elapsed since the Root Bridge (Root Switch) sent the configuration message on which the current configuration message is based.
    int maxAge; // 2 bytes, Indicates when the current configuration message should be deleted.
    byte[] helloTime; // 2 bytes, Provides the time period between Root Bridge (Root Switch) configuration messages.
    byte[] forwardDelay;    // 2 bytes, Provides the length of time that bridges should wait before transitioning to a new state after a topology change.

    public BPDU(byte[] senderId, byte[] rootId, byte[] cost) {
        senderSwitchId = senderId.clone();
        rootSwitchId = rootId.clone();
        rootPathCost = cost.clone();
        maxAge = 5; // just for convenience
    }
}
