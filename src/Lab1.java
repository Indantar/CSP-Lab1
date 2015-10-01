import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.View;

/**
 * Created by g00284823 on 17/09/2015.
 */
public class Lab1
{
    private JButton loadButton;
    private JPanel panel1;
    private JButton reverseContentButton;
    private JButton reverseWordsButton;
    private JButton countButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private String path;
    private String[] sentence;
    private String[] reverseSentence;
    private String[] reverseWordsSentence;

    public Lab1() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(new java.io.File("I:\\Year 5\\CSP\\Lab1"));
                    int res = fc.showOpenDialog(panel1);
                    if( res == JFileChooser.APPROVE_OPTION) {

                    }
                    path = fc.getSelectedFile().getAbsolutePath();
                    File inputFile = fc.getSelectedFile();
                    FileReader fr = new FileReader(inputFile);
                    BufferedReader br = new BufferedReader(fr);
                    StringBuilder sb = new StringBuilder();
                    String s;
                    while ((s = br.readLine()) != null) {
                        sb.append(s+"\n");
                    }
                    String in = sb.toString();
                    sentence = in.split("\n");
                    for( int i = 0; i  < sentence.length; i++)
                    {
                        textArea1.append(sentence[i]+"\n");
                    }
                    fr.close();
                } catch (FileNotFoundException e2) {
                    System.err.println("FileStreamsTest: " + e2);
                } catch (IOException e1) {
                    System.err.println("FileStreamsTest: " + e1);
                }
                System.out.println(sentence.length);
            }
        });
        reverseContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                String setUp = new String();
                String[][] words = new String[sentence.length][];
                for( int  i = 0; i < sentence.length; i++)
                {
                    words[i] = sentence[i].split("\\s+");
                }
                for( int j = 0; j < sentence.length; j++)
                {
                    //sb.delete(0,sb.length());
                    for(int k = words[j].length-1; k >= 0; k--)
                    {
                        sb.append(words[j][k]+" ");
                        textArea2.append(words[j][k]+" ");
                    }
                    sb.append("\n");
                    textArea2.append("\n");
                }
                setUp = sb.toString();
                reverseSentence = new String[words.length];
                reverseSentence = setUp.split("\n");
                System.out.println(reverseSentence.length);
            }
        });
        reverseWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                String setUp = new String();
                String[][] revWords = new String[sentence.length][];
                for( int i = 0; i < sentence.length; i++)
                {
                    revWords[i] = reverseSentence[i].split("\\s+");
                }
                for( int j = 0; j < sentence.length; j++)
                {
                    sb.delete(0, sb.length());
                    for (int k = 0; k < revWords[j].length; k++)
                    {
                        if(revWords[j].length % 2 == 0)
                        {
                            if(k % 2 == 0)
                            {
                                sb.append(revWords[j][k + 1] + " ");
                                textArea3.append(revWords[j][k + 1] + " ");
                            }
                            else
                            {
                                sb.append(revWords[j][k - 1] + " ");
                                textArea3.append(revWords[j][k - 1] + " ");
                            }
                        }
                        else
                        {
                            if(k % 2 == 0)
                            {
                                if(k+1 < revWords[j].length-1)
                                {
                                    sb.append(revWords[j][k + 1] + " ");
                                    textArea3.append(revWords[j][k + 1] + " ");
                                }
                                else
                                {
                                    sb.append(revWords[j][k] + " ");
                                    textArea3.append(revWords[j][k] + " ");
                                }
                            }
                            else
                            {
                                sb.append(revWords[j][k - 1] + " ");
                                textArea3.append(revWords[j][k - 1] + " ");
                            }
                        }
                    }
                    textArea3.append("\n");
                }
                setUp = sb.toString();
                reverseWordsSentence = new String[revWords.length];
                reverseWordsSentence = setUp.split("\n");
                System.out.println(reverseWordsSentence.length);
            }
        });
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = new String();
                String[][] words = new String[sentence.length][];
                for( int  i = 0; i < sentence.length; i++)
                {
                    words[i] = sentence[i].replaceAll("[^a-zA-Z^]"," ").toLowerCase().split("\\s+");
                }
                HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
                for(int j = 0; j < words.length; j++)
                {
                    for(int l = 0; l < words[j].length; l++)
                    {
                        word = words[j][l];
                        if(wordCount.containsKey(word))
                        {
                            wordCount.put(word,wordCount.get(word) + 1);
                        }
                        else
                        {
                            wordCount.put(word,1);
                        }
                    }
                }
                Iterator i = wordCount.keySet().iterator();
                while(i.hasNext())
                {
                    String key = i.next().toString();
                    Integer val = wordCount.get(key);
                    System.out.println(key + " " + val);
                    textArea4.append(key + " " + val + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab1");
        frame.setContentPane(new Lab1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
