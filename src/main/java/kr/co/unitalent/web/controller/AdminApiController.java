package kr.co.unitalent.web.controller;

import kr.co.unitalent.service.AdminService;
import kr.co.unitalent.web.dto.admin.TalentProductModifiedHistoryResponseDto;
import kr.co.unitalent.web.dto.admin.TalentProductStatusResponseDto;
import kr.co.unitalent.web.dto.admin.TalentProductStatusUpdateDto;
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

    @GetMapping("/product/nonapprove-status")
    public ResponseEntity<List<TalentProductStatusResponseDto>> findAllNonApprovalStatus() {
        return new ResponseEntity<>(adminService.findAllNonApprovalStatus(), HttpStatus.OK);
    }

    @GetMapping("/product/modified-history/{boardNumber}")
    public ResponseEntity<List<TalentProductModifiedHistoryResponseDto>> findAllNonApprovalStatusHistory(@PathVariable Long boardNumber) {
        return new ResponseEntity<>(adminService.findAllModifiedHistory(boardNumber), HttpStatus.OK);
    }

    @PostMapping("/product/{boardNumber}/status")
    public Long talentProductUpdate(@PathVariable Long boardNumber, @RequestBody @Valid TalentProductStatusUpdateDto talentProductStatusUpdateDto) {
        return adminService.changeProductStatus(boardNumber, talentProductStatusUpdateDto);
    }
}
