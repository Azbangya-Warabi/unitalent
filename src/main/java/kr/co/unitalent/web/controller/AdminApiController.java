package kr.co.unitalent.web.controller;

import kr.co.unitalent.service.AdminService;
import kr.co.unitalent.web.dto.admin.TalentSellModifiedHistoryResponseDto;
import kr.co.unitalent.web.dto.admin.TalentSellStatusResponseDto;
import kr.co.unitalent.web.dto.admin.TalentSellStatusUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin-api")
@RestController
public class AdminApiController {

    private final AdminService adminService;

    @GetMapping("/talent-sell/nonapprove-status")
    public ResponseEntity<List<TalentSellStatusResponseDto>> findAllNonApprovalStatus() {
        return new ResponseEntity<>(adminService.findAllNonApprovalStatus(), HttpStatus.OK);
    }

    @GetMapping("/talent-sell/modified-history/{boardNumber}")
    public ResponseEntity<List<TalentSellModifiedHistoryResponseDto>> findAllNonApprovalStatusHistory(@PathVariable Long boardNumber) {
        return new ResponseEntity<>(adminService.findAllModifiedHistory(boardNumber), HttpStatus.OK);
    }

    @PostMapping("/talent-sell/{boardNumber}/status")
    public Long talentSellStatusUpdate(@PathVariable Long boardNumber, @RequestBody @Valid TalentSellStatusUpdateDto talentSellStatusUpdateDto) {
        return adminService.changeTalentSellStatus(boardNumber, talentSellStatusUpdateDto);
    }
}
