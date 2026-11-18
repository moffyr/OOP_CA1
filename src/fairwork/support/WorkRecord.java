/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.support;

import java.time.LocalDate;

/**
 * Parent class for anything the tracker records against a date.
 * It is abstract because we only ever create a WorkShift or a PayRecord,
 * never a plain WorkRecord.
 * 
 * @author Shourya Uppal
 */
public abstract class WorkRecord {
    // protected so the child classes can use them directly
    protected int recordId;
    protected LocalDate recordDate;

    public WorkRecord(int recordId, LocalDate recordDate) {
        this.recordId = recordId;
        this.recordDate = recordDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }
    
    // each child class describes itself differently (polymorphism)
    public abstract String getRecordDetails(); 
}
