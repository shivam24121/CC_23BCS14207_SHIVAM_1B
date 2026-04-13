class FenwickTree {

    int[] tree;
    int n;

    FenwickTree(int n){
        this.n = n;
        tree = new int[n+1];
    }

    void update(int i, int val){
        while(i <= n){
            tree[i] += val;
            i += i & (-i);
        }
    }

    int query(int i){
        int sum = 0;

        while(i > 0){
            sum += tree[i];
            i -= i & (-i);
        }

        return sum;
    }

    int rangeQuery(int l, int r){
        return query(r) - query(l-1);
    }
}
