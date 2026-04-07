import java.util.*;

public class Main {

    static void dfs(int u, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[u] = true;

        for (int v : adj.get(u)) {
            if (!vis[v]) {
                dfs(v, vis, adj);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[n + 1];

        ArrayList<Integer> components = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                components.add(i);
                dfs(i, vis, adj);
            }
        }

        System.out.println(components.size() - 1);

        for (int i = 1; i < components.size(); i++) {
            System.out.println(components.get(i - 1) + " " + components.get(i));
        }

        sc.close();
    }
}
