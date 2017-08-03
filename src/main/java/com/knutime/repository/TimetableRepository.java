package com.knutime.repository;

import com.knutime.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    Timetable findOneBySerialNumber(String serialNumber);
}
