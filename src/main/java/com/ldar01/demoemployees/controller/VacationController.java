package com.ldar01.demoemployees.controller;


import com.ldar01.demoemployees.dto.request.vacation.VacationRequest;
import com.ldar01.demoemployees.dto.request.vacation.VacationUpdateRequest;
import com.ldar01.demoemployees.dto.response.GeneralResponse;
import com.ldar01.demoemployees.dto.response.vacation.VacationResponse;
import com.ldar01.demoemployees.exception.VacationNotFoundException;
import com.ldar01.demoemployees.service.VacationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VacationController {
    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/vacation-requests")
    public ResponseEntity<GeneralResponse> getVacations() {
        List<VacationResponse> vacations = vacationService.findAll();

        if (vacations.isEmpty()) {
            throw new VacationNotFoundException("Vacations were not found");
        }
        return buildResponse("Vacations found", HttpStatus.OK, vacations);
    }

    @GetMapping("/vacation-requests/{id}")
    public ResponseEntity<GeneralResponse> getVacationById(@PathVariable int id) {
        VacationResponse vacation = vacationService.findById(id);
        return buildResponse("Vacation found", HttpStatus.OK, vacation);
    }

    @GetMapping("employees/{id}/vacation-requests")
    public ResponseEntity<GeneralResponse> getEmployeeVacationsById(@PathVariable int id) {
        List<VacationResponse> vacations = vacationService.findByEmployeeId(id);
        return buildResponse("Vacation found", HttpStatus.OK, vacations);
    }

    @PostMapping("/vacation-requests")
    public ResponseEntity<GeneralResponse> saveVacation(@RequestBody @Valid VacationRequest vacation) {

        return buildResponse("Vacation created", HttpStatus.CREATED, vacationService.save(vacation));
    }

    @PutMapping("/vacation-requests")
    public ResponseEntity<GeneralResponse> updateVacation(@RequestBody @Valid VacationUpdateRequest vacation) {
        vacationService.findById(vacation.getVacationId());
        return buildResponse("Vacation updated", HttpStatus.OK, vacationService.update(vacation));
    }

    @DeleteMapping("/vacation-requests/{id}")
    public ResponseEntity<GeneralResponse> deleteVacation(@PathVariable int id) {
        VacationResponse vacation = vacationService.findById(id);
        vacationService.delete(id);
        return buildResponse("Vacation deleted", HttpStatus.OK, vacation);
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(GeneralResponse.builder()
                .message(message)
                .status(status.value())
                .data(data)
                .uri(uri)
                .time(LocalDate.now())
                .build());
    }
}
