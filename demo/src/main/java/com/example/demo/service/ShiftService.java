package com.example.demo.service;

import com.example.demo.builder.ShiftMapper;
import com.example.demo.domain.Shift;
import com.example.demo.domain.UserEntity;
import com.example.demo.dto.FavorableShiftDTO;
import com.example.demo.dto.ShiftCreationRequestDTO;
import com.example.demo.dto.ShiftResponseDTO;
import com.example.demo.repository.ShiftRepository;
import jakarta.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {

  @Autowired
  ShiftRepository shiftRepository;
  @Autowired
  UserService userService;

  public ShiftResponseDTO createShift(ShiftCreationRequestDTO shiftCreationRequestDTO) {
    Shift shift = ShiftMapper.buildShiftFromRequestDTO(shiftCreationRequestDTO);
    UserEntity user = userService.getUserEntityById(shiftCreationRequestDTO.getUserId());
    shift.setUserEntity(user);
    shiftRepository.save(shift);
    return ShiftMapper.buildShiftCreationResponseDTO(shift);
  }

  public ShiftResponseDTO getShiftById(Long shiftId) {
    Shift shift = shiftRepository.findById(shiftId).orElseThrow(EntityNotFoundException::new);
    return ShiftMapper.buildShiftCreationResponseDTO(shift);
  }

  public List<ShiftResponseDTO> getShiftsForUser(Long userId) {
    List<Shift> shifts = shiftRepository.findShiftByUserEntityId(userId);
    return shifts.stream().map(shift -> {
      return ShiftMapper.buildShiftCreationResponseDTO(shift);
    }).collect(
     Collectors.toList());
  }

  public List<FavorableShiftDTO> findFavorableSlots(Set<Long> userIds, Timestamp startTime,
   Timestamp endTime, Duration duration) {
    List<FavorableShiftDTO> favorableSlots = new ArrayList<>();
    List<Shift> shifts = shiftRepository.findByUserIdAndTimeRange(userIds, startTime, endTime);
    shifts.sort(Comparator.comparing(Shift::getStartTime));
    Timestamp slotStart = startTime;
    for (Shift shift : shifts) {
      Timestamp shiftStart = shift.getStartTime();
      Timestamp shiftEnd = shift.getEndTime();
      if (slotStart.toInstant().isBefore(shiftStart.toInstant())) {
        Duration slotDuration = Duration.between(slotStart.toInstant(), shiftStart.toInstant());
        if (slotDuration.compareTo(duration) >= 0) {
          favorableSlots.add(new FavorableShiftDTO(slotStart, shiftStart));
        }
      }
      slotStart = shiftEnd;
    }

    if (slotStart.toInstant().isBefore(endTime.toInstant())) {
      Duration slotDuration = Duration.between(slotStart.toInstant(), endTime.toInstant());
      if (slotDuration.compareTo(duration) >= 0) {
        favorableSlots.add(new FavorableShiftDTO(slotStart, endTime));
      }
    }
    return favorableSlots;
  }

}
