  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.support;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * One shift the user worked. Extends WorkRecord (so it has an id and a
 * date) and implements FileStorable so it can be saved to shifts.txt.
 *  
 * @author Shourya Uppal
 */
public class WorkShift extends WorkRecord implements FileStorable  {
    
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final int breakMinutes;
    private final double hourlyRate;

    public WorkShift(int recordId, LocalDate recordDate, LocalTime startTime, LocalTime endTime, int breakMinutes, double hourlyRate) {
        super(recordId, recordDate);
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakMinutes = breakMinutes;
        this.hourlyRate = hourlyRate;
    }
    
    // paid hours = time between start and end, minus the unpaid break
    public double calculatePaidHours() {
        long minutes = Duration.between(startTime, endTime).toMinutes();
        if (minutes < 0) {
            minutes += 24 * 60;        // shift went past midnight, e.g. 22:00 to 06:00
        }
        minutes -= breakMinutes;
        if (minutes < 0) {
            minutes = 0;               // break longer than the shift, so no paid time
        }
        return minutes / 60.0;
    }

    // gross pay before tax
    public double calculateGrossPay() {
        return calculatePaidHours() * hourlyRate;
    }

    // rebuilds a WorkShift from a line read back from shifts.txt
    public static WorkShift fromFileLine(String line) {
        String[] parts = line.split(",");
        return new WorkShift(
                Integer.parseInt(parts[0]),
                LocalDate.parse(parts[1]),
                LocalTime.parse(parts[2]),
                LocalTime.parse(parts[3]),
                Integer.parseInt(parts[4]),
                Double.parseDouble(parts[5]));
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getBreakMinutes() {
        return breakMinutes;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public String getRecordDetails() {
        return "Shift on " + recordDate + " from " + startTime + " to " + endTime
                + ", " + String.format("%.2f", calculatePaidHours()) + " paid hours, €"
                + String.format("%.2f", calculateGrossPay());
    }

    @Override
    public String toFileLine() {
         return recordId + "," + recordDate + "," + startTime + "," + endTime + ","
                + breakMinutes + "," + hourlyRate;
    }
    
}
