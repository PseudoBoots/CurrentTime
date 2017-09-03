/**
 * @author Victor Bieniek
 * CSC 201
 * Professor Tanes Kanchanawanchai
 * Exercise 2.8
 * This class asks for the time offset from GMT that you want and gives you that time
 * 
 * This is the GUI version of the Project
*/
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.WindowEvent;

public class GUITime extends Application{
	
	Button enter;
	TextField offsetTxtF;
	TextField timeTxtF;
	Label titleLabel;
	Label offsetLabel;
	Timer updater;
	int offset;

	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("Complex Numbers");
		primaryStage.setResizable(false);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		    	System.exit(0);
		    }
		});
		
		//initialize all nodes
		offsetTxtF = new TextField();
		timeTxtF = new TextField();
		enter = new Button("Enter");
		titleLabel = new Label("Current Time");
		offsetLabel = new Label("Hours offset from GMT time: ");
		
		
		titleLabel.setFont(new Font(15));
		
		
		//This is what happens when the button is pressed
		enter.setOnAction(e -> {
			updater.start();			
		});
		
		//This is what happens when a key is pressed in the input text field
		//remove the red error color when input is changed and stop the updater
		offsetTxtF.setOnKeyReleased(e -> {
			if(e.getCode() == (KeyCode.ENTER))
			{
				enter.fire();
			}
			else
			{
				resetColor(offsetTxtF);
				updater.stop();
				timeTxtF.setText("");
			}
		});

		updater = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isInt(offsetTxtF.getText()))//check if it is an integer
				{
					offset = Integer.parseInt(offsetTxtF.getText());
					//check if the integer is in the allowed range of numbers
					if(offset > -12 && offset < 12)
					{
						timeTxtF.setText(CurrentTime.offsetTime(CurrentTime.getUTCTime(), offset));
					}
					else
					{
						//show error
						timeTxtF.setText("Invalid Input");
						errorColor(offsetTxtF);
						updater.stop();
					}
				}
			else
			{
				//show error
				timeTxtF.setText("Invalid Input");
				errorColor(offsetTxtF);
			}
			}
		});
		
		
		
		GridPane layout = new GridPane();
		
		int colPercent = 22;
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(colPercent);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(colPercent);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(colPercent - 8);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(colPercent);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(colPercent);
		layout.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
		
		for (int i = 0; i <= 8; i++) {
			RowConstraints con = new RowConstraints();
			// Here we set the height of each row
			con.setPrefHeight(20);
			layout.getRowConstraints().add(con);
        }
		
		layout.getChildren().add(offsetTxtF);
		layout.getChildren().add(timeTxtF);
		layout.getChildren().add(enter);
		layout.getChildren().add(titleLabel);
		layout.getChildren().add(offsetLabel);
		
		
		GridPane.setConstraints(titleLabel, 1, 0, 3, 1);
		GridPane.setConstraints(offsetTxtF, 3, 2);
		GridPane.setConstraints(enter, 1, 4, 3, 1);
		GridPane.setConstraints(timeTxtF, 1, 6, 3, 1);
		GridPane.setConstraints(offsetLabel, 0, 2, 3, 1);
		
		GridPane.setHalignment(enter, HPos.CENTER);
		GridPane.setHalignment(titleLabel, HPos.CENTER);
		GridPane.setHalignment(offsetLabel, HPos.RIGHT);
		
		timeTxtF.setEditable(false);
		
		Scene scene = new Scene(layout, 400, 250); //size is set
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private boolean isInt(String toCheck)
	{
		try {
			Integer.parseInt(toCheck);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private void errorColor(TextField f)
	{
		f.setStyle("-fx-text-box-border: red;");
	}
	
	private void resetColor(TextField f)
	{
		f.setStyle("-fx-text-box-border: lightgrey;");
	}


}//end class