package com.shpp.pzobenko.validation.application.service;

import com.shpp.pzobenko.validation.application.exception.TheCitizenAlreadyExistException;
import com.shpp.pzobenko.validation.application.exception.TheCitizenOfUkraineNotFoundException;
import com.shpp.pzobenko.validation.application.exception.TheIPNHaveWrongValues;
import com.shpp.pzobenko.validation.application.repository.CitizenOfUkraineRepo;
import com.shpp.pzobenko.validation.application.ukrainianpeople.CitizenOfUkraine;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Log4j2
public class FindSaveDeleteEditCitizen {
    private final CitizenOfUkraineRepo repository;

    /**
     * Find citizen by inp in DB.
     *
     * @param ipn the valid IPN of the citizen.
     * @return Citizen.
     */
    public CitizenOfUkraine getCitizenOfUkraineFromDB(Long ipn) {
        return repository.findById(ipn)
                .orElseThrow(() -> new TheCitizenOfUkraineNotFoundException(ipn));
    }

    /**
     * Set old lastName into new
     * @param ipn to find a citizen
     * @param lastName new last name
     * @return citizen but with new lastName
     */
    public CitizenOfUkraine changeLastName(Long ipn, String lastName) {
        CitizenOfUkraine newLastName = getCitizenOfUkraineFromDB(ipn);
        newLastName.setLastName(lastName);
        return repository.save(newLastName);
    }

    /**
     * Set old firstName into new
     * @param ipn to find a citizen
     * @param firstName new first name
     * @return citizen but with new firstName
     */
    public CitizenOfUkraine changeFirstName(Long ipn, String firstName) {
        CitizenOfUkraine newFirstName = getCitizenOfUkraineFromDB(ipn);
        newFirstName.setFirstName(firstName);
        return repository.save(newFirstName);
    }

    /**
     * All citizens from db
     * @return citizens in db
     */
    public List<CitizenOfUkraine> getAllCitizens() {
        return repository.findAll();
    }

    /**
     * Save citizen to DB, but before make sure that we don't have anyone with ipn like on new user.
     *
     * @param newCitizen New citizen.
     * @return info.
     */
    public CitizenOfUkraine saveCitizenToDB(CitizenOfUkraine newCitizen) {
        Long ipnFromNewCitizen = newCitizen.getIpn();

        if (!CheckTheIpnForSexService.areIPNValidBySex(ipnFromNewCitizen, newCitizen.getGenders()))
            throw new TheIPNHaveWrongValues(newCitizen.getIpn());

        log.info("The citizen with IPN {} and sex {} have correct values.", ipnFromNewCitizen,
                newCitizen.getGenders());
        log.info("Adding new citizen with name {} {} and ipn {}", newCitizen.getFirstName(),
                newCitizen.getLastName(), ipnFromNewCitizen);

        if (repository.existsByIpn(ipnFromNewCitizen))
            throw new TheCitizenAlreadyExistException(ipnFromNewCitizen);

        log.info("Adding citizen with ipn {} to DB", ipnFromNewCitizen);

        return repository.save(newCitizen);
    }

    /**
     * Delete citizen from db
     * @param ipn to find a citizen
     * @return citizen what was deleted
     */
    public CitizenOfUkraine deleteCitizenFromDB(Long ipn) {
        if(!repository.existsByIpn(ipn)){
            throw new TheCitizenOfUkraineNotFoundException(ipn);
        }
        CitizenOfUkraine citizenWhichNeedToDelete = getCitizenOfUkraineFromDB(ipn);
        repository.deleteById(ipn);
        log.info("Citizen with IPN {} was deleted successful", ipn);
        return citizenWhichNeedToDelete;
    }
}
