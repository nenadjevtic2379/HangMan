package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

import javafx.scene.text.Font;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Korsn
 */
public class Crtanje extends Canvas {

    private final GraphicsContext grafika = this.getGraphicsContext2D();
    private Random r = new Random();
   /* String[] reci = new String[]{
        "beograd",
   "kompjuter","automobil",
    "trajekt", "ara",
    "dukserica", "predraž"
    debeljko slepac
    prase stomak
    škola čamac
    jastuk jorgan
    ćebe stolica
    sto tepih
    televizor zvučnik
    fotelja prozor
    trava svetlo
    sijalica vrata
    kvaka utičnica
    kabl ekran
    baterija sat
    program meso
    krompir kečap
    majonez tanjir
    tiganj
};*/

private ArrayList<String> l = new ArrayList<>();
    private ArrayList<String> l2 = new ArrayList<>();
    private ArrayList<String> l3 = new ArrayList<>();
    private String rec = "";
    private int greske = 0;
    String s;
    TextInputDialog input = new TextInputDialog();
    boolean pobednik = false;

    public Crtanje() throws IOException {

        super();
        // requestFocus();
        setFocusTraversable(true);
        setHeight(400);
        setWidth(400);
        ucitajReci("main/resources/words.txt");
        novaRec();

        drawRec(l2);
        drawTekst();
        drawGreske(l3);
        drawMan(greske);
       // grafika.clearRect(0, 0, getWidth(), getHeight());

        setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
        public void handle(MouseEvent event) {
                startGame(greske);
            }
        });

    }

    private void drawMan(int greske) {

        switch (greske) {

            case 0:
                grafika.setLineWidth(2);
                grafika.strokeLine(getHeight() - 230, getWidth() - 20, getHeight() - 160, getWidth() - 20);
                grafika.strokeLine(this.getHeight() / 2, this.getWidth() - 280, this.getHeight() / 2, this.getWidth() - 20);
                grafika.strokeLine(this.getHeight() / 2, this.getWidth() - 280, this.getHeight() - 60, this.getWidth() - 280);

                grafika.strokeLine(this.getHeight() - 60, this.getWidth() - 280, this.getHeight() - 60, this.getWidth() - 250);
                break;

            case 1:
                drawMan(0);
                grafika.strokeOval(this.getHeight() - 74, getWidth() - 250, 30, 30);
                break;
            case 2:
                drawMan(1);
                grafika.strokeLine(this.getHeight() - 59, getWidth() - 220, this.getHeight() - 59, getWidth() - 150);
                break;
            case 3:
                drawMan(2);
                grafika.strokeLine(this.getHeight() - 59, getWidth() - 180, this.getHeight() - 17, getWidth() - 230);
                break;
            case 4:
                drawMan(3);
                grafika.strokeLine(getHeight() - 59, getWidth() - 180, getHeight() - 96, getWidth() - 230);
                break;
            case 5:
                drawMan(4);
                grafika.strokeLine(getHeight() - 59, getWidth() - 150, getHeight() - 96, getWidth() - 120);
                break;
            case 6:
                drawMan(5);
                grafika.strokeLine(getHeight() - 59, getWidth() - 150, getHeight() - 22, getWidth() - 120);
        }

    }

    public void drawRec(ArrayList<String> s) {

        grafika.setFont(Font.font(20));
        for (int i = 0; i < s.size(); i++) {
            grafika.strokeText(s.get(i), (30) * (i + 1), (50), getHeight());

        }

    }

    public void drawTekst() {
        grafika.setFont(Font.font(15));
        grafika.fillText("Greške: ", 30, 150, getHeight());
    }

    private void drawGreske(ArrayList<String> s) {
        grafika.setFont(Font.font(15));
        for (int i = 0; i < s.size(); i++) {
            grafika.fillText(s.get(i), 30 * (i + 1), 180, getHeight() - 200);
        }

    }

    private void novaRec() {

        rec = l.get(r.nextInt(l.size()));
        System.out.println(rec);

        for (int i = 0; i < rec.length(); i++) {

            l2.add("_" + " ");

        }
    }

    private void startGame(int error)  {

        /* for (String s : l2) {
            System.out.print(s);
        
        }*/
        while (!pobednik) {

            input.setTitle("Unos slova");
            input.setHeaderText("Unesite vaše slovo");
            input.setContentText("Vaše slovo je: ");

            Optional<String> result = input.showAndWait();

            if (result.isPresent()) {
                s = result.get();
                
            }

            // s = JOptionPane.showInputDialog(null, "unesi slovo");
            System.out.println("\n");
            char[] c = s.toCharArray();

            for (int j = 0; j < rec.length()-1; j++) {

                if (c.length == 2 && rec.charAt(j) == c[0] && rec.charAt(j + 1) == c[1]) {

                    l2.set(j, Character.toString(c[0]));

                    l2.set(j + 1, Character.toString(c[1]));

                    // drawRec(l2);
                } else {
                    for (int i = 0; i < rec.length(); i++) {
                        if (rec.charAt(i) == c[0]) {
                            l2.set(i, Character.toString(c[0]));
                            //  drawRec(l2);
                        }

                    }
                }

            }

            if ((!rec.contains(Character.toString(c[0])))) {
                error++;
                l3.add(Character.toString(c[0]));
            }
            //System.out.println(greske);
            drawRec(l2);
            drawMan(error);
            drawGreske(l3);

            if (!checkList(l2)) {
                pobednik = true;

              /*  Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Kraj igre");
                alert.setHeaderText("Čestitamo, pogodili ste reč");
                alert.setContentText("Nova igra?");

                ButtonType buttonTypeOne = new ButtonType("Da");
                ButtonType buttonTypeTwo = new ButtonType("Ne");

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                Optional<ButtonType> op = alert.showAndWait();
                if (op.get() == buttonTypeOne) {

                }
                if (op.get() == buttonTypeTwo) {
                    System.exit(0);
                }*/

              Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Kraj igre");
                alert.setHeaderText("Uspeli ste da pogodite reč");
                alert.setContentText("Reč je bila: " + rec);

                alert.showAndWait();
              
            }

            if (error == 6) {
                pobednik = true;
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Kraj igre");
                alert.setHeaderText("Niste uspeli da pogodite reč");
                alert.setContentText("Reč je bila: " + rec);

                alert.showAndWait();
            }

        }

    }

    private void ucitajReci(String s)  throws IOException {

       // ClassLoader cl = getClass().getClassLoader();
       // br = new BufferedReader(new FileReader(new File(cl.getClass().getClassLoader().getResourceAsStream(""))));

        FileInputStream fis = null;
            
        try {
           ClassLoader loader = this.getClass().getClassLoader();
            File file = new File(loader.getResource(s).getFile());
            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
          
        /*  File f = new File(s);
          fis = new FileInputStream(f);
          BufferedReader br = new BufferedReader(new InputStreamReader(fis));*/
          
            String linija;
             while ((linija = br.readLine()) != null) {

            l.add(linija);

        }
             br.close();
        }
       catch (FileNotFoundException e) {
           e.printStackTrace();
       }
        
        catch (IOException i) {
            i.printStackTrace();
        }
      finally {
            fis.close();
        }
       
    }

    private boolean checkList(ArrayList<String> lista) {
        String search = "_";
        for (String s : lista) {
            if (s.trim().contains(search)) {
                return true;
            }
        }
        return false;
    }

    public int getGreske() {
        return greske;
    }

    public void setGreske(int greske) {
        this.greske = greske;
    }

}
