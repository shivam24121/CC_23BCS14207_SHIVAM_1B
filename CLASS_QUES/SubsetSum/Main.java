import java.util.*;
import java.lang.*;
import java.io.*;

public class Main{
    
    
    
    public static void main(String[] args) throws java.lang.Exception{
        
        BufferedReader br;
        br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t=Integer.parseInt(br.readLine());
        while(t-->0){
            
            int n=Integer.parseInt(br.readLine());
            st=new StringTokenizer(br.readLine());
            
            int[] arr=new int[n];
            
            for(int i=0;i<n;i++){
                arr[i]=Integer.parseInt(st.nextToken());
            }
            ArrayList<ArrayList<Integer>>res=new ArrayList<>();
            int target=Integer.parseInt(br.readLine());
            int ct=0;
            
            for(int mask=0;mask<(1<<n);mask++){
                int sum=0;
                ArrayList<Integer>list=new ArrayList<>();
                for(int i=0;i<n;i++){
                    if(((mask>>i)&1)!=0){
                        sum+=arr[i];
                        list.add(arr[i]);
                    }
                }
                if(sum==target){
                    ct++;
                    res.add(list);
                }
            }
            System.out.println(ct);
            for(int i=0;i<res.size();i++){
                for(int j:res.get(i)){
                    System.out.print(j+" ");
                }
                System.out.println();
            }
        }
    }
}
