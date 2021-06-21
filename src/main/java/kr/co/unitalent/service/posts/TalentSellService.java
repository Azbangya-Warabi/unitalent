package kr.co.unitalent.service.posts;

import kr.co.unitalent.domain.posts.TalentSell;
import kr.co.unitalent.domain.posts.TalentSellModifiedHistory;
import kr.co.unitalent.domain.posts.TalentSellModifiedHistoryRepository;
import kr.co.unitalent.domain.posts.TalentSellRepository;
import kr.co.unitalent.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TalentSellService {

    private final TalentSellRepository talentSellRepository;
    private final TalentSellModifiedHistoryRepository talentSellModifiedHistoryRepository;

    public List<TalentSellResponseDto> findByHomeData() {
        return talentSellRepository.findTop8ByStatusOrderByModifiedDateDesc("승인").stream().map(TalentSellResponseDto::new).collect(Collectors.toList());
    }

    public List<TalentSellResponseDto> findAll(String status) {
        return talentSellRepository.findByStatusOrderByModifiedDateDesc(status).stream().map(TalentSellResponseDto::new).collect(Collectors.toList());
    }

    public List<TalentSellResponseDto> findByBoardListWithPaging(int page, int size) {
        return talentSellRepository.findByStatus("승인", PageRequest.of(page, size)).stream().map(TalentSellResponseDto::new).collect(Collectors.toList());
    }

    public TalentSellResponseDto findByBoardNumber(Long boardNumber) {
        TalentSell entity = talentSellRepository.findById(boardNumber).orElseThrow(IllegalAccessError::new);
        return new TalentSellResponseDto(entity);
    }

    public List<TalentSellMypageResponseDto> findByMypageData(String nickname) {
        return talentSellRepository.findByNicknameOrderByModifiedDateDesc(nickname).stream().map(TalentSellMypageResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long save(TalentSellSaveRequestDto requestDto) {
        return talentSellRepository.save(requestDto.toEntity()).getBoardNumber();
    }

    @Transactional
    public Long update(Long boardNumber, TalentSellUpdateDto requestDto) {
        TalentSell entity = talentSellRepository.findById(boardNumber).orElseThrow(IllegalAccessError::new);
        if(entity.getStatus().equals("승인")) {
            talentSellModifiedHistoryRepository.save(new TalentSellModifiedHistorySaveDto(entity).toEntity());
        }
        entity.update(requestDto.getTitle(), requestDto.getCategoryCode(), requestDto.getServiceInformation(), requestDto.getImages(), requestDto.getPrice());
        return boardNumber;
    }

    @Transactional
    public Long delete(Long boardNumber) {
        TalentSell entity = talentSellRepository.findById(boardNumber).orElseThrow(IllegalAccessError::new);
        entity.delete("비활성화");
        return boardNumber;
    }
}
