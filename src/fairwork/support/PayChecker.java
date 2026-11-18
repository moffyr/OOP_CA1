/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.support;

import java.time.LocalDate;
import java.util.ArrayList;

/**
  * Checks whether the user was paid correctly for a pay period and
 * explains the result in plain words.
 * 
 * @author Shourya Uppal    
 */
public class PayChecker {
    // differences smaller than one cent count as paid correctly,
    // so rounding on a payslip doesn't show up as underpaid
    private static final double TOLERANCE = 0.01;

    // positive = paid more than expected, negative = underpaid
    public double comparePay(double expected, double actual) {
        return actual - expected;
    }

    // adds up the gross pay of every saved shift between the two dates,
    // including shifts on the start and end dates themselves
    public double calculateExpectedPay(ArrayList<WorkShift> shifts,
            LocalDate periodStart, LocalDate periodEnd) {
        double total = 0;
        for (WorkShift shift : shifts) {
            LocalDate date = shift.getRecordDate();
            if (!date.isBefore(periodStart) && !date.isAfter(periodEnd)) {
                total += shift.calculateGrossPay();
            }
        }
        return total;
    }

    // turns the difference into a message for the user
    public String getComparisonMessage(double difference) {
        if (Math.abs(difference) < TOLERANCE) {
            return "You were paid correctly for this period.";
        } else if (difference < 0) {
            return "You were underpaid by €" + String.format("%.2f", -difference)
                    + ". Check your payslip and contact the Workplace Relations Commission (WRC) if it isn't corrected.";
        } else {
            return "You were paid €" + String.format("%.2f", difference)
                    + " more than expected. Check your payslip with your employer.";
        }
    }
}
