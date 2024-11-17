package pe.edu.upc.tfversion1.serviceinterfaces;

import pe.edu.upc.tfversion1.dtos.ReportDataDTO;

import java.util.List;

public interface IReportService {
    List<ReportDataDTO> getRoleDistribution();
    List<ReportDataDTO> getActivitySummary();
    List<ReportDataDTO> getMessagesByUser();
    List<ReportDataDTO> getAdsByRestaurant();
}
