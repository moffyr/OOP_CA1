/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Manages all the user's shifts and pay records. Keeps them in two
 * ArrayLists and saves them to text files so they are still there the
 * next time the app is opened.
 * 
 * @author Shourya Uppal
 */
public class EmploymentManager {
    private static final String SHIFTS_FILE = "shifts.txt";
    private static final String PAY_RECORDS_FILE = "payrecords.txt";

    private final ArrayList<WorkShift> shifts;
    private final ArrayList<PayRecord> payRecords;
    private int nextShiftId;
    private int nextPayRecordId;

    // creates the empty lists, then fills them from the files if they exist
    public EmploymentManager() {
        shifts = new ArrayList<>();
        payRecords = new ArrayList<>();
        nextShiftId = 1;
        nextPayRecordId = 1;
    }

    // ---------------- shifts ----------------

    // ADD: gives the shift the next free id, adds it to the list and saves
    public WorkShift addShift(LocalDate date, LocalTime start,
            LocalTime end, int breakMinutes, double hourlyRate) {
        WorkShift shift = new WorkShift(nextShiftId, date, start, end, breakMinutes, hourlyRate);
        nextShiftId++;
        shifts.add(shift);
        return shift;
    }

    // DISPLAY: returns every saved shift
    public ArrayList<WorkShift> viewShifts() {
        return shifts;
    }

    // SEARCH: returns only the shifts between the two dates (inclusive)
    public ArrayList<WorkShift> searchShifts(LocalDate from, LocalDate to) {
        ArrayList<WorkShift> results = new ArrayList<>();
        for (WorkShift shift : shifts) {
            LocalDate date = shift.getRecordDate();
            if (!date.isBefore(from) && !date.isAfter(to)) {
                results.add(shift);
            }
        }
        return results;
    }

    // DELETE: removes the shift with this id and saves; false if not found
    public boolean deleteShift(int recordId) {
        for (int i = 0; i < shifts.size(); i++) {
            if (shifts.get(i).getRecordId() == recordId) {
                shifts.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // total paid hours for any list of shifts (e.g. a search result)
    public double getTotalHours(ArrayList<WorkShift> list) {
        double total = 0;
        for (WorkShift shift : list) {
            total += shift.calculatePaidHours();
        }
        return total;
    }

    // total estimated earnings for any list of shifts
    public double getTotalEarnings(ArrayList<WorkShift> list) {
        double total = 0;
        for (WorkShift shift : list) {
            total += shift.calculateGrossPay();
        }
        return total;
    }
}
