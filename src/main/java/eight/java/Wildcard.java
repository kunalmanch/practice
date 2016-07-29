package eight.java;

/**
 * Created by kmishra on 4/25/2016.
 */
public class Wildcard {

    boolean isMatch(String s, String p) {
        DFA DFA = new DFA(p);
        return DFA.matches(s);
    }

    private class DFA {

        State start;
        DFA(String p) {
            p = p.replace(".*", "*");
            int id = 0;
            start = new State(id++);
            State curr = start;
            for (int i = 0; i < p.length(); i++) {
                curr.next = new State(id++);
                curr.edge = p.charAt(i);
                curr = curr.next;
            }
            curr.terminal = true;
        }
        boolean matches(String s) {
            State curr = start;
            char prevEdge = 0;
            for (int i = 0; i < s.length(); i++) {
                if (curr.terminal) {
                    return prevEdge == '*';
                } else if (curr.edge == '.' || curr.edge == s.charAt(i) || curr.edge == '*') {
                    prevEdge = curr.edge;
                    curr = curr.next;
                } else return false;
            }
            return true;
        }

        @Override
        public String toString() {
            String string = "";
            State curr = start;
            while (curr != null) {
                if (curr.terminal) {
                    string += curr.id;
                } else {
                    string += curr.id + " --> " + curr.edge + " --> ";
                }
                curr = curr.next;
            }
            return string;
        }
    }

    private class State {
        int id;
        State next;
        char edge;
        boolean terminal;

        State(int id) {
            this(id, false);
        }

        State(int id, boolean terminal) {
            this.id = id;
            this.terminal = terminal;
        }
    }

    public static void main(String[] args) {
        String p = "a*b";
        DFA dfa = new Wildcard(). new DFA(p);
        System.err.println(dfa);
        System.err.println(dfa.matches("aab"));
    }
}
