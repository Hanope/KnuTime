package com.knutime.api;

import com.knutime.domain.ajax.AjaxResponseMessage;
import com.knutime.domain.ajax.AjaxResponseObject;
import com.knutime.domain.timetable.Timetable;
import com.knutime.domain.user.CurrentUser;
import com.knutime.service.timetable.TimetableService;
import com.knutime.support.CustomErrorType;
import com.knutime.support.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timetable")
public class TimetableAPI {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/{serialNumber}")
    public ResponseEntity<?> getTimetable(@PathVariable String serialNumber) {
        AjaxResponseObject result = new AjaxResponseObject();
        Timetable timetable = timetableService.getTimetable(serialNumber);

        if (timetable == null) {
            return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_NOT_FOUND_TABLE),
                    HttpStatus.NOT_FOUND);
        } else {
            result.success();
            result.setMessage("timetable load success");
            result.setResult(timetable);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/{serialNumber}/{courseId}")
    public ResponseEntity<?> addCourse(@PathVariable String serialNumber, @PathVariable Long courseId) {
        AjaxResponseMessage result = new AjaxResponseMessage();
        CurrentUser user = CurrentUser.getCurrentUser();

        if (user == null) {
            return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_UNAUTHORIZED_USER),
                    HttpStatus.UNAUTHORIZED);
        } else {
            int flag = timetableService.addCourse(serialNumber, courseId, user.getId());

            switch (flag) {
                case TimetableService.BAD_ACCESS:
                    return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_BAD_ACCESS),
                            HttpStatus.BAD_REQUEST);
                case TimetableService.EXISTS_COURSE:
                    return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_EXISTS_COURSE),
                            HttpStatus.CONFLICT);
                case TimetableService.EXISTS_TIME:
                    return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_EXISTS_TIME),
                            HttpStatus.CONFLICT);
                case TimetableService.UNAUTHORIZED:
                    return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_UNAUTHORIZED_USER),
                            HttpStatus.UNAUTHORIZED);
                case TimetableService.SUCCESS:
                    result.success();
                    result.setMessage("과목이 추가되었습니다.");
            }

            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/{serialNumber}/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable String serialNumber, @PathVariable Long courseId) {
        AjaxResponseMessage result = new AjaxResponseMessage();
        CurrentUser user = CurrentUser.getCurrentUser();

        if (user == null)
            return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_UNAUTHORIZED_USER),
                    HttpStatus.UNAUTHORIZED);

        int flag = timetableService.deleteCourse(serialNumber, courseId, user.getId());

        switch (flag) {
            case TimetableService.NOT_FOUND:
                return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_NOT_FOUND_COURSE),
                        HttpStatus.NOT_FOUND);
            case TimetableService.UNAUTHORIZED:
                return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_UNAUTHORIZED_USER),
                        HttpStatus.UNAUTHORIZED);
            case TimetableService.SUCCESS:
                result.success();
                result.setMessage("과목이 삭제되었습니다.");
        }

        return ResponseEntity.ok(result);
    }
}
