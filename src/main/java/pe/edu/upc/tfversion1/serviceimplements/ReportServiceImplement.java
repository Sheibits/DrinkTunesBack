package pe.edu.upc.tfversion1.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tfversion1.dtos.ReportDataDTO;
import pe.edu.upc.tfversion1.repositories.*;
import pe.edu.upc.tfversion1.serviceinterfaces.IReportService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImplement implements IReportService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRestaurantAdsRepository restaurantAdsRepository;

    @Autowired
    private IMessagesRepository messagesRepository;

    @Autowired
    private IArtistProfileRepository artistProfileRepository;

    @Autowired
    private IArtistApplicationsRepository artistApplicationsRepository;

    @Override
    public List<ReportDataDTO> getRoleDistribution() {
        return userRepository.countUsersByRole();
    }

    @Override
    public List<ReportDataDTO> getActivitySummary() {
        List<ReportDataDTO> reportData = new ArrayList<>();

        // Número de anuncios
        Long adsCount = restaurantAdsRepository.count();
        reportData.add(new ReportDataDTO("Anuncios", adsCount));

        // Número de mensajes
        Long messagesCount = messagesRepository.count();
        reportData.add(new ReportDataDTO("Mensajes", messagesCount));

        // Número de perfiles de artistas
        Long profilesCount = artistProfileRepository.count();
        reportData.add(new ReportDataDTO("Perfiles de Artistas", profilesCount));

        // Número de aplicaciones de artistas
        Long applicationsCount = artistApplicationsRepository.count();
        reportData.add(new ReportDataDTO("Aplicaciones de Artistas", applicationsCount));

        return reportData;
    }

    @Override
    public List<ReportDataDTO> getMessagesByUser() {
        return messagesRepository.countMessagesByUser();
    }

    @Override
    public List<ReportDataDTO> getAdsByRestaurant() {
        return restaurantAdsRepository.countAdsByRestaurant();
    }
}
