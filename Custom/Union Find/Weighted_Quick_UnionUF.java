public class Weighted_Quick_UnionUF {
    private int[] id; 
    private int[] sz;

    // creating id array
    public Weighted_Quick_UnionUF (int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i){
        while (id[i] != i) {
            // id[i] = id[id[i]]; every other node in tree points to their grandparents: PATH COMPRESSION?
            i = id[i];
        }
        // could also add another loop so that every node in path points to the root, therefore flattening the tree
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        // link root of smaller tree to the larger tree
        if (sz[i] < sz[j]) {
            id[i] = j; 
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }                   
    }
}