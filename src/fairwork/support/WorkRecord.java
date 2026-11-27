/*
 *
 * @author Shourya Uppal, 24289001
 * WorkRecord.java
 * 
 */
package fairwork.support;

import java.time.LocalDate;

/*
 * Parent class for anything the tracker records against a date.
 * It is abstract because we only ever create a WorkShift or a PayRecord,
 * never a plain WorkRecord.
 * 
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
