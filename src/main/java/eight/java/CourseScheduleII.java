package eight.java;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by kmishra on 5/13/2016.
 */
public class CourseScheduleII {

    static int[] findOrder(int numCourses, int[][] prerequisites) {
        int transitions[][] = new int[numCourses][numCourses];
        for (int[] t :  transitions) Arrays.fill(t, -1);

        for (int[] pre : prerequisites) {
            transitions[pre[1]][pre[0]] = pre[0];
        }

        int root = findRoot(transitions);
        if (root == -1) return new int[0];

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        Deque<Integer> stack = new ArrayDeque<>();

        if (topologicalSort(transitions, visited, recStack, root, stack)) {
            System.err.println("Cycle detected!");
            return new int[0];
        }

        int[] result = new int[numCourses];
        int i = 0;
        while (!stack.isEmpty()) result[i++] = stack.pop();
        return result;
    }

    private static int findRoot(int[][] transitions) {
        boolean[] inDegrees = new boolean[transitions.length];
        for (int t[] : transitions) {
            for (int j : t) {
                if (j != -1) inDegrees[j] = true;
            }
        }
        int root = 0;
        for (boolean b : inDegrees) {
            if (!b) {
                break;
            }
            ++root;
        }
        return root == transitions.length ? -1 : root;
    }

    private static boolean topologicalSort(int[][] transitions, boolean[] visited, boolean[] recStack, int v, Deque<Integer> stack) {
        if (!visited[v]) {
            visited[v] = true;
            recStack[v] = true;
            int[] t = transitions[v];
            for (int j : t) {
                if (j == -1) continue;
                if (!visited[j] && topologicalSort(transitions, visited, recStack, j, stack))
                    return true;
                else if (recStack[j])
                    return true;
            }
        }
        recStack[v] = false;
        stack.push(v);
        return false;
    }

    private static boolean isCyclic(int[][] transitions, boolean[] visited, boolean recStack[], int v) {
        if (!visited[v]) {
            visited[v] = true;
            recStack[v] = true;
            int[] t = transitions[v];
            for (int j : t) {
                if (j == -1) continue;
                if (!visited[j] && isCyclic(transitions, visited, recStack, j))
                    return true;
                else if (recStack[j])
                    return true;
            }
        }
        recStack[v] = false;
        return false;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {
                {0,1},
                {2,1},
                {3,0},
                {3,2}
        };
        int[] order = findOrder(prerequisites.length, prerequisites);
        for (int i : order) System.err.print(i + " ");
        System.err.println("");
    }
}
