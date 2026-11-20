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
        loadShifts();
        loadPayRecords();
    }

    // ---------------- shifts ----------------

    // ADD: gives the shift the next free id, adds it to the list and saves
    public WorkShift addShift(LocalDate date, LocalTime start,
            LocalTime end, int breakMinutes, double hourlyRate) {
        WorkShift shift = new WorkShift(nextShiftId, date, start, end, breakMinutes, hourlyRate);
        nextShiftId++;
        shifts.add(shift);
        saveShifts();
        return shift;
    }

    // DISPLAY: returns every saved shift
    public ArrayList<WorkShift> viewShifts() {
        return shifts;
    }

    // SEARCH: returns only the shifts between the two dates (inclusive)
    public ArrayList<WorkShift> searchShifts(LocalDate from, LocalDate to) {
        ArrayList<WorkShift> results =  new ArrayList<>();
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
                saveShifts();
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
    
     // ---------------- pay records ----------------

    // ADD: gives the record the next free id, adds it to the list and saves
    public PayRecord addPayRecord(LocalDate periodStart, LocalDate periodEnd,
            double expected, double actual) {
        PayRecord record = new PayRecord(nextPayRecordId, periodStart, periodEnd, expected, actual);
        nextPayRecordId++;
        payRecords.add(record);
        savePayRecords();
        return record;
    }

    // DISPLAY: returns every saved pay record
    public ArrayList<PayRecord> viewPayRecords() {
        return payRecords;
    }
    
    // ---------------- files ----------------

    public void saveShifts() {
        saveToFile(SHIFTS_FILE, shifts);
    }

    public void savePayRecords() {
        saveToFile(PAY_RECORDS_FILE, payRecords);
    }

    // writes every record to the file, one line each, replacing what was there.
    // Works for both lists because WorkShift and PayRecord both implement FileStorable.
    private void saveToFile(String fileName, ArrayList<? extends FileStorable> records) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (FileStorable record : records) {
                bw.write(record.toFileLine());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to " + fileName + ": " + e.getMessage());
        }
    }

    // reads shifts.txt back into the list when the app starts
    private void loadShifts() {
        File file = new File(SHIFTS_FILE);
        if (!file.exists()) {
            return;                    // first time running, nothing saved yet
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;          // skip empty lines
                }
                try {
                    WorkShift shift = WorkShift.fromFileLine(line);
                    if (shift.getHourlyRate() <= 0 || shift.getBreakMinutes() < 0) {
                        System.out.println("Skipped an invalid shift in " + SHIFTS_FILE + ": " + line);
                        continue;
                    }
                    shifts.add(shift);
                    // keep the next id higher than any id already used
                    nextShiftId = Math.max(nextShiftId, shift.getRecordId() + 1);
                } catch (RuntimeException e) {
                    System.out.println("Skipping bad line in " + SHIFTS_FILE + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + SHIFTS_FILE + ": " + e.getMessage());
        }
    }

    // reads payrecords.txt back into the list when the app starts
    private void loadPayRecords() {
        File file = new File(PAY_RECORDS_FILE);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                try {
                    PayRecord record = PayRecord.fromFileLine(line);
                    if (record.getExpectedGrossPay() < 0 || record.getActualGrossPay() < 0
                            || record.getPeriodStart().isAfter(record.getPeriodEnd())) {
                        System.out.println("Skipped an invalid pay record in " + PAY_RECORDS_FILE + ": " + line);
                        continue;
                    }
                    payRecords.add(record);
                    nextPayRecordId = Math.max(nextPayRecordId, record.getRecordId() + 1);
                } catch (RuntimeException e) {
                    System.out.println("Skipping bad line in " + PAY_RECORDS_FILE + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + PAY_RECORDS_FILE + ": " + e.getMessage());
        }
    }
}           
