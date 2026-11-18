/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.support;

import java.time.LocalDate;

/**
 * One pay comparison: what the user expected to be paid for a pay period
 * against what their payslip actually showed. Extends WorkRecord, where
 * recordDate is used as the start of the pay period, and implements
 * FileStorable so it can be saved to payrecords.txt.
 * 
 * @author Shourya Uppal
 */
public class PayRecord extends WorkRecord implements FileStorable {
    
    private final LocalDate periodEnd;
    private final double expectedGrossPay;
    private final double actualGrossPay;
    
    public PayRecord(int recordId, LocalDate periodStart, LocalDate periodEnd,
            double expectedGrossPay, double actualGrossPay) {
        super(recordId, periodStart);   // periodStart is stored as recordDate in WorkRecord
        this.periodEnd = periodEnd;
        this.expectedGrossPay = expectedGrossPay;
        this.actualGrossPay = actualGrossPay;
    }
    
    // positive = paid more than expected, negative = underpaid, 0 = correct
    public double calculateDifference() {
        return actualGrossPay - expectedGrossPay;
    }

    // rebuilds a PayRecord from a line read back from payrecords.txt
    public static PayRecord fromFileLine(String line) {
        String[] parts = line.split(",");
        return new PayRecord(
                Integer.parseInt(parts[0]),
                LocalDate.parse(parts[1]),
                LocalDate.parse(parts[2]),
                Double.parseDouble(parts[3]),
                Double.parseDouble(parts[4]));
    }

    public LocalDate getPeriodStart() {
        return recordDate;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public double getExpectedGrossPay() {
        return expectedGrossPay;
    }

    public double getActualGrossPay() {
        return actualGrossPay;
    }
    
    // overrides the abstract method in WorkRecord
    @Override
    public String getRecordDetails() {
        return "Pay period " + recordDate + " to " + periodEnd
                + ": expected €" + String.format("%.2f", expectedGrossPay)
                + ", received €" + String.format("%.2f", actualGrossPay)
                + ", difference €" + String.format("%.2f", calculateDifference());
    }

    // one line for payrecords.txt: id,start,end,expected,actual
    @Override
    public String toFileLine() {
        return recordId + "," + recordDate + "," + periodEnd + ","
                + expectedGrossPay + "," + actualGrossPay;
    }
}

