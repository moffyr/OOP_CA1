/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fairwork.rights;

/**
 * @author Salma Hussein
 */
public class PayTopic extends RightsTopic {

    public PayTopic(String title, String explanation) {
        super("Pay", title, explanation);   // sends the values up to RightsTopic
    }

    @Override
    public String getWhereToReport() {
        return "Workplace Relations Commission (WRC)";
    }

}
