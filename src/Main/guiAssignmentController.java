/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Brady
 */
public class guiAssignmentController implements Initializable {
    
    @FXML
    private Label lblteacher;

    @FXML
    private Pane paneseat_01;

    @FXML
    private Pane paneseat_02;

    @FXML
    private Pane paneseat_03;

    @FXML
    private Pane paneseat_04;

    @FXML
    private Pane paneseat_05;

    @FXML
    private Pane paneseat_06;

    @FXML
    private Pane paneseat_07;

    @FXML
    private Pane paneseat_08;

    @FXML
    private Pane paneseat_09;

    @FXML
    private Button btnadd_student;

    @FXML
    private Label lblseat_01;

    @FXML
    private Label lblseat_02;

    @FXML
    private Label lblseat_03;

    @FXML
    private Label lblseat_04;

    @FXML
    private Label lblseat_05;

    @FXML
    private Label lblseat_06;

    @FXML
    private Label lblseat_07;

    @FXML
    private Label lblseat_08;

    @FXML
    private Label lblseat_09;

    @FXML
    private Label lblstudent_name;

    @FXML
    private Label lblstudent_color;

    @FXML
    private TextField txtfldstudent_name;

    @FXML
    private ColorPicker color_picker;

    @FXML
    private Label lblname_error;

    @FXML
    private Label lblcolor_error;
    
    
    boolean checkme = true;
    ArrayList<String> pickedcolors = new ArrayList();
    ArrayList<Integer> seats = new ArrayList(Arrays.asList(0,1,2,3,4,5,6,7,8));
    Random rand = new Random();
    ArrayList<Label> names = new ArrayList();
    ArrayList<Pane> desks = new ArrayList();
    
    @FXML
    private void addStudentButton(ActionEvent event) //The event handler for our button click.
    {
        if(checkme) //Had to add them to the array for some reason, couldn't do asList
        {           //or initialize a regular array... Weird :S
            checkme= false;
            names.add(lblseat_01);
            names.add(lblseat_02);
            names.add(lblseat_03);
            names.add(lblseat_04);
            names.add(lblseat_05);
            names.add(lblseat_06);
            names.add(lblseat_07);
            names.add(lblseat_08);
            names.add(lblseat_09);
            
            desks.add(paneseat_01);
            desks.add(paneseat_02);
            desks.add(paneseat_03);
            desks.add(paneseat_04);
            desks.add(paneseat_05);
            desks.add(paneseat_06);
            desks.add(paneseat_07);
            desks.add(paneseat_08);
            desks.add(paneseat_09);
        }
        Color picked = color_picker.getValue(); //Gets Color value.
        String color = hexFix(picked); //Gets/sets proper hex value for color.
        int seatnum;
        try
        {
            seatnum = rand.nextInt(seats.size()); //Gets random seat number from the seats left.
        } catch(Exception e){seatnum = 0;}
        String stdname = txtfldstudent_name.getText(); //Gets text from name field.
        
        if(txtfldstudent_name.getText().isEmpty()) //Checks name field.
        {
            lblname_error.setText("You must enter a name.");
        }
        else if(checkColor(color))
        {
            lblcolor_error.setText("You must pick a different color.");
        }
        else
        {
            try //Error handling.
            {
                names.get(seats.get(seatnum)).setText(stdname);
                desks.get(seats.get(seatnum)).setStyle("-fx-background-color: " +color);
                lblname_error.setText("");
                lblcolor_error.setText("");
                pickedcolors.add(color);
                seats.remove(seatnum);
            }
            catch(Exception e) //Should only run once the seats are used or if I missed an error lol
            {
                lblname_error.setText("No more seats.");
                lblcolor_error.setText("Thanks for using my app!");
            }
        }
        
    }
    
    //Didn't use a rectangle shape at first and decided to do it the hard way... this method helps.
    private String hexFix(Color color) //Changes funky hex code from ColorPicker to a proper hex value.
    {
        StringBuilder sb = new StringBuilder(color.toString()); //StringBuilder will save your life.
        String fixed; //To store our result from sb.
        
        sb.deleteCharAt(9); //Had these backwards at first and got out of bounds... fml
        sb.deleteCharAt(8);
        sb.deleteCharAt(1);
        sb.replace(0, 1, "#"); //Found out this is NOT inclusive.
        fixed = sb.toString(); //Casts StringBuilder object to a String.
        return fixed; //Returns the proper hex value.
    }
    
    private boolean checkColor(String whatcolor) //Checks if color has been picked.
    {
        try
        {
            for(String a : pickedcolors)
            {
                if(whatcolor.equals(a)) //Compares the colors.
                {
                    return true;
                }
                else{}
            }
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
