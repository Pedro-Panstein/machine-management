package weg.arquiteturasoftware.chaodefabricaapplication.DTO;

public record ResponseMaquinaDTO (
        int id,
        String nome,
        boolean operando,
        String status
) {
}
