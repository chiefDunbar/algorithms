public class Path_Compression_Weighted_Quick_UnionUF {
    int id[];
    int size[];

    Path_Compression_Weighted_Quick_UnionUF(int N){
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    // // two pass implepentation
    // private int root(int i){
    //     int s = i;
    //     int t;
    //     while (id[i] != i) {
    //         i = id[i];
    //     }
    //     while (id[s] != i) {
    //         t = s;
    //         s = id[s];
    //         id[t] = i;
    //     }
    //     return i;
    // }

    // single pass implementation
    private int root(int i){
        while (id[i] != i) {
            id[i] = id[id[i]];  // every other node in path points to their grandparents: PATH COMPRESSION
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
        if (size[i] < size[j]) {
            id[i] = j; 
            size[j] += size[i];
        }
        else {
            id[j] = i;
            size[i] += size[j];
        }                   
    }
}

