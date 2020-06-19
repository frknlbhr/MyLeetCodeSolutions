package deneme;

/**
 * @author Furkan İlbahar
 * @dateCreated 17.06.2020
 */
public class SynchronizedTrial {

    private int sum;

    public SynchronizedTrial(int sum) {
        this.sum = sum;
    }

    public void calculate() {
        synchronized (this) {
            sum++;
        }
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
