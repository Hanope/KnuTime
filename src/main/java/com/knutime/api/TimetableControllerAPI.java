package com.knutime.api;

import com.knutime.domain.ajax.TimetableResponse;
import com.knutime.domain.timetable.Timetable;
import com.knutime.service.timetable.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timetable")
public class TimetableControllerAPI {

    @Autowired
    private TimetableService timetableService;

    @GetMapping(value = "/{serialNumber}")
    public ResponseEntity<?> getTimetable(@PathVariable String serialNumber) {
        Timetable timetable = timetableService.getTimetable(serialNumber);
        TimetableResponse result = new TimetableResponse();

        if(timetable == null) {
            result.fail();
        } else {
            result.success();
        }

        result.setResult(timetable);

        return ResponseEntity.ok(result);
    }
}
