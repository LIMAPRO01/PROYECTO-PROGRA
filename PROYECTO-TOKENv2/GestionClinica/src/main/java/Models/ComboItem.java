/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Windows
 */
public class ComboItem {

    private int id;
    private String value;

    public ComboItem(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() { return id; }
    public String getValue() { return value; }

    @Override
    public String toString() {
        return value; // Esto permite que se muestre el nombre en el JComboBox
    }
}


