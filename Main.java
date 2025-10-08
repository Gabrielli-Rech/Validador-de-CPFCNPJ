// Main.java

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Validador de CPF/CNPJ ---");

        // --- Testes com CPF ---
        System.out.println("\n--- TESTANDO CPFs ---");
        String cpfValido = "123.456.789-00"; // Use um CPF válido para teste
        String cpfInvalidoDigito = "123.456.789-01";
        String cpfInvalidoSequencia = "111.111.111-11";
        String cpfInvalidoTamanho = "123.456.789-0";

        System.out.printf("O CPF '%s' é válido? %b\n", cpfValido, ValidadorDocumentos.validarCpf(cpfValido));
        System.out.printf("O CPF '%s' é válido? %b\n", cpfInvalidoDigito, ValidadorDocumentos.validarCpf(cpfInvalidoDigito));
        System.out.printf("O CPF '%s' é válido? %b\n", cpfInvalidoSequencia, ValidadorDocumentos.validarCpf(cpfInvalidoSequencia));
        System.out.printf("O CPF '%s' é válido? %b\n", cpfInvalidoTamanho, ValidadorDocumentos.validarCpf(cpfInvalidoTamanho));

        // --- Testes com CNPJ ---
        System.out.println("\n--- TESTANDO CNPJs ---");
        String cnpjValido = "11.222.333/0001-81"; // Use um CNPJ válido para teste
        String cnpjInvalidoDigito = "11.222.333/0001-82";
        String cnpjInvalidoSequencia = "22.222.222/2222-22";
        String cnpjInvalidoTamanho = "11.222.333/0001-8";

        System.out.printf("O CNPJ '%s' é válido? %b\n", cnpjValido, ValidadorDocumentos.validarCnpj(cnpjValido));
        System.out.printf("O CNPJ '%s' é válido? %b\n", cnpjInvalidoDigito, ValidadorDocumentos.validarCnpj(cnpjInvalidoDigito));
        System.out.printf("O CNPJ '%s' é válido? %b\n", cnpjInvalidoSequencia, ValidadorDocumentos.validarCnpj(cnpjInvalidoSequencia));
        System.out.printf("O CNPJ '%s' é válido? %b\n", cnpjInvalidoTamanho, ValidadorDocumentos.validarCnpj(cnpjInvalidoTamanho));
    }
}
