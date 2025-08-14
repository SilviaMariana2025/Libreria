public class CuentaBancaria {
    String titular;
    String numeroCuenta;
    double saldo;
    public CuentaBancaria(String titular, String numeroCuenta, double saldo) {
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }
    public void mostrarDatos() {
        System.out.println("Titular: " + titular);
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo actual: $" + saldo);
    }
    public void depositar(double monto) {
        saldo += monto;
        System.out.println(titular + " depositó $" + monto + ". Saldo actual: $" + saldo);
    }
    public void retirar(double monto) {
        saldo -= monto;
        System.out.println(titular + " retiró $" + monto + ". Saldo actual: $" + saldo);
    }
    public void transferir(CuentaBancaria destino, double monto) {
        saldo -= monto;
        destino.saldo += monto;
        System.out.println(titular + " transfirió $" + monto + " a " + destino.titular);
        System.out.println(titular + " quedó con $" + saldo + " y " + destino.titular + " quedó con $" + destino.saldo);
    }
    public static void main(String[] args) {
        CuentaBancaria cuenta1 = new CuentaBancaria("Mariana Grajales", "001122", 500000);
        CuentaBancaria cuenta2 = new CuentaBancaria("Juan Pérez", "003344", 300000);
        CuentaBancaria cuenta3 = new CuentaBancaria("Laura Martínez", "005566", 200000);

        cuenta1.depositar(100000);
        System.out.println();
        cuenta2.retirar(50000);
        System.out.println();
        cuenta1.transferir(cuenta3, 150000);
        System.out.println();
        cuenta1.mostrarDatos();
        System.out.println();
        cuenta2.mostrarDatos();
        System.out.println();
        cuenta3.mostrarDatos();
    }
}
