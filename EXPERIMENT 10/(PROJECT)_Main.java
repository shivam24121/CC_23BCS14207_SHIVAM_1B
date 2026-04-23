import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    static class Trie {

        TrieNode root = new TrieNode();

        public void insert(String word) {

            TrieNode curr = root;

            for (int i = 0; i < word.length(); i++) {

                int index = word.charAt(i) - 'a';

                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }

                curr = curr.children[index];
            }

            curr.isEnd = true;
        }

        public boolean search(String word) {

            TrieNode curr = root;

            for (int i = 0; i < word.length(); i++) {

                int index = word.charAt(i) - 'a';

                if (curr.children[index] == null) {
                    return false;
                }

                curr = curr.children[index];
            }

            return curr.isEnd;
        }

        public List<String> autocomplete(String prefix) {

            List<String> result = new ArrayList<>();

            TrieNode curr = root;

            for (int i = 0; i < prefix.length(); i++) {

                int index = prefix.charAt(i) - 'a';

                if (curr.children[index] == null) {
                    return result;
                }

                curr = curr.children[index];
            }

            dfs(curr, prefix, result);

            return result;
        }

        private void dfs(TrieNode node, String word, List<String> result) {

            if (node.isEnd) {
                result.add(word);
            }

            for (int i = 0; i < 26; i++) {

                if (node.children[i] != null) {

                    char next = (char) ('a' + i);

                    dfs(node.children[i], word + next, result);
                }
            }
        }
    }


    static Trie trie = new Trie();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Trie Smart Search");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Smart Search Engine (Trie)", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();

        JTextField input = new JTextField(20);
        JButton insertBtn = new JButton("Insert");
        JButton searchBtn = new JButton("Search");

        searchPanel.add(input);
        searchPanel.add(insertBtn);
        searchPanel.add(searchBtn);

        frame.add(searchPanel, BorderLayout.CENTER);

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> suggestionList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(suggestionList);

        frame.add(scrollPane, BorderLayout.SOUTH);

        // Insert button
        insertBtn.addActionListener(e -> {

            String word = input.getText().toLowerCase();

            if (!word.isEmpty()) {

                trie.insert(word);
                JOptionPane.showMessageDialog(frame, "Word inserted!");
            }
        });

        // Search button
        searchBtn.addActionListener(e -> {

            String word = input.getText().toLowerCase();

            boolean exists = trie.search(word);

            JOptionPane.showMessageDialog(frame,
                    exists ? "Word found!" : "Word not found.");
        });

        input.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {

                String text = input.getText().toLowerCase();

                model.clear();

                if (text.length() == 0) {
                    return;
                }

                List<String> suggestions = trie.autocomplete(text);

                for (int i = 0; i < suggestions.size(); i++) {
                    model.addElement(suggestions.get(i));
                }
            }
        });

        String[] words = {
                "apple", "app", "application", "apply",
                "banana", "band", "bandit",
                "cat", "car", "carbon",
                "dog", "door"
        };

        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }

        frame.setVisible(true);
    }
}
