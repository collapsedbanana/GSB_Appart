    package com.gsb_appart.gsb_appart.Services;

    import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
    import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
    import com.gsb_appart.gsb_appart.Repository.AppartRepository;
    import com.gsb_appart.gsb_appart.Repository.DemandeRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class AppartService {

        private final AppartRepository appartRepository;

        @Autowired
        public AppartService(AppartRepository appartRepository) {
            this.appartRepository = appartRepository;
        }


        @PostMapping
        public Appart addAppart(@RequestBody Appart appart) {
            return appartRepository.save(appart);
        }

        public List<Appart> getAllApparts() {
            return appartRepository.findAll();
        }


        public Optional<Appart> getAppartById(Long id) {
            return appartRepository.findById(id);
        }


        public Appart updateAppart(Long id, Appart appartDetails) {
            Appart appart = appartRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appartement non trouvé pour cet id :: " + id));
            appart.setType_appart(appartDetails.getType_appart());
            appart.setPrix_loc(appartDetails.getPrix_loc());
            appart.setPrix_charge(appartDetails.getPrix_charge());
            appart.setRue(appartDetails.getRue());
            appart.setArrondissement(appartDetails.getArrondissement());
            appart.setEtage(appartDetails.getEtage());
            appart.setAscenseur(appartDetails.isAscenseur());
            appart.setPreavis(appartDetails.getPreavis());
            appart.setDate_libre(appartDetails.getDate_libre());

            return appartRepository.save(appart);
        }


        public void deleteAppart(Long id) {
            Appart appart = appartRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appartement non trouvé pour cet id :: " + id));
            appartRepository.delete(appart);
        }
    }
