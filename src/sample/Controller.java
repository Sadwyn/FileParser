package sample;


import com.sun.javafx.css.converters.StringConverter;
import javafx.css.ParsedValue;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.StringContent;
import java.awt.*;
import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Controller {

    File file;


   static private FileChooser chooser = new FileChooser();
    public Label selectedFile;
    public TextArea output;
    public TextField searchField;

    static {

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("TXT & HTML","*.txt", "*.html")
        );
    }




    public void ChooseOnClick(ActionEvent actionEvent) {

        file = chooser.showOpenDialog(Main.getScene().getWindow());
        if(file!=null) {
            selectedFile.setText(file.getName());

            try {
                if (FilenameUtils.getExtension(file.getCanonicalPath()).equals("html")) {

                    Document document = null;
                    try {
                        document = Jsoup.parse(file, "UTF-8");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    output.setText(document.html());
                }


                else {
                    StringBuilder builder = new StringBuilder();
                    String s;
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    while ((s = reader.readLine())!=null){
                      builder.append(s);
                    }
                    output.setText(String.valueOf(builder));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }


    public void findOnClick(ActionEvent actionEvent) {
        output.deselect();

        if(file!=null){
            if(output.getText().contains(searchField.getText())){


                char[] chars = searchField.getText().toCharArray();
                char[] upperChars = new char[chars.length];
                for(int i = 0; i<chars.length;i++){
                    if(i==0){
                       upperChars[i]=Character.toUpperCase(chars[i]);
                    }
                    else upperChars[i]=chars[i];

                }

                String modern = String.valueOf(upperChars);

                output.setText(output.getText().replaceAll(String.valueOf(chars),modern));
                output.positionCaret(output.getText().indexOf(modern));
                output.selectEndOfNextWord();
                
            }
        }
    }
}

