package fi.arcada.p01;

public class DataModel {
    //Vår model klass definerar vår data, det är list som en "ny typ av ArrayList"

    // Vi hade en ArrayList tidigare med doubles
    // ArrayList {1.0, 2.0, osv, osv}

    // Nu definerar vi en ArrayList som håller två värden
    // ArrayList {[värde101, värde102], [värde201, värde 202], [osv], [osv]}
    private String value1;
    private String value2;

    public DataModel(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    // Vi måste göra ny metoder för vår model, så den förstår hur man hämtar första eller andra värdet
    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }
}
