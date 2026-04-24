class NumArray {

    int n;
    int[] segmentTree;

    public void buildTree(int idx,int l,int r,int[] nums){
        if(l==r){
            segmentTree[idx]=nums[l];
            return;
        }

        int mid=l+(r-l)/2;
        buildTree(idx*2+1,l,mid,nums);
        buildTree(idx*2+2,mid+1,r,nums);

        segmentTree[idx]=segmentTree[idx*2+1]+segmentTree[idx*2+2];
    }

    public int query(int left,int right,int idx,int l,int r){

        if(l>right || r<left){
            return 0;
        }
        else if(l>=left && r<=right){
            return segmentTree[idx];
        }
        else{
            int mid=l+(r-l)/2;

            int idhar=query(left,right,2*idx+1,l,mid);
            int udhar=query(left,right,2*idx+2,mid+1,r);

            return idhar+udhar;
        }
    }

    public void updateTree(int index,int val,int idx,int l,int r){
        if(l==r){
            segmentTree[idx]=val;
            return;
        }

        int mid=l+(r-l)/2;

        if(index<=mid){
            updateTree(index,val,idx*2+1,l,mid);
        }
        else{
            updateTree(index,val,idx*2+2,mid+1,r);
        }
        segmentTree[idx]=segmentTree[idx*2+1]+segmentTree[idx*2+2];
    }

    public NumArray(int[] nums) {
        n=nums.length;
        segmentTree=new int[4*n];
        buildTree(0,0,n-1,nums);
    }
    
    public void update(int index, int val) {
        updateTree(index,val,0,0,n-1);
    }
    
    public int sumRange(int left, int right) {
        return query(left,right,0,0,n-1);
    }
}
