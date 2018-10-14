package ds.graph;

public class DisjointSet {
    DisjointSet leader;
    int rank;
    String value;

    public DisjointSet (String value) {
        this.leader = this;
        this.rank = 0;
        this.value = value;
    }

    public int rank () {
        return this.rank;
    }

    /**
     * Find the leader of the set while doing path compression
     * @return
     */
    public DisjointSet find () {
        if (this.leader == this) {
            return this;
        }
        //Path compression
        this.leader = this.leader.find();
        return this.leader;
    }

    /**
     * For each node, we maintain a rank that approximates the logarithm of the sub-tree size
     * and is also an upper bound on the height of the node.
     * @param a
     * @param b
     */
    public void union (DisjointSet a, DisjointSet b) {
        DisjointSet leaderA = a.find();
        DisjointSet leaderB = b.find();

        if (leaderA.rank > leaderB.rank) {
            leaderB.leader = leaderA.leader;
        } else if (leaderA.rank < leaderB.rank) {
            leaderA.leader = leaderB.leader;
        } else {
            //The rank is the log of the subtree size
            leaderA.leader = leaderB.leader;
            leaderB.rank++;
        }
    }

}
