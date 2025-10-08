// ValidadorDocumentos.java

public class ValidadorDocumentos {

    // Constantes para os tamanhos dos documentos
    private static final int TAMANHO_CPF = 11;
    private static final int TAMANHO_CNPJ = 14;

    // Impede que a classe seja instanciada
    private ValidadorDocumentos() {
    }

    /**
     * Valida um número de CPF.
     *
     * @param cpf O CPF a ser validado, pode conter formatação.
     * @return {@code true} se o CPF for válido, {@code false} caso contrário.
     */
    public static boolean validarCpf(String cpf) {
        // 1. Remove caracteres não numéricos
        String cpfNumerico = removerFormatacao(cpf);

        // 2. Verifica o tamanho e se todos os dígitos são iguais
        if (!isTamanhoValido(cpfNumerico, TAMANHO_CPF) || isSequenciaDeDigitosIguais(cpfNumerico)) {
            return false;
        }

        // 3. Calcula e compara os dígitos verificadores
        try {
            int digito1 = calcularDigitoVerificador(cpfNumerico.substring(0, 9), 10);
            int digito2 = calcularDigitoVerificador(cpfNumerico.substring(0, 9) + digito1, 11);

            // Compara os dígitos calculados com os dígitos reais do CPF
            return cpfNumerico.equals(cpfNumerico.substring(0, 9) + digito1 + digito2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida um número de CNPJ.
     *
     * @param cnpj O CNPJ a ser validado, pode conter formatação.
     * @return {@code true} se o CNPJ for válido, {@code false} caso contrário.
     */
    public static boolean validarCnpj(String cnpj) {
        // 1. Remove caracteres não numéricos
        String cnpjNumerico = removerFormatacao(cnpj);

        // 2. Verifica o tamanho e se todos os dígitos são iguais
        if (!isTamanhoValido(cnpjNumerico, TAMANHO_CNPJ) || isSequenciaDeDigitosIguais(cnpjNumerico)) {
            return false;
        }

        // 3. Calcula e compara os dígitos verificadores
        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int digito1 = calcularDigitoVerificador(cnpjNumerico.substring(0, 12), pesos1);

            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int digito2 = calcularDigitoVerificador(cnpjNumerico.substring(0, 12) + digito1, pesos2);

            // Compara os dígitos calculados com os dígitos reais do CNPJ
            return cnpjNumerico.equals(cnpjNumerico.substring(0, 12) + digito1 + digito2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // --- MÉTODOS PRIVADOS DE APOIO ---

    /**
     * Remove todos os caracteres que não são dígitos.
     */
    private static String removerFormatacao(String documento) {
        if (documento == null) {
            return "";
        }
        // Regex para manter apenas os dígitos de 0 a 9
        return documento.replaceAll("[^0-9]", "");
    }

    /**
     * Verifica se a string tem o tamanho esperado.
     */
    private static boolean isTamanhoValido(String documento, int tamanho) {
        return documento.length() == tamanho;
    }

    /**
     * Verifica se todos os caracteres da string são iguais. Ex: "11111111111".
     */
    private static boolean isSequenciaDeDigitosIguais(String documento) {
        // Regex que verifica se o primeiro caractere se repete até o final da string.
        return documento.matches("(\\d)\\1{" + (documento.length() - 1) + "}");
    }

    /**
     * Método sobrecarregado para calcular o dígito verificador do CPF.
     */
    private static int calcularDigitoVerificador(String base, int pesoMaximo) {
        int soma = 0;
        int peso = pesoMaximo;

        for (int i = 0; i < base.length(); i++) {
            soma += Character.getNumericValue(base.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;
        // Se o resto for 0 ou 1, o dígito é 0. Senão, é 11 - resto.
        return (resto < 2) ? 0 : 11 - resto;
    }
    
    /**
     * Método sobrecarregado para calcular o dígito verificador do CNPJ.
     */
    private static int calcularDigitoVerificador(String base, int[] pesos) {
        int soma = 0;
        
        for (int i = 0; i < base.length(); i++) {
            soma += Character.getNumericValue(base.charAt(i)) * pesos[i];
        }

        int resto = soma % 11;
        // Se o resto for 0 ou 1, o dígito é 0. Senão, é 11 - resto.
        return (resto < 2) ? 0 : 11 - resto;
    }
}