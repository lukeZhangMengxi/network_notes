# Spanning Tree Algorithm
- Ethernet switches have incorporated a switch-to-switch protocol to construct a subset of the switch-connections graph that has no loops and yet allows reachability of every host, known in graph theory as a **spanning tree**.

<br />

### The Algorithm
- Every switch has an ID, eg its smallest Ethernet address, and every edge that attaches to a switch does so via a particular, numbered interface.
- The simplest measure of path cost is the number of hops, though current implementations generally use a cost factor inversely proportional to the bandwidth (so larger bandwidth has lower cost).
- The process is dynamic, so if an outage occurs then the spanning tree is recomputed. If the outage should partition the network into two pieces, both pieces will build spanning trees.
- Procedure:
    1. Elect a root node, eg the one with the smallest ID.
    2. If a given segment connects to two switches that both connect to the root node, the switch with the shorter path to the root is used, if possible; in the event of ties, the switch with the smaller ID is used.
