package com.example.spartaschedule.controller;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.dto.UserRequestDto;
import com.example.spartaschedule.dto.UserResponseDto;
import com.example.spartaschedule.service.ScheduleService;
import com.example.spartaschedule.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    public ScheduleController(ScheduleService scheduleService, UserService userService){
        this.scheduleService = scheduleService;
        this.userService = userService;
    }

    //스케쥴을 생성할때 사용
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto){
        return new ResponseEntity<>(scheduleService.saveSchedule(dto),HttpStatus.CREATED);
    }

    //유저의 정보를 생성할 때 사용
    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto){
        return new ResponseEntity<>(userService.saveUser(dto),HttpStatus.CREATED);
    }

    //모든 스케쥴을 response 함
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules(){
        return scheduleService.findAllSchedules();
    }

    //pages 에 받은 페이지 번호를 기준으로 일정을 페이징 해 response 함
    @GetMapping("/page/{pages}")
    public List<ScheduleResponseDto> findAllScheduleByPage(@PathVariable int pages){
        return scheduleService.findAllSchedulesByPage(pages, 5);
    }

    //db에서 userId로 스케쥴을 검색할 때 사용
    @GetMapping("/{userId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(scheduleService.findScheduleByUserId(userId),HttpStatus.OK);
    }

    //db에서 현재 날짜를 기준으로 days 이전까지의 게시글만을 response 함ㅁ
    @GetMapping("/date/{days}")
    public List<ScheduleResponseDto> findScheduleByDate(@PathVariable int days){
        return scheduleService.findScheduleByDate(days);
    }

    //비밀번호가 일치하다만 기존의 스케쥴을 request 받은 값으로 덮어씌움
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id, @RequestBody ScheduleRequestDto dto
    ){
        return new ResponseEntity<>(scheduleService.updateSchedule(id,dto.getPassword(), dto.getTitle(),dto.getContents()),HttpStatus.OK);
    }

    //비밀번호가 일치하다면 기존의 스케쥴을 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        scheduleService.deleteSchedule(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
