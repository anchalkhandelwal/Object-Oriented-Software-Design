package mvcpattern.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

	private View theView;
	private Model theModel;
	
	public Controller(View theView, Model theModel){
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addCalculateListener(new CalculateListener());
	}
	
	class CalculateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			int firstNumber, secondNumber = 0;
			
			try{
				firstNumber = theView.getFirstNumber();
				secondNumber = theView.getSecondNumber();
				
				theModel.addTwoNumbers(firstNumber, secondNumber);
				theView.setCalcSolution(theModel.getCalculationValue());
			}catch(NumberFormatException ex){
				theView.displayErrorMessage("Need 2 integers");
			}
		}
		
	}
}
