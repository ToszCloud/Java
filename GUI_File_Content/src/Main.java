import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.sun.javafx.fxml.expression.Expression.add;
import static javax.swing.SwingConstants.EAST;

public class Main {
    public static void main(String[] args) {
        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Resizable slider");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        JButton but= new JButton("select file");
        but.setLayout(new GridLayout(1,1,1,1));


        JButton but2=new JButton("Exist");
        but2.setLayout(new GridLayout(1,1,1,1));

        JPanel panel = new JPanel();



            panel.add(but);
            panel.add(but2);
        JTextArea area = new JTextArea(400,30);
        JScrollPane scroll=new JScrollPane(area);

        area.setBackground(Color.WHITE);
        area.setForeground(Color.BLACK);






        but.addActionListener(e-> {
            JFileChooser f1=new JFileChooser();
            if (f1.showOpenDialog(new JFrame())==0){
                StringBuilder str=new StringBuilder();
                try {
                    BufferedReader buff=new BufferedReader(new FileReader((f1.getSelectedFile())));
                    String line;
                    while ((line=buff.readLine())!=null){
                        str.append(line);
                        str.append("\n");

                    }
                } catch (IOException ex) {}
                area.setText(str.toString());
            }
        });
        but2.addActionListener(a-> {
            System.exit(0);

        });




        frame.getContentPane().add(scroll, BorderLayout.CENTER);
        frame.pack();
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}
