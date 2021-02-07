/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearoptimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
 

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

/**
 *
 * @author Fakrul Islam
 */

@SuppressWarnings("deprecation")
public class LinearOptimization {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings({ "rawtypes", "unchecked"})
    public static void main(String[] args) {
            
        solveLinearEquationByApacheCommonMath();
    
    }//end of the main method.
    
/*
    Objective Function: Maximize 3X1 + 5X2 
    2X1 + 8X2 <=  13 
    5X1 - X2  <=  11 
    X1 >= 0,  X2 >= 0 
*/

   public static void solveLinearEquationByApacheCommonMath (){
       
    //describe the optimization problem
    LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 3, 5}, 0);
    
    Collection constraints = new ArrayList();
    constraints.add(new LinearConstraint(new double[] { 2, 8}, Relationship.LEQ, 13));
    constraints.add(new LinearConstraint(new double[] { 5, -1}, Relationship.LEQ, 11));
    
    constraints.add(new LinearConstraint(new double[] { 1, 0}, Relationship.GEQ, 0));
    constraints.add(new LinearConstraint(new double[] { 0, 1}, Relationship.GEQ, 0));
    
    
 
    //create and run solver
    RealPointValuePair solution = null;
    
    
    //To Find MAX Value GoalType.MAXIMIZE, For Min Value, GoalType.MAXIMIZE
    try {
        
        SimplexSolver p = new SimplexSolver();
        //p.setMaxIterations(10);
        //solution = new SimplexSolver().optimize(f, constraints, GoalType.MAXIMIZE, false);
        solution = p.optimize(f, constraints, GoalType.MAXIMIZE, false);
        //System.out.println("Solution found in:"+ p.getMaxIterations());
    }
    catch (OptimizationException e) {
        
        System.out.println("Solution Not Found");
        e.printStackTrace();
    }
 
    if (solution != null) {
        //get solution
        double max = solution.getValue();
        System.out.println("Opt: " + max);
 
        //print decision variables
        for (int i = 0; i < 2; i++) {
        System.out.print(solution.getPoint()[i] + "\t");
    }       
       
   }//end of the function solveLinearEquation ()
 
}
    
}//end of the Class LinearOptimization.
