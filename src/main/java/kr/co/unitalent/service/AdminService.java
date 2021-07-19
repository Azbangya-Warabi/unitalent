package kr.co.unitalent.service;

import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductModifiedHistoryRepository;
import kr.co.unitalent.domain.posts.TalentProductRepository;
import kr.co.unitalent.web.dto.admin.TalentProductModifiedHistoryResponseDto;
import kr.co.unitalent.web.dto.admin.TalentProductStatusResponseDto;
import kr.co.unitalent.web.dto.admin.TalentProductStatusUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final TalentProductRepository talentProductRepository;
    private final TalentProductModifiedHistoryRepository talentProductModifiedHistoryRepository;

    @Transactional(readOnly = true)
    public List<TalentProductStatusResponseDto> findAllNonApprovalStatus() {
        return talentProductRepository.findByStatusOrderByModifiedDateDesc("비승인").stream().map(TalentProductStatusResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TalentProductModifiedHistoryResponseDto> findAllModifiedHistory(Long boardNumber) {
        return talentProductModifiedHistoryRepository.findByIdOrderByCreateDateDesc(boardNumber).stream().map(TalentProductModifiedHistoryResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long changeProductStatus(Long boardNumber, TalentProductStatusUpdateDto talentProductStatusUpdateDto) {
        TalentProduct entity = talentProductRepository.findById(boardNumber).orElseThrow(IllegalAccessError::new);
        entity.changeStatus(talentProductStatusUpdateDto.getStatus(), talentProductStatusUpdateDto.getStatusMessage());
        return boardNumber;
    }

}
