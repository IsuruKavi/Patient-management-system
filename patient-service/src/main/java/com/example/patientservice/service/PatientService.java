package com.example.patientservice.service;

import com.example.patientservice.dto.PatientRequestDTO;
import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.exception.EmailAlreadyExistsException;
import com.example.patientservice.exception.PatientNotFoundException;
import com.example.patientservice.mapper.PatientMapper;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRespository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRespository patientRespository;
    public PatientService(PatientRespository patientRespository) {
        this.patientRespository = patientRespository;
    }
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRespository.findAll();
        return patients.stream().map(patient-> PatientMapper.toDTO(patient)).toList();
    }
    public PatientResponseDTO getPatientById(UUID id) {
      Patient patient = patientRespository.findById(id).orElse(null);
        assert patient != null;
        return PatientMapper.toDTO(patient);
    }
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
      if(patientRespository.existsByEmail(patientRequestDTO.getEmail())) {
          throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
      }
       Patient patient= patientRespository.save(PatientMapper.toModel(patientRequestDTO));
       return PatientMapper.toDTO(patient);
    }
    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO) {
        Patient patient= patientRespository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with ID: " + id));
//        if(patientRespository.existsByEmail(patientRequestDTO.getEmail()) && !patient.getEmail().equals(patientRequestDTO.getEmail())) {
//            System.out.println("Patient with this email already exists " + patientRequestDTO.getEmail());
//            throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
//        } alternative
        if(patientRespository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            System.out.println("Patient with this email already exists " + patientRequestDTO.getEmail());
            throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        Patient updatedPatient= patientRespository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRespository.deleteById(id);

    }
}
