package com.example.spartaschedule.service;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;
import com.example.spartaschedule.repository.ScheduleRepository;
import com.example.spartaschedule.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    //스케쥴을 생성할 때 사용한다. 비밀번호, 사용자 이름, 제목이 null이 아닌지 검증한다.
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto.getPassword(), requestDto.getUserId(),requestDto.getUserName(),requestDto.getTitle(),requestDto.getContents());
        if (
                schedule.getUserName() == null || schedule.getPassword() == null || schedule.getTitle() == null
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The username, password, title and contents are not required values.");
        }
        //User를 생성하고 그 키 값을 userId로 받는다
        Number userId = userRepository.saveUserToSchedule(schedule);
        return scheduleRepository.saveSchedule(schedule, userId);
    }

    //모든 스케쥴을 response 함
    @Override
    public List<ScheduleResponseDto> findAllSchedules(){
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();
        return allSchedules;
    }

    //페이징으로 스케쥴 목록을 보여준다.
    @Override
    public List<ScheduleResponseDto> findAllSchedulesByPage(int page, int pageSize) {
        //모든 스케쥴을 가지고있는 allSchedules
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();
        //페이징에 필요한 스케쥴만을 받는 pageSchedules
        List<ScheduleResponseDto> pageSchedules = new ArrayList<>();

        //Math의 min을 사용해 statr+pageSize가 리스트의 값보다 클때 생기는 오류를 방지한다.
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, allSchedules.size());

        //start가 스케쥴의 크기보다 클 시 빈 배열을 반환한다.
        if (start >= allSchedules.size() || start < 0) {
            return pageSchedules;
        }

        //조건에 맞는 스케쥴을 추가한다.
        for (int i = start; i < end; i++) {
            pageSchedules.add(allSchedules.get(i));
        }
        return pageSchedules;
    }

    //사용자 id로 스케쥴을 찾아 반환한다.
    @Override
    public ScheduleResponseDto findScheduleByUserId(Long id){
        Schedule schedule = scheduleRepository.findScheduleByUserIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    //현재 날짜 -days 에 해당하는 스케쥴을 리스트로 반환한다.
    @Override
    public List<ScheduleResponseDto> findScheduleByDate(int days){
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findScheduleByDate(days);
        return allSchedules;
    }

    //스케쥴을 덮어씌울 때 사용한다. title이 null값이거나 id에 해당하는 스케쥴이 없을 때 예외처리 한다.
    @Override
    public ScheduleResponseDto updateSchedule(Long id,String password, String title, String contents){
        if (title == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The title is not required values.");
        }
        int updateRow = scheduleRepository.updateSchedule(id, password, title, contents);
        if (updateRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No date has been modified");
        }
        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

    //스케쥴을 삭제할 때 사용한다. id에 해당하는 스케쥴이 없을 때 예외처리 한다.
    @Override
    public void deleteSchedule(Long id, String password){
        int deleteRow = scheduleRepository.deleteSchedule(id, password);
        if (deleteRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No date has been modified");
        }
    }
}
