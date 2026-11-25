/*CIS/SWE/24/005
  WUNUKEN AMAYIKAI
   SOFTWARE ENGINEERING*/

public class VatCalculator {

    private static final double UNIT_PRICE = 49.99;
    private static final double VAT_RATE = 0.075;

    static double totalWithVat(int qty) {
        double subtotal = qty * UNIT_PRICE;
        double vat = subtotal * VAT_RATE;
        return subtotal + vat;
    }

    public static void main(String[] args) {
        System.out.printf("%.2f%n", totalWithVat(1)); // 53.74
        System.out.printf("%.2f%n", totalWithVat(3)); // 161.22
    }
}