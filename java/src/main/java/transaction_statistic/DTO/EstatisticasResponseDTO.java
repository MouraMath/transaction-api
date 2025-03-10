package transaction_statistic.DTO;

public record EstatisticasResponseDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max) {

}
