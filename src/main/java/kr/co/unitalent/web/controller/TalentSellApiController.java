package kr.co.unitalent.web.controller;

import kr.co.unitalent.service.posts.TalentSellService;
import kr.co.unitalent.web.dto.posts.TalentSellMypageResponseDto;
import kr.co.unitalent.web.dto.posts.TalentSellResponseDto;
import kr.co.unitalent.web.dto.posts.TalentSellSaveRequestDto;
import kr.co.unitalent.web.dto.posts.TalentSellUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class TalentSellApiController {

    private final TalentSellService talentSellService;

    @GetMapping("/talent-sell/home")
    public ResponseEntity<List<TalentSellResponseDto>> findHome() {
        return new ResponseEntity<>(talentSellService.findByHomeData(), HttpStatus.OK);
    }

    @GetMapping("/talent-sell/list/page/{page}/amount/{amount}")
    public ResponseEntity<List<TalentSellResponseDto>> findByBoardListWithPaging(@PathVariable int page, @PathVariable int amount) {
        return new ResponseEntity<>(talentSellService.findByBoardListWithPaging(page-1, amount), HttpStatus.OK);
    }

    @GetMapping("/talent-sell/{nickname}/mypage/sell/myservice")
    public ResponseEntity<List<TalentSellMypageResponseDto>> findMypage(@PathVariable String nickname) {
        return new ResponseEntity<>(talentSellService.findByMypageData(nickname), HttpStatus.OK);
    }

    @GetMapping("/talent-sell")
    public ResponseEntity<List<TalentSellResponseDto>> findAll() {
        return new ResponseEntity<>(talentSellService.findAll("승인"), HttpStatus.OK);
    }

    @GetMapping("/talent-sell/{boardNumber}")
    public TalentSellResponseDto findByBoardNumber(@PathVariable Long boardNumber) {
        return talentSellService.findByBoardNumber(boardNumber);
    }

    @PostMapping("/talent-sell")
    public Long talentSellSave(@RequestBody @Valid TalentSellSaveRequestDto talentSellSaveRequestDto) {
        log.info(talentSellSaveRequestDto.toString());
        return talentSellService.save(talentSellSaveRequestDto);
    }

    @PutMapping("/talent-sell/{boardNumber}")
    public Long talentSellUpdate(@PathVariable Long boardNumber, @RequestBody @Valid TalentSellUpdateDto talentSellUpdateDto) {
        return talentSellService.update(boardNumber, talentSellUpdateDto);
    }

    @DeleteMapping("/talent-sell/{boardNumber}")
    public Long talentSellDelete(@PathVariable Long boardNumber) {
        return talentSellService.delete(boardNumber);
    }


}
