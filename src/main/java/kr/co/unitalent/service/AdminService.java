package kr.co.unitalent.service;

import kr.co.unitalent.domain.posts.TalentSell;
import kr.co.unitalent.domain.posts.TalentSellModifiedHistoryRepository;
import kr.co.unitalent.domain.posts.TalentSellRepository;
import kr.co.unitalent.web.dto.admin.TalentSellModifiedHistoryResponseDto;
import kr.co.unitalent.web.dto.admin.TalentSellStatusResponseDto;
import kr.co.unitalent.web.dto.admin.TalentSellStatusUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final TalentSellRepository talentSellRepository;
    private final TalentSellModifiedHistoryRepository talentSellModifiedHistoryRepository;

    public List<TalentSellStatusResponseDto> findAllNonApprovalStatus() {
        return talentSellRepository.findByStatusOrderByModifiedDateDesc("비승인").stream().map(TalentSellStatusResponseDto::new).collect(Collectors.toList());
    }

    public List<TalentSellModifiedHistoryResponseDto> findAllModifiedHistory(Long boardNumber) {
        return talentSellModifiedHistoryRepository.findByBoardNumberOrderByCreateDateDesc(boardNumber).stream().map(TalentSellModifiedHistoryResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long changeTalentSellStatus(Long boardNumber, TalentSellStatusUpdateDto talentSellStatusUpdateDto) {
        TalentSell entity = talentSellRepository.findById(boardNumber).orElseThrow(IllegalAccessError::new);
        entity.changeStatus(talentSellStatusUpdateDto.getStatus(), talentSellStatusUpdateDto.getStatusMessage());
        return boardNumber;
    }

}
